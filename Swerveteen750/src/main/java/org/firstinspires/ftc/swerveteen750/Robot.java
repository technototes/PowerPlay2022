package org.firstinspires.ftc.swerveteen750;

import org.firstinspires.ftc.swerveteen750.command.autonomous.StartingPosition;
import org.firstinspires.ftc.swerveteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.MecanumDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.SwerveDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.VisionSubsystem;

import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;

public class Robot implements Loggable {
    @Config
    public static class RobotConstant {
        // The only purpose of RobotConstant is to enable/disable subsystem(s) in FTC-Dashboard for non-testing OpMode
        public static boolean SWERVE_DRIVE_ENABLED = true;
        private static boolean TANK_DRIVE_ENABLED = false;
        public static boolean MECANUM_DRIVE_ENABLED = false;
        public static boolean LIFT_ENABLED = true;
        public static boolean CLAW_ENABLED = true;
        public static boolean CAMERA_ENABLED = false;
    }

    public ConfigurableSwerveDriveSubsystem swerveDriveSubsystem;
    public MecanumDriveSubsystem mecanumDriveSubsystem;
    public LiftSubsystem liftSubsystem;
    public ClawSubsystem clawSubsystem;
    public VisionSubsystem visionSubsystem;

    public Robot(HardwareMap hardwareMap,
                 Hardware hardware,
                 boolean enableSwerveDrive,
                 boolean enableMecanumDrive,
                 boolean enableLift,
                 boolean enableClaw,
                 boolean enableCamera,
                 Alliance alliance,
                 StartingPosition whichSide
    ) {
        if (enableMecanumDrive) {
            /// Don't forget to check the order of the motors
            mecanumDriveSubsystem = new MecanumDriveSubsystem(
                    hardware.leftFrontMotorT,
                    hardware.rightFrontMotorT,
                    hardware.leftRearMotorT,
                    hardware.rightRearMotorT,
                    hardware.imu
            );
        } else if (enableSwerveDrive) {
            // Hint: we never put the SwerveDriveSubsystem in the Robot class
            swerveDriveSubsystem = new ConfigurableSwerveDriveSubsystem(hardwareMap); // much simpler
        }

        if (enableLift) {
            liftSubsystem = new LiftSubsystem(hardware.leftLiftMotor, hardware.turretServo, hardware::getVoltage);
        } else {
            liftSubsystem = new LiftSubsystem(null, null);
        }

        if (enableClaw) {
            clawSubsystem = new ClawSubsystem(hardware.clawServo, null, alliance);
            if (enableLift) {
                clawSubsystem.liftSubsystem = liftSubsystem;
            }
        } else {
            clawSubsystem = new ClawSubsystem(null);
        }
        if (enableCamera) {
            visionSubsystem = new VisionSubsystem(hardware.camera, alliance, whichSide);
        }
    }

    public enum SubsystemCombo {
        DEFAULT,
        S_DRIVE_ONLY,
        M_DRIVE_ONLY,
        LIFT_ONLY,
        CLAW_ONLY,
        VISION_ONLY,
        VISION_M_DRIVE,
    }

    public Robot(HardwareMap hardwareMap, Hardware hardware, SubsystemCombo combo, Alliance team, StartingPosition whichSide) {
        this(
                hardwareMap,
                hardware,
                combo == SubsystemCombo.DEFAULT ? RobotConstant.SWERVE_DRIVE_ENABLED : combo == SubsystemCombo.S_DRIVE_ONLY,
                combo == SubsystemCombo.DEFAULT ? RobotConstant.MECANUM_DRIVE_ENABLED : combo == SubsystemCombo.M_DRIVE_ONLY || combo == SubsystemCombo.VISION_M_DRIVE,
                combo == SubsystemCombo.DEFAULT ? RobotConstant.LIFT_ENABLED : combo == SubsystemCombo.LIFT_ONLY,
                combo == SubsystemCombo.DEFAULT ? RobotConstant.CLAW_ENABLED : combo == SubsystemCombo.CLAW_ONLY,
                combo == SubsystemCombo.DEFAULT ? RobotConstant.CAMERA_ENABLED : combo == SubsystemCombo.VISION_ONLY || combo == SubsystemCombo.VISION_M_DRIVE,
                team,
                whichSide
        );
    }
}
/*
Robot is 12 inches

BLUE_HOME
Starting pos: x= 36 y = -66 Angle: 90
Junction: Angle: 135   x=28  y=-4
Stack Home: x= 62   y= -12   Angle= 0
Park Left: x= 12   y= -36   Angle= -90
Park Middle: Angle = -90 x= 36 y= -36
Park Right: Angle = 180 x= 60  y= -36

BLUE_AWAY
Starting pos: x=-36 y= -66 Angle: 90
Junction: x=-28 y=-4 Angle: 45
Stack Away: x= -62 y=-12 Angle: 180
Park Left:  x=- 60 y= -36 Angle= 0
Park Middle: Angle= -90 x= -36 y= -36
Park Right: x= -12 y= -36 Angle= -90

Blue Center Junction:

RED HOME
Starting pos: x= 36 y= 66 Angle: -90
Junction: x= 28 y= 4 Angle: -135
Stack Home: x= 62 y= 12 Angle: 0
Park Left: x= 60 y= 36 Angle: 180
Park Middle: x= 36 y= 36 Angle: 90
Park Right: x= 12 y= 36 Angle: 90

RED AWAY
Starting pos: x= -36 y= 66 Angle: -90
Junction: x= -28 y= 4 Angle: -45
Stack Away: x= -62 y= 12 Angle: 180
Park Left: y= 36 x= -12 Angle: 90
Park Middle: y= 36 x= -36 Angle 90
Park Right: y= 36 x= -60 Angle: 0

Red Center Junction:
 */
