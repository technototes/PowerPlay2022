package org.firstinspires.ftc.twenty403.subsystem;

import android.graphics.Bitmap;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;
import java.util.function.Supplier;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

@Config
public class VisionPipeline extends OpenCvPipeline implements Supplier<Integer>, Loggable {

    public enum Mode {
        Signal,
        Junction,
        Inactive,
    }

    public Mode activeMode;
    public Alliance alliance;
    public StartingPosition side;

    public VisionPipeline(Alliance teamAlliance, StartingPosition startSide) {
        super();
        alliance = teamAlliance;
        side = startSide;
        activeMode = Mode.Signal;
    }

    @Config
    public static class VisionConstants {
        @Config
        public static class SignalDetection{
            public enum ParkingPosition {
                LEFT,
                CENTER,
                RIGHT,
            }
            // Yellow is around 25 (50 degrees)
            public static double YELLOW = 30;
            // Aqua is at 100 (200 degrees)
            public static double AQUA = 100;
            // Purple is at 170 (340 degrees)
            public static double PINK = 170;

            // The low saturation point for color identification
            public static double lowS = 70;
            // The high saturation point for color identification
            public static double highS = 255;
            // The low value for color ID
            public static double lowV = 50;
            // The high value for color ID
            public static double highV = 255;
            // The 'range' around the hue that we're looking for
            public static double RANGE = 10;

            // In the 160x120 bitmap, where are we looking?
            public static int X = 165;
            public static int Y = 136;
            public static int WIDTH = 60;
            public static int HEIGHT = 60;

            public static Scalar RGB_HIGHLIGHT = new Scalar(255, 128, 255);
        }
        @Config
        public static class JunctionDetection{
            public static double JYELLOW = 24;
            // Other yellow value?
            public static double JYELLOW2 = 28;
            // the width, in pixels, of a junction
            public static int JUNCTION_WIDTH = 10;

            public static double RANGE = 10;

            // The low saturation point for color identification
            public static double lowS = 60;
            // The high saturation point for color identification
            public static double highS = 255;
            // The low value for color ID
            public static double lowV = 120;
            // The high value for color ID
            public static double highV = 255;

            // What color should we draw the outlining rectangle?
            public static Scalar RGB_HIGHLIGHT = new Scalar(255, 128, 255);
            public static int RED_RANGE = 10;
            public static Scalar RGB_RED = new Scalar(255, 0, 0);
            public static int GREEN_RANGE = 30;
            public static Scalar RGB_GREEN = new Scalar(0, 255, 0);
            public static Scalar RGB_BLACK = new Scalar(0,0,0);
            public static int BLUE_RANGE = 50;
            public static Scalar RGB_BLUE = new Scalar(0, 0, 255);
            public static int YELLOW_RANGE = 70;
            public static Scalar RGB_YELLOW = new Scalar(0, 255, 255);
            public static int AQUA_RANGE = 90;
            public static Scalar RGB_AQUA = new Scalar(255, 255, 0);
            public static int PINK_RANGE = 110;
            public static Scalar RGB_PINK = new Scalar(255, 0, 255);
            public static int PURPLE_RANGE = 130;
            public static Scalar RGB_PURPLE = new Scalar(128, 0, 128);
            public static int WHITE_RANGE = 150;
            public static Scalar RGB_WHITE = new Scalar(255, 255, 255);

            @Config
            public static class OnlyXRight{
                public static double RIGHT_CENTER = 220;
                public static double RIGHT_RANGE = 60;
            }
            @Config
            public static class OnlyXLeft{
                public static double LEFT_CENTER = 200;
                public static double LEFT_RANGE = 80;
            }
            @Config
            public static class OnlyYForward{
                public static double FORWARD_CENTER = 220;
                public static double FORWARD_RANGE = 70;
            }

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

    @LogConfig.Run(duringRun = true, duringInit = true)
    @Log.Number(name = "FPS")
    public volatile double fps = 0.0;

