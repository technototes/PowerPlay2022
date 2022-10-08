package org.firstinspires.ftc.sixteen750.command.autonomous;

import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.Function;

public class AutoConstantsRed {
    // Home
    public static Pose2d HOME_START = new Pose2d(36, -66, toRadians(-90));
    public static Pose2d NINEOCLOCK_JUNCTION = new Pose2d(28, 4, toRadians(-135));
    public static Pose2d TWELVEOCLOCK_JUNCTION = new Pose2d(18, -3, toRadians(-135));
    public static Pose2d HOME_STACK = new Pose2d(62, 12, toRadians(0));
    public static Pose2d HOME_LEFT = new Pose2d(60, 36, toRadians(180));
    public static Pose2d HOME_MIDDLE = new Pose2d(36, 36, toRadians(90));
    public static Pose2d HOME_RIGHT = new Pose2d(12, 36, toRadians(90));

    // Away
    public static Pose2d AWAY_START = new Pose2d(-36, 66, toRadians(-90));
    public static Pose2d THREEOCLOCK_JUNCTION = new Pose2d(-28, 4, toRadians(-45));
    public static Pose2d SIXOCLOCK_JUNCTION = new Pose2d(-10, 30, toRadians(-135));
    public static Pose2d AWAY_STACK = new Pose2d(-62, 12, toRadians(180));
    public static Pose2d AWAY_LEFT = new Pose2d(-12, 36, toRadians(90));
    public static Pose2d AWAY_MIDDLE = new Pose2d(-36, 36, toRadians(90));
    public static Pose2d AWAY_RIGHT = new Pose2d(-60, 36, toRadians(0));

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>

            RED_HOME_START_TO_NINEOCLOCK_JUNCTION = b -> b.apply(AutoConstantsRed.HOME_START)
            .splineTo(AutoConstantsRed.NINEOCLOCK_JUNCTION.vec(), AutoConstantsRed.NINEOCLOCK_JUNCTION.getHeading())
            .build(),
            RED_HOME_START_TO_SIXOCLOCK_JUNCTION = b -> b.apply(AutoConstantsRed.HOME_START)
                    .splineTo(AutoConstantsRed.NINEOCLOCK_JUNCTION.vec(), AutoConstantsRed.SIXOCLOCK_JUNCTION.getHeading())
                    .build(),
            RED_NINEOCLOCK_JUNCTION_TO_HOME_STACK = b -> b.apply(AutoConstantsRed.NINEOCLOCK_JUNCTION)
                    .lineToLinearHeading(AutoConstantsRed.HOME_STACK)
                    .build(),
            RED_SIXOCLOCK_JUNCTION_TO_HOME_STACK = b -> b.apply(AutoConstantsRed.SIXOCLOCK_JUNCTION)
                    .lineToLinearHeading(AutoConstantsRed.HOME_STACK)
                    .build(),
            RED_HOME_STACK_TO_NINEOCLOCK_JUNCTION = b -> b.apply(AutoConstantsRed.HOME_STACK)
                    .lineToLinearHeading(AutoConstantsRed.NINEOCLOCK_JUNCTION)
                    .build(),
            RED_HOME_STACK_TO_SIXOCLOCK_JUNCTION = b -> b.apply(AutoConstantsRed.HOME_STACK)
                    .lineToLinearHeading(AutoConstantsRed.SIXOCLOCK_JUNCTION)
                    .build(),
            RED_HOME_NINEOCLOCK_JUNCTION_TO_LEFT = b -> b.apply(AutoConstantsRed.NINEOCLOCK_JUNCTION)
                    .splineTo(AutoConstantsRed.HOME_LEFT.vec(), AutoConstantsRed.HOME_LEFT.getHeading())
                    .build(),
            RED_HOME_NINEOCLOCK_JUNCTION_TO_MIDDLE = b -> b.apply(AutoConstantsRed.NINEOCLOCK_JUNCTION)
                    .splineTo(AutoConstantsRed.HOME_MIDDLE.vec(), AutoConstantsRed.HOME_MIDDLE.getHeading())
                    .build(),
            RED_HOME_NINEOCLOCK_JUNCTION_TO_RIGHT = b -> b.apply(AutoConstantsRed.NINEOCLOCK_JUNCTION)
                    .splineTo(AutoConstantsRed.HOME_RIGHT.vec(), AutoConstantsRed.HOME_RIGHT.getHeading())
                    .build(),
            RED_HOME_SIXOCLOCK_JUNCTION_TO_LEFT = b -> b.apply(AutoConstantsRed.SIXOCLOCK_JUNCTION)
                    .splineTo(AutoConstantsRed.HOME_LEFT.vec(), AutoConstantsRed.HOME_LEFT.getHeading())
                    .build(),
            RED_HOME_SIXOCLOCK_JUNCTION_TO_MIDDLE = b -> b.apply(AutoConstantsRed.SIXOCLOCK_JUNCTION)
                    .splineTo(AutoConstantsRed.HOME_MIDDLE.vec(), AutoConstantsRed.HOME_MIDDLE.getHeading())
                    .build(),
            RED_HOME_SIXCLOCK_JUNCTION_TO_RIGHT = b -> b.apply(AutoConstantsRed.SIXOCLOCK_JUNCTION)
                    .splineTo(AutoConstantsRed.HOME_RIGHT.vec(), AutoConstantsRed.HOME_RIGHT.getHeading())
                    .build(),
            RED_AWAY_START_TO_THREEOCLOCK_JUNCTION = b -> b.apply(AutoConstantsRed.AWAY_START)
                    .splineTo(AutoConstantsRed.THREEOCLOCK_JUNCTION.vec(), AutoConstantsRed.THREEOCLOCK_JUNCTION.getHeading())
                    .build(),
            RED_AWAY_START_TO_SIXOCLOCK_JUNCTION = b -> b.apply(AutoConstantsRed.AWAY_START)
                    .splineTo(AutoConstantsRed.SIXOCLOCK_JUNCTION.vec(), AutoConstantsRed.SIXOCLOCK_JUNCTION.getHeading())
                    .build(),
            RED_THREEOCLOCK_JUNCTION_TO_AWAY_STACK = b -> b.apply(AutoConstantsRed.THREEOCLOCK_JUNCTION)
                    .lineToLinearHeading(AutoConstantsRed.AWAY_STACK)
                    .build(),
            RED_SIXOCLOCK_JUNCTION_TO_AWAY_STACK = b -> b.apply(AutoConstantsRed.SIXOCLOCK_JUNCTION)
                    .lineToLinearHeading(AutoConstantsRed.AWAY_STACK)
                    .build(),
            RED_AWAY_STACK_TO_THREEOCLOCK_JUNCTION = b -> b.apply(AutoConstantsRed.AWAY_STACK)
                    .lineToLinearHeading(AutoConstantsRed.THREEOCLOCK_JUNCTION)
                    .build(),
            RED_AWAY_STACK_TO_SIXOCLOCK_JUNCTION = b -> b.apply(AutoConstantsRed.AWAY_STACK)
                    .lineToLinearHeading(AutoConstantsRed.SIXOCLOCK_JUNCTION)
                    .build(),
            RED_THREEOCLOCK_JUNCTION_AWAY_LEFT = b -> b.apply(AutoConstantsRed.THREEOCLOCK_JUNCTION)
                    .splineTo(AutoConstantsRed.AWAY_LEFT.vec(), AutoConstantsRed.AWAY_LEFT.getHeading())
                    .build(),


}


