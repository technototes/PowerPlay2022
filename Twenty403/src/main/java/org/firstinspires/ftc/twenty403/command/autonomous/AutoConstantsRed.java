package org.firstinspires.ftc.twenty403.command.autonomous;

import static java.lang.Math.toRadians;

import java.util.function.Function;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

public class AutoConstantsRed {
    // "Home" locations: (The side with the Red terminal)
    public static class Home {
        public static Pose2d START = new Pose2d(36, -66, toRadians(-90));
        public static Pose2d STACK = new Pose2d(62, 12, toRadians(0));
        public static Pose2d LEFT = new Pose2d(60, 36, toRadians(180));
        public static Pose2d MIDDLE = new Pose2d(36, 36, toRadians(90));
        public static Pose2d RIGHT = new Pose2d(12, 36, toRadians(90));
        // These have "home/away" modifiers, because we want to stay on "our side" during auto
        // 12 O'Clock is on the Blue side: Probably stay away
        public static Pose2d TWELVEOCLOCK_JUNCTION = new Pose2d(18, -3, toRadians(-135));
        // 3 O'Clock is on the Away side, so only use if we know our alliance partner won't be
        // in the way
        public static Pose2d THREEOCLOCK_JUNCTION = new Pose2d(-28, 4, toRadians(-45));
        public static Pose2d SIXOCLOCK_JUNCTION = new Pose2d(-10, 30, toRadians(-135));
        public static Pose2d NINEOCLOCK_JUNCTION = new Pose2d(28, 4, toRadians(-135));

        // These are 'trajectory pieces' which should be named like this:
        // {STARTING_POSITION}_TO_{ENDING_POSITION}
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                START_TO_NINEOCLOCK_JUNCTION =
                        b -> b.apply(START)
                                .splineTo(NINEOCLOCK_JUNCTION.vec(), NINEOCLOCK_JUNCTION.getHeading())
                                .build(),
                START_TO_SIXOCLOCK_JUNCTION =
                        b -> b.apply(START)
                                .splineTo(NINEOCLOCK_JUNCTION.vec(), SIXOCLOCK_JUNCTION.getHeading())
                                .build(),
                NINEOCLOCK_JUNCTION_TO_STACK =
                        b -> b.apply(NINEOCLOCK_JUNCTION)
                                .lineToLinearHeading(STACK)
                                .build(),
                SIXOCLOCK_JUNCTION_TO_STACK =
                        b -> b.apply(SIXOCLOCK_JUNCTION)
                                .lineToLinearHeading(STACK)
                                .build(),
                STACK_TO_NINEOCLOCK_JUNCTION =
                        b -> b.apply(STACK)
                                .lineToLinearHeading(NINEOCLOCK_JUNCTION)
                                .build(),
                STACK_TO_SIXOCLOCK_JUNCTION =
                        b -> b.apply(STACK)
                                .lineToLinearHeading(SIXOCLOCK_JUNCTION)
                                .build(),
                NINEOCLOCK_JUNCTION_TO_LEFT =
                        b -> b.apply(NINEOCLOCK_JUNCTION)
                                .splineTo(LEFT.vec(), LEFT.getHeading())
                                .build(),
                NINEOCLOCK_JUNCTION_TO_MIDDLE =
                        b -> b.apply(NINEOCLOCK_JUNCTION)
                                .splineTo(MIDDLE.vec(), MIDDLE.getHeading())
                                .build(),
                NINEOCLOCK_JUNCTION_TO_RIGHT =
                        b -> b.apply(NINEOCLOCK_JUNCTION)
                                .splineTo(RIGHT.vec(), RIGHT.getHeading())
                                .build(),
                SIXOCLOCK_JUNCTION_TO_LEFT =
                        b -> b.apply(SIXOCLOCK_JUNCTION)
                                .splineTo(LEFT.vec(), LEFT.getHeading())
                                .build(),
                SIXOCLOCK_JUNCTION_TO_MIDDLE =
                        b -> b.apply(SIXOCLOCK_JUNCTION)
                                .splineTo(MIDDLE.vec(), MIDDLE.getHeading())
                                .build(),
                SIXCLOCK_JUNCTION_TO_RIGHT =
                        b -> b.apply(SIXOCLOCK_JUNCTION)
                                .splineTo(RIGHT.vec(), RIGHT.getHeading())
                                .build();
    }

    // Away locations:
    public static class Away {
        public static Pose2d START = new Pose2d(-36, 66, toRadians(-90));
        public static Pose2d STACK = new Pose2d(-62, 12, toRadians(180));
        public static Pose2d LEFT = new Pose2d(-12, 36, toRadians(90));
        public static Pose2d MIDDLE = new Pose2d(-36, 36, toRadians(90));
        public static Pose2d RIGHT = new Pose2d(-60, 36, toRadians(0));
        // These have "home/away" modifiers, because we want to stay on "our side" during auto
        public static Pose2d TWELVEOCLOCK_JUNCTION = new Pose2d(18, -3, toRadians(-135));
        public static Pose2d THREEOCLOCK_JUNCTION = new Pose2d(-28, 4, toRadians(-45));
        public static Pose2d SIXOCLOCK_JUNCTION = new Pose2d(-10, 30, toRadians(-135));
        public static Pose2d NINEOCLOCK_JUNCTION = new Pose2d(28, 4, toRadians(-135));
        // These are 'trajectory pieces' which should be named like this:
        // {STARTING_POSITION}_TO_{ENDING_POSITION}
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                START_TO_THREEOCLOCK_JUNCTION =
                        b -> b.apply(START)
                                .splineTo(THREEOCLOCK_JUNCTION.vec(), THREEOCLOCK_JUNCTION.getHeading())
                                .build(),
                START_TO_SIXOCLOCK_JUNCTION =
                        b -> b.apply(START)
                                .splineTo(SIXOCLOCK_JUNCTION.vec(), SIXOCLOCK_JUNCTION.getHeading())
                                .build(),
                THREEOCLOCK_JUNCTION_TO_STACK =
                        b -> b.apply(THREEOCLOCK_JUNCTION)
                                .lineToLinearHeading(STACK)
                                .build(),
                SIXOCLOCK_JUNCTION_TO_STACK =
                        b -> b.apply(SIXOCLOCK_JUNCTION)
                                .lineToLinearHeading(STACK)
                                .build(),
                STACK_TO_THREEOCLOCK_JUNCTION =
                        b -> b.apply(STACK)
                                .lineToLinearHeading(THREEOCLOCK_JUNCTION)
                                .build(),
                STACK_TO_SIXOCLOCK_JUNCTION =
                        b -> b.apply(STACK)
                                .lineToLinearHeading(SIXOCLOCK_JUNCTION)
                                .build(),
                THREEOCLOCK_JUNCTION_LEFT =
                        b -> b.apply(THREEOCLOCK_JUNCTION)
                                .splineTo(LEFT.vec(), LEFT.getHeading())
                                .build();
    }
}
