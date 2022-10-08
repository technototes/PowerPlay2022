package org.firstinspires.ftc.twenty403;

import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.acmerobotics.dashboard.config.Config;

import com.technototes.library.logger.Loggable;

public class Robot implements Loggable {
    @Config
    public static class RobotConstant {
        public static boolean DRIVE_CONNECTED = true;
        public static boolean CONE_CONNECTED = true;
        public static boolean CLAW_CONNECTED = true;
        public static boolean LIFT_CONNECTED = true;
    }

    public DrivebaseSubsystem drivebaseSubsystem;
    public ClawSubsystem clawSubsystem;
    public LiftSubsystem liftSubsystem;
    public ConeSubsystem coneSubsystem;

    public Robot(Hardware hardware) {
        if (RobotConstant.DRIVE_CONNECTED) {
            drivebaseSubsystem = new DrivebaseSubsystem(
                    hardware.flDriveMotor,
                    hardware.frDriveMotor,
                    hardware.rlDriveMotor,
                    hardware.rrDriveMotor,
                    hardware.imu);
        }
        if (RobotConstant.CONE_CONNECTED) {
            coneSubsystem = new ConeSubsystem();
        }

    }
}