    @LogConfig.Run(duringRun = true, duringInit = true)
    @Log.Number(name = "Junction X")
    public volatile double junctionX = -1;

    @LogConfig.Run(duringRun = true, duringInit = true)
    @Log.Number(name = "Junction Y")
    public volatile double junctionY = -1;

    private ElapsedTime time = new ElapsedTime();

    public Mat customColorSpace = new Mat();
    public Mat Cr = new Mat();
    public Mat img = null;

    private int countColor(double hue) {
        Scalar edge1 = new Scalar(
            hue - VisionConstants.SignalDetection.RANGE,
            VisionConstants.SignalDetection.lowS,
            VisionConstants.SignalDetection.lowV
        );
        Scalar edge2 = new Scalar(
            hue + VisionConstants.SignalDetection.RANGE,
            VisionConstants.SignalDetection.highS,
            VisionConstants.SignalDetection.highV
        );
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
                        img.put(j + VisionConstants.SignalDetection.Y, i + VisionConstants.SignalDetection.X, colorToDraw);
                    }
                }
            }
        }
        return count;
    }

    public void detectSignal(Mat input) {
        // Put the input matrix in a member variable, so that other functions can draw on it
        img = input;

        // First, slice the smaller rectangle out of the overall bitmap:
        Mat rectToLookAt = input.submat(
            // Row start to Row end
            VisionConstants.SignalDetection.Y,
            VisionConstants.SignalDetection.Y + VisionConstants.SignalDetection.HEIGHT,
            // Col start to Col end
            VisionConstants.SignalDetection.X,
            VisionConstants.SignalDetection.X + VisionConstants.SignalDetection.WIDTH
        );

        // Next, convert the RGB image to HSV, because HUE is much easier to identify colors in
        // The output is in 'customColorSpace'
        Imgproc.cvtColor(rectToLookAt, customColorSpace, Imgproc.COLOR_RGB2HSV);

        // Check to see which colors occur:
        int countY = countColor(VisionConstants.SignalDetection.YELLOW);
        int countA = countColor(VisionConstants.SignalDetection.AQUA);
        int countP = countColor(VisionConstants.SignalDetection.PINK);

        // Check which spot we should park in
        middleDetected = countA >= countY && countA >= countP;
        rightDetected = countP >= countA && countP >= countY;
        leftDetected = !rightDetected && !middleDetected;

        // Draw a rectangle around the area we're looking at, for debugging
        int x = Range.clip(VisionConstants.SignalDetection.X - 1, 0, input.width() - 1);
        int y = Range.clip(VisionConstants.SignalDetection.Y - 1, 0, input.height() - 1);
        int w = Range.clip(VisionConstants.SignalDetection.WIDTH + 2, 1, input.width() - x);
        int h = Range.clip(VisionConstants.SignalDetection.HEIGHT + 2, 1, input.height() - y);
        Imgproc.rectangle(input, new Rect(x, y, w, h), VisionConstants.SignalDetection.RGB_HIGHLIGHT);
    }

    public void init(Mat firstFrame) {
        detectSignal(firstFrame);
    }

    private static boolean brightEnough(double[] color) {
        return (
            color[1] > VisionConstants.JunctionDetection.lowS &&
            color[1] < VisionConstants.JunctionDetection.highS &&
            color[2] > VisionConstants.JunctionDetection.lowV &&
            color[2] < VisionConstants.JunctionDetection.highV
        );
    }

    private static boolean inRange(double[] color, double target) {
        return (
            color[0] <= target + VisionConstants.JunctionDetection.RANGE &&
                    color[0] >= target - VisionConstants.JunctionDetection.RANGE
        );
    }

    public void detectJunction(Mat frame) {
        Imgproc.rectangle(img, new Rect(2, 2, 7, 9), VisionConstants.JunctionDetection.RGB_GREEN);
        Imgproc.cvtColor(frame, customColorSpace, Imgproc.COLOR_RGB2HSV);
        int startX = -1;
        int endX = -1;
        for (int j = customColorSpace.height() - 1; j >= 0; j--) {
            for (int i = 0; i < customColorSpace.width(); i++) {
                double[] color = customColorSpace.get(j, i);
                if (
                    brightEnough(color) &&
                    (inRange(color, VisionConstants.JunctionDetection.JYELLOW) || inRange(color, VisionConstants.JunctionDetection.JYELLOW2))
                ) {
                    if (startX == -1) {
                        startX = i;
                    } else {
                        endX = i;
                    }
                    img.put(j, i, VisionConstants.JunctionDetection.RGB_BLACK.val);
                    // Draw a dot on the image at this point - input was put into img
                    // The color choice makes things stripey, which makes it easier to identif
                    // if less than 20 for range after not seeing yellow than set both to -1 as not junction ypou are
                    // looking for
                } else {
                    if (startX != -1 && (Math.abs(startX - endX) < VisionConstants.JunctionDetection.JUNCTION_WIDTH) ||
                            endX == -1) {
                        startX = -1;
                        endX = -1;
                    }
                    // Debug some stuff:
                    if (brightEnough(color)) {
                        // Let's draw some colors to help identify the right range
                        if (inRange(color, VisionConstants.JunctionDetection.RED_RANGE)) {
                            img.put(j, i, VisionConstants.JunctionDetection.RGB_RED.val);
                        }
                        if (inRange(color, VisionConstants.JunctionDetection.GREEN_RANGE)) {
                            img.put(j, i, VisionConstants.JunctionDetection.RGB_GREEN.val);
                        }
                        if (inRange(color, VisionConstants.JunctionDetection.BLUE_RANGE)) {
                            img.put(j, i, VisionConstants.JunctionDetection.RGB_BLUE.val);
                        }
                        if (inRange(color, VisionConstants.JunctionDetection.YELLOW_RANGE)) {
                            img.put(j, i, VisionConstants.JunctionDetection.RGB_YELLOW.val);
                        }
                        if (inRange(color, VisionConstants.JunctionDetection.AQUA_RANGE)) {
                            img.put(j, i, VisionConstants.JunctionDetection.RGB_AQUA.val);
                        }
                        if (inRange(color, VisionConstants.JunctionDetection.PINK_RANGE)) {
                            img.put(j, i, VisionConstants.JunctionDetection.RGB_PINK.val);
                        }
                        if (inRange(color, VisionConstants.JunctionDetection.PURPLE_RANGE)) {
                            img.put(j, i, VisionConstants.JunctionDetection.RGB_PURPLE.val);
                        }
                        if (inRange(color, VisionConstants.JunctionDetection.WHITE_RANGE)) {
                            img.put(j, i, VisionConstants.JunctionDetection.RGB_WHITE.val);
                        }
                    }
                }
            }

            if (startX != -1 && endX != -1) {
                // Check to see if the width of the range in the color is 'wide enough' to be a junction
                if (Math.abs(startX - endX) > VisionConstants.JunctionDetection.JUNCTION_WIDTH) {
                    junctionY = j;
                    junctionX = (startX + endX) / 2;
                    Imgproc.rectangle(
                        img,
                        new Rect((int) junctionX, (int) junctionY, 5, 5),
                        VisionConstants.JunctionDetection.RGB_HIGHLIGHT
                    );
                    return;
                }
            }
        }
        junctionX = -1;
        junctionY = -1;
    }

    @Override
    public Mat processFrame(Mat input) {
        // Update the FPS counter to see how slow the vision code is
        // As of October 2022, it runs between 10 and 14 FPS.
        fps = 1000 / time.milliseconds();
        time.reset();

        switch (activeMode) {
            case Signal:
                detectSignal(input);
                break;
            case Junction:
                detectJunction(input);
                break;
            case Inactive:
            default:
                return input;
        }
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

    public double getJunctionX() {
        return junctionX;
    }

    public double getJunctionY() {
        return junctionY;
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
