package org.firstinspires.ftc.twenty403;

import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.acmerobotics.dashboard.config.Config;

import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;

public class Robot implements Loggable {
    @Config
    public static class RobotConstant {
        public static boolean DRIVE_CONNECTED = false;
        public static boolean CLAW_CONNECTED = true;
        public static boolean LIFT_CONNECTED = false;
        public static boolean LIFT_MOVE_MOTORS = false;

        public static boolean CAMERA_CONNECTED = false;

        // Are we using 1 or 2 motors?
        public static boolean DUAL_LIFT_SETUP = true;
    }

    public DrivebaseSubsystem drivebaseSubsystem;
    public ClawSubsystem clawSubsystem;
    public LiftSubsystem liftSubsystem;
    public VisionSubsystem visionSystem;
    public double initialVoltage;

    public Robot(Hardware hardware, Alliance team, StartingPosition whichSide) {
        if (RobotConstant.DRIVE_CONNECTED) {
            drivebaseSubsystem = new DrivebaseSubsystem(
                    hardware.flDriveMotor,
                    hardware.frDriveMotor,
                    hardware.rlDriveMotor,
                    hardware.rrDriveMotor,
                    hardware.imu);
        }
        if (RobotConstant.LIFT_CONNECTED) {
            if (RobotConstant.DUAL_LIFT_SETUP) {
                liftSubsystem = new LiftSubsystem(hardware.LiftLeftMotor, hardware.LiftRightMotor, initialVoltage);
            } else {
                liftSubsystem = new LiftSubsystem(hardware.LiftLeftMotor, initialVoltage);
            }
        } else {
            liftSubsystem = new LiftSubsystem();
        }
        if (RobotConstant.CLAW_CONNECTED) {
            clawSubsystem = new ClawSubsystem(liftSubsystem, hardware.claw, hardware.clawDistance, team);
        } else {
            clawSubsystem = new ClawSubsystem();
        }
        if (RobotConstant.CAMERA_CONNECTED) {
            visionSystem = new VisionSubsystem(hardware.camera, team, whichSide);
        } else {
            visionSystem = new VisionSubsystem(team, whichSide);
        }
        // Read the voltage
        initialVoltage = hardware.voltage();
    }
}
