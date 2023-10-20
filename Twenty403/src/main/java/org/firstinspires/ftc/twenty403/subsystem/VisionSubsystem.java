package org.firstinspires.ftc.twenty403.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;
import com.technototes.vision.HSVRange;
import com.technototes.vision.hardware.Camera;
import com.technototes.vision.subsystem.BasicVisionSubsystem;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class VisionSubsystem extends BasicVisionSubsystem implements Loggable {

    @Config
    public static class VisionConstants {

        public static int CAM_WIDTH = 320;
        public static int CAM_HEIGHT = 240;
        public static OpenCvCameraRotation ROTATION = OpenCvCameraRotation.UPRIGHT;

        public static int CYAN = 100;
        public static int YELLOW = 30;
        public static int MAGENTA = 170;

        public static int HUE_RANGE = 10;
        public static int SAT_LO = 70;
        public static int SAT_HI = 255;
        public static int VAL_LO = 50;
        public static int VAL_HI = 255;
    }

    public static class Rects {

        @Config
        public static class Red {

            public static int RX = 10;
            public static int RY = 20;
            public static int RW = 25;
            public static int RH = 25;

            public static int LX = 120;
            public static int LY = 110;
            public static int LW = 25;
            public static int LH = 25;
        }

        @Config
        public static class Blue {

            public static int RX = 15;
            public static int RY = 25;
            public static int RW = 25;
            public static int RH = 25;

            public static int LX = 100;
            public static int LY = 90;
            public static int LW = 25;
            public static int LH = 25;
        }
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
    StartingPosition side;
    Rect[] rects;

    // Here's the commend to use...
    public final VisCommand runVision;

    public VisionSubsystem(Camera c, Alliance a, StartingPosition s) {
        // Initialize the underlying subsystem and vision pipeline
        super(c, VisionConstants.CAM_WIDTH, VisionConstants.CAM_HEIGHT, VisionConstants.ROTATION);
        alliance = a;
        side = s;
        rects = new Rect[4];
        rects[0] = new Rect(Rects.Red.RX, Rects.Red.RY, Rects.Red.RW, Rects.Red.RH);
        rects[1] = new Rect(Rects.Red.LX, Rects.Red.LY, Rects.Red.LW, Rects.Red.LH);
        rects[2] = new Rect(Rects.Blue.RX, Rects.Blue.RY, Rects.Blue.RW, Rects.Blue.RH);
        rects[3] = new Rect(Rects.Blue.LX, Rects.Blue.LY, Rects.Blue.LW, Rects.Blue.LH);
        runVision = new VisCommand();
    }

    @Override
    protected void detectionStart() {
        // For PowerPlay, we're only looking at 1 spot on the camera,
        // so we don't really need to do anything here
        // But I'm updating the rectangles in real-time here, so they get updated by the
        // FTC Dashboard in real time...
        rects[0].x = Rects.Red.RX;
        rects[0].y = Rects.Red.RY;
        rects[0].width = Rects.Red.RW;
        rects[0].height = Rects.Red.RH;
        rects[1].x = Rects.Red.LX;
        rects[1].y = Rects.Red.LY;
        rects[1].width = Rects.Red.LW;
        rects[1].height = Rects.Red.LH;
        rects[2].x = Rects.Blue.RX;
        rects[2].y = Rects.Blue.RY;
        rects[2].width = Rects.Blue.RW;
        rects[2].height = Rects.Blue.RH;
        rects[3].x = Rects.Blue.LX;
        rects[3].y = Rects.Blue.LY;
        rects[3].width = Rects.Blue.LW;
        rects[3].height = Rects.Blue.LH;
    }

    @Override
    public int numRectangles() {
        return 4;
    }

    private int getRectNum() {
        if (alliance == Alliance.RED) {
            return (side == StartingPosition.RIGHT) ? 0 : 1;
        } else {
            return (side == StartingPosition.RIGHT) ? 2 : 3;
        }
    }

    @Override
    public Rect getRect(int rectNumber) {
        // In PowerPlay, we only have 1 rectangle, but we need to know if we're running red or blue
        return rects[rectNumber];
    }

    @Override
    protected void detectionEnd() {
        // Again, only looking at one rectangle, so we don't have any post-processing to do...
    }

    @Override
    public void runDetection(Mat inputHSV, int rectNumber) {
        // We only run detection for the current alliance/side
        if (rectNumber != getRectNum()) {
            return;
        }
        // These are the color ranges we're looking for
        HSVRange cyan = new HSVRange(
            VisionConstants.CYAN,
            VisionConstants.HUE_RANGE,
            VisionConstants.SAT_LO,
            VisionConstants.SAT_HI,
            VisionConstants.VAL_LO,
            VisionConstants.VAL_HI
        );
        HSVRange yellow = cyan.newHue(VisionConstants.YELLOW, VisionConstants.HUE_RANGE);
        HSVRange magenta = cyan.newHue(VisionConstants.MAGENTA, VisionConstants.HUE_RANGE);

        // Now, count the # of pixels of each color in the rectangle
        int xo = rects[rectNumber].x;
        int yo = rects[rectNumber].y;
        int cyanCount = countPixelsOfColor(cyan, inputHSV, this.curFrameRGB, xo, yo);
        int yellowCount = countPixelsOfColor(yellow, inputHSV, this.curFrameRGB, xo, yo);
        int magentaCount = countPixelsOfColor(magenta, inputHSV, this.curFrameRGB, xo, yo);

        // We could save these values in member variables, then check them in 'detectionEnd'
        // but since we're only processing a single rectangle, why bother?
        leftDetected = cyanCount > yellowCount && cyanCount > magentaCount;
        middleDetected = yellowCount > cyanCount && yellowCount > magentaCount;
        rightDetected = !leftDetected && !middleDetected;
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

    public class VisCommand implements com.technototes.library.command.Command {

        public VisCommand() {
            addRequirements(VisionSubsystem.this);
        }

        @Override
        public void initialize() {
            startVisionPipeline();
        }

        @Override
        public void execute() {}

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public void end(boolean cancel) {
            stopVisionPipeline();
        }
    }
}
