package org.firstinspires.ftc.swerveteen750.subsystem;

import android.graphics.Bitmap;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.swerveteen750.command.autonomous.StartingPosition;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.function.Supplier;

public class VisionPipeline extends OpenCvPipeline implements Supplier<Integer>, Loggable {

    public Alliance alliance;
    public StartingPosition side;

    public VisionPipeline(Alliance teamAlliance, StartingPosition startSide) {
        super();
        alliance = teamAlliance;
        side = startSide;
    }

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

        // In the 240x320 bitmap, where are we looking?
        public static int X = 132;
        public static int Y = 50;
        public static int WIDTH = 86;
        public static int HEIGHT = 50;

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

    @LogConfig.Run(duringRun = false, duringInit = true)
    @Log.Number(name = "FPS")
    public volatile double fps = 0.0;

    private ElapsedTime time = new ElapsedTime();

    public Mat customColorSpace = new Mat();
    public Mat Cr = new Mat();
    public Mat img = null;

    private int countColor(double hue) {
        Scalar edge1 = new Scalar(hue - VisionConstants.RANGE, VisionConstants.lowS, VisionConstants.lowV);
        Scalar edge2 = new Scalar(hue + VisionConstants.RANGE, VisionConstants.highS, VisionConstants.highV);
        // Check to see which pixels are between edge1 & edge2, output into a boolean matrix Cr
        Core.inRange(customColorSpace, edge1, edge2, Cr);
        int count = 0;
        for (int i = 0; i < Cr.width(); i++) {
            for (int j = 0; j < Cr.height(); j++) {
                if (Cr.get(j, i)[0] > 0) {
                    count++;
                    // Draw a dot on the image at this point - input was put into img
                    // The color choice makes things stripey, which makes it easier to identify
                    if (VisionSubsystem.VisionSubsystemConstants.DEBUG_VIEW) {
                        double[] colorToDraw = ((j + i) & 3) != 0 ? edge1.val : edge2.val;
                        img.put(j + VisionConstants.Y, i + VisionConstants.X, colorToDraw);
                    }
                }
            }
        }
        return count;
    }

    public void inputToCr(Mat input) {
        // Put the input matrix in a member variable, so that other functions can draw on it
        img = input;

        // First, slice the smaller rectangle out of the overall bitmap:
        Mat rectToLookAt = input.submat(
                // Row start to Row end
                VisionConstants.Y, VisionConstants.Y + VisionConstants.HEIGHT,
                // Col start to Col end
                VisionConstants.X, VisionConstants.X + VisionConstants.WIDTH);

        // Next, convert the RGB image to HSV, because HUE is much easier to identify colors in
        // The output is in 'customColorSpace'
        Imgproc.cvtColor(rectToLookAt, customColorSpace, Imgproc.COLOR_RGB2HSV);

        // Check to see which colors occur:
        int countY = countColor(VisionConstants.YELLOW);
        int countA = countColor(VisionConstants.AQUA);
        int countP = countColor(VisionConstants.PINK);

        // Check which spot we should park in
        middleDetected = countA >= countY && countA >= countP;
        rightDetected = countP >= countA && countP >= countY;
        leftDetected = !rightDetected && !middleDetected;

        // Draw a rectangle around the area we're looking at, for debugging
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
        // Update the FPS counter to see how slow the vision code is
        // As of October 2022, it runs between 10 and 14 FPS.
        fps = 1000 / time.milliseconds();
        time.reset();

        inputToCr(input);

        if (VisionSubsystem.VisionSubsystemConstants.DEBUG_VIEW) {
            sendBitmap();
        }
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

    // Helper to send the bitmap to the FTC Dashboard
    private void sendBitmap() {
        FtcDashboard db = FtcDashboard.getInstance();
        if (db != null) {
            Bitmap bitmap = Bitmap.createBitmap(img.cols(), img.rows(), Bitmap.Config.RGB_565);
            Utils.matToBitmap(img, bitmap);
            db.sendImage(bitmap);
        }
    }
}