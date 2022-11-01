package org.firstinspires.ftc.twenty403.subsystem;

import java.util.function.Supplier;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.Range;

import com.technototes.library.logger.Log;
import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;

public class VisionPipeline extends OpenCvPipeline implements Supplier<Integer>, Loggable {

    @Config
    public static class VisionConstants {
        public enum ParkingPosition {
            LEFT,
            CENTER,
            RIGHT,
        }

        // Yellow is around 25 (50 degrees)
        public static double YELLOW = 25;
        // Aqua is at 100 (200 degrees)
        public static double AQUA = 100;
        // Purple is at 170 (340 degrees)
        public static double PINK = 170;
        // The 'range' around the hue that we're looking for
        public static double RANGE = 10;

        // The low saturation point for color identification
        public static double lowS = 60;
        // The high saturation point for color identification
        public static double highS = 255;
        // The low value for color ID
        public static double lowV = 50;
        // The high value for color ID
        public static double highV = 255;

        // In the 160x120 bitmap, where are we looking?
        public static int X = 45;
        public static int Y = 60;
        public static int WIDTH = 60;
        public static int HEIGHT = 60;

        // What color should we draw the outlining rectangle?
        public static Scalar HIGHLIGHT = new Scalar(255, 0, 255);
    }

    @LogConfig.Run(duringRun = false, duringInit = true)
    @Log.Boolean(name = "left")
    public volatile boolean leftDetected = false;

    @LogConfig.Run(duringRun = false, duringInit = true)
    @Log.Boolean(name = "middle")
    public volatile boolean middleDetected = true;

    @LogConfig.Run(duringRun = false, duringInit = true)
    @Log.Boolean(name = "right")
    public volatile boolean rightDetected = false;

    public Mat customColorSpace = new Mat();
    public Mat Cr = new Mat();
    public Mat img = null;

    private int countColor(double hue) {
        Scalar edge1 = new Scalar(hue - VisionConstants.RANGE, VisionConstants.lowS, VisionConstants.lowV);
        Scalar edge2 = new Scalar(hue + VisionConstants.RANGE, VisionConstants.highS, VisionConstants.highV);
        // Check to see which pixels are between edge1 & edge2, output into a boolean matrix Cr
        Core.inRange(customColorSpace, edge1, edge2, Cr);
        // TODO: This should probably do something faster
        // Only look at the 'right' spot in the image
        int count = 0;
        for (int i = 0; i < VisionConstants.WIDTH && i < Cr.width(); i++) {
            for (int j = 0; j < VisionConstants.HEIGHT && j < Cr.height(); j++) {
                if (Cr.get(j + VisionConstants.Y, i + VisionConstants.X)[0] > 0) {
                    count++;
                    // Draw a dot on the image at this point - input was put into img
                    // The color choice makes things stripey
                    img.put(j + VisionConstants.Y, i + VisionConstants.X, ((j + i) & 3) != 0 ? edge1.val : edge2.val);
                }
            }
        }
        return count;
    }

    public void inputToCr(Mat input) {
        // Convert the RGB image to HSV, because HUE is much easier to to deal with
        // The output is in 'customColorSpace'
        // Maybe 'smooth' the image first? Dunno...
        Imgproc.cvtColor(input, customColorSpace, Imgproc.COLOR_RGB2HSV);
        // Plop the input matrix in a member variable, so that countColor can write to it
        img = input;
        int countY = countColor(VisionConstants.YELLOW);
        int countA = countColor(VisionConstants.AQUA);
        int countP = countColor(VisionConstants.PINK);
        middleDetected = countA >= countY && countA >= countP;
        rightDetected = countP >= countA && countP >= countY;
        leftDetected = !rightDetected && !middleDetected;
        // Draw a highlight rectangle around the area we're looking at
        int x = Range.clip(VisionConstants.X - 1, 0, input.width() - 1);
        int y = Range.clip(VisionConstants.Y - 1, 0, input.height() - 1);
        int w = Range.clip(VisionConstants.WIDTH + 2, 1, input.width() - x);
        int h = Range.clip(VisionConstants.HEIGHT + 2, 1, input.height() - y);
        Imgproc.rectangle(input, new Rect(x, y, w, h), VisionConstants.HIGHLIGHT);
    }

    public void init(Mat firstFrame) {
        inputToCr(firstFrame);
    }

    @Override
    public Mat processFrame(Mat input) {
        inputToCr(input);
        return input;
    }

    @Override
    public Integer get() {
        return null;
    }

    public boolean left() {
        return leftDetected;
    }

    public boolean middle() {
        return middleDetected;
    }

    public boolean right() {
        return rightDetected;
    }
}
