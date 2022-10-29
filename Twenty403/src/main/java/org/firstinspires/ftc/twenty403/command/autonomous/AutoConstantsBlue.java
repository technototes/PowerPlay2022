package org.firstinspires.ftc.twenty403.command.autonomous;

import static java.lang.Math.toRadians;

import java.util.function.Function;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.Function;

import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

public class AutoConstantsBlue {
    public static class Home {
        public static Pose2d START = new Pose2d(-36, 66, toRadians(-90));
        public static Pose2d STACK = new Pose2d(-60, 12, toRadians(180));
        public static Pose2d BETWEEN = new Pose2d(-40, 12, toRadians(180));
        public static Pose2d BETWEEN_2 = new Pose2d(-36, 6, toRadians(90));
        public static Pose2d BETWEEN_3 = new Pose2d(-36, 36, toRadians(90));
        public static Pose2d LEFT = new Pose2d(-12, 36, toRadians(-90));
        public static Pose2d MIDDLE = new Pose2d(-36, 36, toRadians(90));
        public static Pose2d RIGHT = new Pose2d(-60, 36, toRadians(-90));
        // These have "home/away" modifiers, because we want to stay on "our side" during auto
        public static Pose2d N_JUNCTION = new Pose2d(18, -3, toRadians(-135));
        public static Pose2d E_JUNCTION = new Pose2d(-30, 6, toRadians(-45));
        public static Pose2d S_JUNCTION = new Pose2d(-10, 30, toRadians(-135));
        public static Pose2d W_JUNCTION = new Pose2d(28, 4, toRadians(-135));
        // These are 'trajectory pieces' which should be named like this:
        // {STARTING_POSITION}_TO_{ENDING_POSITION}
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                START_TO_E_JUNCTION =
                b -> b.apply(START)
                        .splineTo(E_JUNCTION.vec(), E_JUNCTION.getHeading())
                        .build(),
                START_TO_S_JUNCTION =
                        b -> b.apply(START)
                                .splineTo(S_JUNCTION.vec(), S_JUNCTION.getHeading())
                                .build(),
                E_JUNCTION_TO_STACK =
                        b -> b.apply(E_JUNCTION)
                                .lineToLinearHeading(STACK)
                                .build(),
                E_JUNCTION_TO_BETWEEN =
                        b -> b.apply(E_JUNCTION)
                                .lineToLinearHeading(BETWEEN)
                                .build(),
                E_JUNCTION_TO_BETWEEN_2 =
                        b -> b.apply(E_JUNCTION)
                                .lineToLinearHeading(BETWEEN_2)
                                .build(),
                BETWEEN_TO_STACK =
                        b -> b.apply(BETWEEN)
                                .lineToLinearHeading(STACK)
                                .build(),
                STACK_TO_BETWEEN =
                        b -> b.apply(STACK)
                                .lineToLinearHeading(BETWEEN)
                                .build(),
                BETWEEN_TO_E_JUNCTION =
                        b -> b.apply(BETWEEN)
                                .lineToLinearHeading(E_JUNCTION)
                                .build(),
                START_TO_LEFT_PARK =
                        b -> b.apply(START)
                                .lineToLinearHeading(LEFT)
                                .build(),
                START_TO_MIDDLE_PARK =
                        b -> b.apply(START)
                                .lineToLinearHeading(MIDDLE)
                                .build(),
                START_TO_RIGHT_PARK =
                        b -> b.apply(START)
                                .lineToLinearHeading(RIGHT)
                                .build(),

        S_JUNCTION_TO_STACK =
                b -> b.apply(S_JUNCTION)
                        .lineToLinearHeading(STACK)
                        .build(),
                STACK_TO_E_JUNCTION =
                        b -> b.apply(STACK)
                                .lineToLinearHeading(E_JUNCTION)
                                .build(),
                STACK_TO_S_JUNCTION =
                        b -> b.apply(STACK)
                                .lineToLinearHeading(S_JUNCTION)
                                .build(),
                BETWEEN_TO_MIDDLE =
                        b -> b.apply(BETWEEN_2)
                                .splineTo(MIDDLE.vec(), MIDDLE.getHeading())
                                .build(),
                BETWEEN_2_TO_BETWEEN_3 =
                        b -> b.apply(BETWEEN_2)
                                .splineTo(BETWEEN_3.vec(), BETWEEN_3.getHeading())
                                .build(),
                BETWEEN_3_TO_LEFT =
                        b -> b.apply(BETWEEN_3)
                                .lineToLinearHeading(LEFT)
                                .build(),
                BETWEEN_3_TO_RIGHT =
                        b -> b.apply(BETWEEN_3)
                                .lineToLinearHeading(RIGHT)
                                .build(),
                E_JUNCTION_TO_MIDDLE =
                        b -> b.apply(E_JUNCTION)
                                .splineTo(MIDDLE.vec(), LEFT.getHeading())
                                .build(),
                E_JUNCTION_TO_RIGHT =
                        b -> b.apply(E_JUNCTION)
                                .splineTo(RIGHT.vec(), LEFT.getHeading())
                                .build();


    }

    public static class Away {
        public static Pose2d START = new Pose2d(-36, -66, toRadians(90));
        public static Pose2d JUNCTION = new Pose2d(-28, -4, toRadians(180));
        public static Pose2d STACK = new Pose2d(-62, -12, toRadians(180));
        public static Pose2d PARK_LEFT = new Pose2d(-60, -36, toRadians(0));
        public static Pose2d PARK_MIDDLE = new Pose2d(-36, -36, toRadians(-90));
        public static Pose2d PARK_RIGHT = new Pose2d(-12, -36, toRadians(-90));

public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                START_TO_JUNCTION =
                b -> b.apply(START)
                        .splineTo(JUNCTION.vec(), JUNCTION.getHeading())
                        .build(),
                JUNCTION_TO_STACK =
                        b -> b.apply(JUNCTION)
                                .lineToLinearHeading(STACK)
                                .build(),
                JUNCTION_TO_PARK_LEFT =
                        b -> b.apply(JUNCTION)
                                .lineToLinearHeading(PARK_LEFT)
                                .build(),
                JUNCTION_TO_PARK_MIDDLE =
                        b -> b.apply(JUNCTION)
                                .lineToLinearHeading(PARK_MIDDLE)
                                .build(),
                JUNCTION_TO_PARK_RIGHT =
                        b -> b.apply(JUNCTION)
                                .lineToLinearHeading(PARK_RIGHT)
                                .build(),
                STACK_TO_JUNCTION =
                        b -> b.apply(STACK)
                                .lineToLinearHeading(JUNCTION)
                                .build(),
                START_TO_LEFT_PARK =
                        b -> b.apply(START)
                                .lineToLinearHeading(PARK_LEFT)
                                .build(),
                START_TO_MIDDLE_PARK =
                        b -> b.apply(START)
                                .lineToLinearHeading(PARK_MIDDLE)
                                .build(),
                START_TO_RIGHT_PARK =
                        b -> b.apply(START)
                                .lineToLinearHeading(PARK_RIGHT)
                                .build();


    }
}
