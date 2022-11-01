package org.firstinspires.ftc.twenty403;

import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.acmerobotics.dashboard.config.Config;

import com.technototes.library.logger.Loggable;

public class Robot implements Loggable {
    @Config
    public static class RobotConstant {
        public static boolean DRIVE_CONNECTED = true;
        public static boolean CLAW_CONNECTED = false;
        public static boolean LIFT_CONNECTED = false;
        public static boolean DUAL_LIFT_SETUP = false;
    }

    public DrivebaseSubsystem drivebaseSubsystem;
    public ClawSubsystem clawSubsystem;
    public LiftSubsystem liftSubsystem;

    public Robot(Hardware hardware) {
        if (RobotConstant.DRIVE_CONNECTED) {
            drivebaseSubsystem = new DrivebaseSubsystem(
                    hardware.flDriveMotor,
                    hardware.frDriveMotor,
                    hardware.rlDriveMotor,
                    hardware.rrDriveMotor,
                    hardware.imu);
        }
        if (RobotConstant.CLAW_CONNECTED) {
            clawSubsystem = new ClawSubsystem(hardware.claw, hardware.flipper, hardware.clawDistance);
        } else {
            clawSubsystem = new ClawSubsystem();
        }
        if (RobotConstant.LIFT_CONNECTED) {
            if (RobotConstant.DUAL_LIFT_SETUP) {
                liftSubsystem = new LiftSubsystem(hardware.LiftLeftMotor, hardware.LiftRightMotor);
            } else {
                liftSubsystem = new LiftSubsystem(hardware.LiftLeftMotor);
            }
        } else {
            liftSubsystem = new LiftSubsystem();
        }
    }
}
