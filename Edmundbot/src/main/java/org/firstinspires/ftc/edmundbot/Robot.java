package org.firstinspires.ftc.edmundbot;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.edmundbot.command.autonomous.StartingPosition;
import org.firstinspires.ftc.edmundbot.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.edmundbot.subsystem.OdoSubsystem;
import org.firstinspires.ftc.edmundbot.subsystem.ShooterSubsystem;
import org.firstinspires.ftc.edmundbot.subsystem.VisionSubsystem;

public class Robot implements Loggable {

    @Config
    public static class RobotConstant {

        public static boolean DRIVE_CONNECTED = true;
        public static boolean SHOOTER_CONNECTED = true;
        public static boolean ODO_SENSORS_CONNECTED = false;

        public static boolean CAMERA_CONNECTED = false;
    }

    public DrivebaseSubsystem drivebaseSubsystem;
    public ShooterSubsystem shooterSubsystem;
    public VisionSubsystem visionSystem;
    public OdoSubsystem odoSubsystem;
    public double initialVoltage;

    public Robot(Hardware hardware, Alliance team, StartingPosition whichSide) {
        if (RobotConstant.ODO_SENSORS_CONNECTED) {
            odoSubsystem =
                    new OdoSubsystem(
                            hardware.leftdis,
                            hardware.rightdis,
                            hardware.colorleft,
                            hardware.colorcenter,
                            hardware.colorright
                    );
        } else {
            odoSubsystem = new OdoSubsystem();
        }
        if (RobotConstant.DRIVE_CONNECTED) {
            drivebaseSubsystem =
                    new DrivebaseSubsystem(
                            hardware.flDriveMotor,
                            hardware.frDriveMotor,
                            hardware.rlDriveMotor,
                            hardware.rrDriveMotor,
                            hardware.imu,
                            odoSubsystem
                    );
        }
        if (RobotConstant.CAMERA_CONNECTED) {
            visionSystem = new VisionSubsystem(hardware.camera, team, whichSide);
        }
        if (RobotConstant.SHOOTER_CONNECTED) {
            shooterSubsystem =
                    new ShooterSubsystem(
                            hardware.leftShooterMotor,
                            hardware.rightShooterMotor
                    );
        } else {
            shooterSubsystem = new ShooterSubsystem(null, null);
        }
        // Read the voltage
        initialVoltage = hardware.voltage();
    }
}
