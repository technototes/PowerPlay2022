package org.firstinspires.ftc.forteaching.TechnoBot.Subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.function.Supplier;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;
import com.technototes.library.logger.Log;
import com.technototes.library.util.Alliance;

public class VisionPipeline extends OpenCvPipeline implements Supplier<Integer>, Loggable {

    @Config
    public static class VisionConstants {
        public enum ParkingPosition {
            LEFT,
            CENTER,
            RIGHT,
        }

        // Yellow is at 42.5 (60 degrees)
        static final Scalar YELLOW_A = new Scalar(35, 70, 50);
        static final Scalar YELLOW_B = new Scalar(50, 255, 255);
        // Aqua is at 128 (180 degrees)
        static final Scalar AQUA_A = new Scalar(121, 70, 50);
        static final Scalar AQUA_B = new Scalar(136, 255, 255);
        // Purple is at 212.5 (300 degrees)
        static final Scalar PURPLE_A = new Scalar(205, 70, 50);
        static final Scalar PURPLE_B = new Scalar(220, 255, 255);
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

    private int countColor(Mat input, Scalar edge1, Scalar edge2) {
        Core.inRange(input, edge1, edge2, Cr);
        // TODO: This should maybe do something faster, yes?
        int count = 0;
        for (int i = 0; i < Cr.width(); i++) {
            for (int j = 0; j < Cr.height(); j++) {
                if (Cr.get(j, i)[0] > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public void inputToCr(Mat input) {
        Imgproc.cvtColor(input, customColorSpace, Imgproc.COLOR_RGB2HSV);
        int countY = countColor(customColorSpace, VisionConstants.YELLOW_A, VisionConstants.YELLOW_B);
        int countA = countColor(customColorSpace, VisionConstants.AQUA_A, VisionConstants.AQUA_B);
        int countP = countColor(customColorSpace, VisionConstants.PURPLE_A, VisionConstants.PURPLE_B);
        middleDetected = countA >= countY && countA >= countP;
        rightDetected = countP >= countA && countP >= countY;
        leftDetected = !rightDetected && !middleDetected;
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
