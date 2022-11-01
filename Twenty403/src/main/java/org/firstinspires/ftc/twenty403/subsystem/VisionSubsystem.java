package org.firstinspires.ftc.twenty403.subsystem;

import org.openftc.easyopencv.OpenCvCameraRotation;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;

import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.vision.hardware.Webcam;

public class VisionSubsystem implements Subsystem, Loggable {
    @Config
    public static class VisionSubsystemConstants {
        // This is a super-low res image. I don't think we need higher resolution...
        public static int WIDTH = 160;
        public static int HEIGHT = 120;
        // Change this if the camera is oriented differently
        public static OpenCvCameraRotation ROTATION = OpenCvCameraRotation.UPRIGHT;
    }

    public Webcam camera;
    public VisionPipeline visionPipeline;

    public VisionSubsystem(Webcam c) {
        camera = c;
        visionPipeline = new VisionPipeline();
    }

    public void startStreaming() {
        camera.startStreaming(
                VisionSubsystemConstants.WIDTH, VisionSubsystemConstants.HEIGHT, VisionSubsystemConstants.ROTATION);
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
