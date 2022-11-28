package org.firstinspires.ftc.sixteen750;

import org.firstinspires.ftc.sixteen750.command.autonomous.StartingPosition;
import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.VisionSubsystem;

import com.acmerobotics.dashboard.config.Config;

import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;

public class Robot implements Loggable {
    @Config
    public static class RobotConstant {
        // The only purpose of RobotConstant is to enable/disable subsystem(s) in FTC-Dashboard for non-testing OpMode
        private static boolean SWERVE_DRIVE_ENABLED = false;
        private static boolean TANK_DRIVE_ENABLED = false;
        public static boolean MECANUM_DRIVE_ENABLED = true;
        public static boolean LIFT_ENABLED = true;
        public static boolean ARM_ENABLED = true;
        public static boolean CLAW_ENABLED = true;
        public static boolean CAMERA_ENABLED = true;
    }

    public ClawSubsystem clawSubsystem;
    public ArmSubsystem armSubsystem;
    public LiftSubsystem liftSubsystem;
    public MecanumDriveSubsystem mecanumDriveSubsystem;
    public VisionSubsystem visionSubsystem;

    // T
    public Robot(Hardware hardware, boolean enableMecanumDrive, boolean enableLift, boolean enableArm, boolean enableClaw, boolean enableCamera, Alliance alliance, StartingPosition whichSide) {
        if (enableMecanumDrive) {
            /// Don't forget to check the order of the motors
            mecanumDriveSubsystem = new MecanumDriveSubsystem(hardware.leftFrontMotor, hardware.rightFrontMotor, hardware.leftRearMotor, hardware.rightRearMotor, hardware.imu);
        }

        if (enableLift) {
            liftSubsystem = new LiftSubsystem(hardware.leftLiftMotor, hardware.rightLiftMotor);
        } else {
            liftSubsystem = new LiftSubsystem(null, null);
        }

        if (enableArm) {
            armSubsystem = new ArmSubsystem(hardware.flipperServo, hardware.elbowServo);
        } else {
            armSubsystem = new ArmSubsystem(null, null);
        }

        if (enableClaw) {
            clawSubsystem = new ClawSubsystem(hardware.clawServo, null);
        } else {
            clawSubsystem = new ClawSubsystem(null, null);
        }
        if (enableCamera){
            visionSubsystem = new VisionSubsystem(hardware.camera, alliance, whichSide);
        }
    }

    public Robot(Hardware hardware, Alliance team, StartingPosition whichSide) {
        this(hardware, RobotConstant.MECANUM_DRIVE_ENABLED, RobotConstant.LIFT_ENABLED, RobotConstant.ARM_ENABLED, RobotConstant.CLAW_ENABLED, RobotConstant.CAMERA_ENABLED, team, whichSide);
    }

    public Robot(Hardware hardware){
        this(hardware, Alliance.NONE, StartingPosition.NEUTRAL);
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
