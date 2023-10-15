package org.firstinspires.ftc.twenty403.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;
import com.technototes.vision.HSVRange;
import com.technototes.vision.hardware.Camera;
import com.technototes.vision.subsystem.BasicVisionSubsystem;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class NewVisionSubsystem extends BasicVisionSubsystem implements Loggable {

    @Config
    public static class VisionConstants {
        public static int RX = 10;
        public static int RY = 20;
        public static int RW = 25;
        public static int RH = 25;

        public static int BX = 120;
        public static int BY = 110;
        public static int BW = 25;
        public static int BH = 25;

        public static int CYAN = 80;
        public static int YELLOW = 120;
        public static int MAGENTA = 170;

        public static int HUE_RANGE = 20;
        public static int SAT_LO = 50;
        public static int SAT_HI = 255;
        public static int VAL_LO = 50;
        public static int VAL_HI = 255;
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

    Alliance alliance;

    public NewVisionSubsystem(Camera c, int w, int h, OpenCvCameraRotation rot, Alliance a) {
        super( c,  w,  h,  rot);
        alliance = a;
    }

    @Override
    public int numRectangles() {
        return 1;
    }

    @Override
    public Rect getRect(int rectNumber) {
        // In PowerPlay, we only have 1 rectangle, but we need to know if we're running red or blue
        switch (alliance) {
            case RED:
                return new Rect(VisionConstants.RX, VisionConstants.RY, VisionConstants.RW, VisionConstants.RH);
            case BLUE:
            case NONE:
            default:
                return new Rect(VisionConstants.BX, VisionConstants.BY, VisionConstants.BW, VisionConstants.BH);
        }
    }

    /*
    @Override
    protected void detectionStart() {
        // For PowerPlay, we're only looking at 1 spot on the camera,
        // so we don't need to do anything here
    }

    @Override
    protected void detectionEnd() {
        // Again, only looking at one rectangle, so we're golden...
    }
    */

    @Override
    public void runDetection(Mat inputHSV, int rectNumber) {

        // These values are used to draw things on the camera monitor
        int xo = alliance == Alliance.RED ? VisionConstants.RX : VisionConstants.BX;
        int yo = alliance == Alliance.RED ? VisionConstants.RY : VisionConstants.BY;

        // These are the color ranges we're looking for
        HSVRange cyan = new HSVRange(VisionConstants.CYAN, VisionConstants.HUE_RANGE, VisionConstants.SAT_LO, VisionConstants.SAT_HI, VisionConstants.VAL_LO, VisionConstants.VAL_HI);
        HSVRange yellow = cyan.newHue(VisionConstants.YELLOW, VisionConstants.HUE_RANGE);
        HSVRange magenta = cyan.newHue(VisionConstants.MAGENTA, VisionConstants.HUE_RANGE);

        // Now, count the # of pixels of each color in the rectangle
        int cyanCount = countPixelsOfColor(cyan, inputHSV, this.curFrameRGB, xo, yo);
        int yellowCount = countPixelsOfColor(yellow, inputHSV, this.curFrameRGB, xo, yo);
        int magentaCount = countPixelsOfColor(magenta, inputHSV, this.curFrameRGB, xo, yo);

        // We could save these values in member variables, then check them in 'detectionEnd'
        // but since we're only processing a single rectangle, why bother?
        if (cyanCount > yellowCount && cyanCount > magentaCount) {
            leftDetected = true;
        } else if (yellowCount > cyanCount && yellowCount > magentaCount) {
            middleDetected = true;
        } else {
            // We could check, but we don't need to...
            rightDetected = true;
        }
    }

    // We need to expose these values as functions for use by a ChoiceCommand
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
