package org.firstinspires.ftc.sixteen750;

import java.util.ArrayList;

import com.acmerobotics.dashboard.config.Config;

import com.technototes.library.logger.Loggable;
import com.technototes.path.trajectorysequence.TrajectorySequence;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

public class Robot implements Loggable {
    @Config
    public static class RobotConstant {
        public static boolean DRIVE_CONNECTED = true;
        public static boolean CLAW_CONNECTED = true;
        public static boolean LIFT_CONNECTED = true;
    }

    //public DriveBaseSubsystem driveBaseSubsystem;
    public ClawSubsystem clawSubsystem;
    public LiftSubsystem liftSubsystem;

    // TODO: Move this stuff into the AutoConstantsBlue file
    // and structure it the same as the AutoConstantsRed file
    public static class Trajectories {
        public static TrajectorySequence BLUE_AWAY_STACK = new TrajectorySequence(new ArrayList<>());
        public static TrajectorySequence BLUE_HOME_STACK = new TrajectorySequence(new ArrayList<>());
        public static TrajectorySequence BLUE_HIGH_JUNCTION_HOME = new TrajectorySequence(new ArrayList<>());
        public static TrajectorySequence BLUE_HIGH_JUNCTION_AWAY = new TrajectorySequence(new ArrayList<>());
        public static TrajectorySequence BLUE_PARK_LOCATION_HOME = new TrajectorySequence(new ArrayList<>());
        public static TrajectorySequence BLUE_PARK_LOCATION_AWAY = new TrajectorySequence(new ArrayList<>());
        public static TrajectorySequence BLUE_HIGH_JUNCTION_CENTER = new TrajectorySequence(new ArrayList<>());
        public static TrajectorySequence RED_HIGH_JUNCTION_AWAY = new TrajectorySequence(new ArrayList<>());
        public static TrajectorySequence RED_PARK_LOCATION_AWAY = new TrajectorySequence(new ArrayList<>());
    }

    public Robot(Hardware hardware) {
        if (RobotConstant.DRIVE_CONNECTED) {
//            driveBaseSubsystem(
//                    hardware.flDriveMotor,
//                    hardware.frDriveMotor,
//                    hardware.rlDriveMotor,
//                    hardware.rrDriveMotor,
//                    hardware.imu)
//            )
        }
        if (RobotConstant.CLAW_CONNECTED) {
            clawSubsystem = new ClawSubsystem(hardware.claw, hardware.flipper, hardware. clawDistance);
        }

        if (RobotConstant.LIFT_CONNECTED) {
            liftSubsystem = new LiftSubsystem(hardware.LiftLeftMotor, hardware.LiftRightMotor);
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
