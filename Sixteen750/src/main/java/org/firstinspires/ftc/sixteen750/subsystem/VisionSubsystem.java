package org.firstinspires.ftc.sixteen750.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.util.Alliance;
import com.technototes.vision.hardware.Webcam;

import org.firstinspires.ftc.sixteen750.command.autonomous.StartingPosition;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class VisionSubsystem implements Subsystem, Loggable {
    @Config
    public static class VisionSubsystemConstants {
        // This is a super-low res image. I don't think we need higher resolution...
        // Note: This may be too small for the older camera.
        // I think it only goes down to 320 x 240
        public static int WIDTH = 320;
        public static int HEIGHT = 240;
        // Change this if the camera is oriented differently
        public static OpenCvCameraRotation ROTATION = OpenCvCameraRotation.UPRIGHT;
        // Turn this on if we want to see the debug image
        public static boolean DEBUG_VIEW = true;
    }

    public Webcam camera;
    public VisionPipeline visionPipeline;

    public VisionSubsystem(Webcam c, Alliance alliance, StartingPosition side) {
        camera = c;
        visionPipeline = new VisionPipeline(alliance, side);
    }

    public void startStreaming() {
        camera.startStreaming(
                VisionSubsystemConstants.WIDTH, VisionSubsystemConstants.HEIGHT, VisionSubsystemConstants.ROTATION);
    }
    public void pauseScanning() {
        visionPipeline.activeMode = VisionPipeline.Mode.Inactive;
    }

    public void startJunctionScanning() {
        visionPipeline.activeMode = VisionPipeline.Mode.Junction;
    }

    public void startVisionPipeline() {
        camera.setPipeline(visionPipeline);
        camera.openCameraDeviceAsync(this::startStreaming, i -> startVisionPipeline());
    }

    public void stopVisionPipeline() {
        camera.setPipeline(null);
        camera.closeCameraDeviceAsync(() -> {
            /* Do we need to do anything to stop the vision pipeline? */
        });
    }
}
