package org.firstinspires.ftc.twenty403;

import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.acmerobotics.dashboard.config.Config;

import com.technototes.library.logger.Loggable;

public class Robot implements Loggable {
    @Config
    public static class RobotConstant {
        public static boolean DRIVE_CONNECTED = true;
    }

    public DrivebaseSubsystem drivebaseSubsystem;

    public Robot(Hardware hardware) {
        if (RobotConstant.DRIVE_CONNECTED)
            drivebaseSubsystem = new DrivebaseSubsystem(
                    hardware.flDriveMotor,
                    hardware.frDriveMotor,
                    hardware.rlDriveMotor,
                    hardware.rrDriveMotor,
                    hardware.imu);
    }
}
