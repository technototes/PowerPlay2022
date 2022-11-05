package org.firstinspires.ftc.sixteen750;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.TankDrivebaseSubsystem;

import com.acmerobotics.dashboard.config.Config;

import com.technototes.library.logger.Loggable;

public class Robot implements Loggable {
    @Config
    public static class RobotConstant {
        public static boolean SWERVE_DRIVE_CONNECTED = false;
        public static boolean TANK_DRIVE_CONNECTED = false;
        public static boolean CLAW_CONNECTED = true;
        public static boolean LIFT_CONNECTED = false;
    }

//    public TankDrivebaseSubsystem tankDriveSubsystem;
    public ClawSubsystem clawSubsystem;
    public LiftSubsystem liftSubsystem;

    public Robot(Hardware hardware) {
        if (RobotConstant.SWERVE_DRIVE_CONNECTED) {
            //            driveBaseSubsystem(
            //                    hardware.flDriveMotor,
            //                    hardware.frDriveMotor,
            //                    hardware.rlDriveMotor,
            //                    hardware.rrDriveMotor,
            //                    hardware.imu)
            //            )
        }
        if (RobotConstant.TANK_DRIVE_CONNECTED) {

        }
        if (RobotConstant.CLAW_CONNECTED) {
            clawSubsystem = new ClawSubsystem(hardware.clawServo, hardware.flipperServo, hardware.elbowServo);
        } else {
            clawSubsystem = new ClawSubsystem();
        }

        if (RobotConstant.LIFT_CONNECTED) {
            // TODO: enable both motor as needed
//          liftSubsystem = new LiftSubsystem(hardware.liftLeftMotor, hardware.liftRightMotor);
            liftSubsystem = new LiftSubsystem(hardware.liftLeftMotor, null);
        } else {
            liftSubsystem = new LiftSubsystem(null, null);
        }
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
