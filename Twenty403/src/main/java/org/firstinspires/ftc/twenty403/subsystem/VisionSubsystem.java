package org.firstinspires.ftc.twenty403.subsystem;

import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;

import org.openftc.easyopencv.OpenCvCameraRotation;

import com.acmerobotics.dashboard.config.Config;

import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.util.Alliance;
import com.technototes.vision.hardware.Webcam;

public class VisionSubsystem implements Subsystem {
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

    public VisionSubsystem(Alliance alliance, StartingPosition side) {
        camera = null;
        visionPipeline = new VisionPipeline(alliance, side);
    }

    public void startStreaming() {
        if (camera != null) {
            camera.startStreaming(
                    VisionSubsystemConstants.WIDTH, VisionSubsystemConstants.HEIGHT, VisionSubsystemConstants.ROTATION);
        }
    }

    public void startVisionPipeline() {
        if (camera != null) {
            camera.setPipeline(visionPipeline);
            camera.openCameraDeviceAsync(this::startStreaming, i -> startVisionPipeline());
        }
    }

    public void stopVisionPipeline() {
        if (camera != null) {
            camera.setPipeline(null);
            camera.closeCameraDeviceAsync(() -> {
                /* Do we need to do anything to stop the vision pipeline? */
            });
        }
    }
}
