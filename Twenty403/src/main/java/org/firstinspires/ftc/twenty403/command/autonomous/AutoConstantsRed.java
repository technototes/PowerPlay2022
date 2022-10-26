package org.firstinspires.ftc.twenty403.command.autonomous;

import static java.lang.Math.toRadians;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
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
        public static Pose2d N_JUNCTION = new Pose2d(18, -3, toRadians(-135));
        // 3 O'Clock is on the Away side, so only use if we know our alliance partner won't be
        // in the way
        public static Pose2d E_JUNCTION = new Pose2d(-28, 4, toRadians(-45));
        public static Pose2d S_JUNCTION = new Pose2d(-10, 30, toRadians(-135));
        public static Pose2d W_JUNCTION = new Pose2d(28, 4, toRadians(-135));

        // These are 'trajectory pieces' which should be named like this:
        // {STARTING_POSITION}_TO_{ENDING_POSITION}
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                START_TO_W_JUNCTION =
                b -> b.apply(START)
                        .splineTo(W_JUNCTION.vec(), W_JUNCTION.getHeading())
                        .build(),
                START_TO_S_JUNCTION =
                        b -> b.apply(START)
                                .splineTo(S_JUNCTION.vec(), S_JUNCTION.getHeading())
                                .build(),
                W_JUNCTION_TO_STACK =
                        b -> b.apply(W_JUNCTION).lineToLinearHeading(STACK).build(),
                S_JUNCTION_TO_STACK =
                        b -> b.apply(S_JUNCTION).lineToLinearHeading(STACK).build(),
                STACK_TO_W_JUNCTION =
                        b -> b.apply(STACK).lineToLinearHeading(W_JUNCTION).build(),
                STACK_TO_S_JUNCTION =
                        b -> b.apply(STACK).lineToLinearHeading(S_JUNCTION).build(),
                W_JUNCTION_TO_LEFT =
                        b -> b.apply(W_JUNCTION)
                                .splineTo(LEFT.vec(), LEFT.getHeading())
                                .build(),
                W_JUNCTION_TO_MIDDLE =
                        b -> b.apply(W_JUNCTION)
                                .splineTo(MIDDLE.vec(), MIDDLE.getHeading())
                                .build(),
                W_JUNCTION_TO_RIGHT =
                        b -> b.apply(W_JUNCTION)
                                .splineTo(RIGHT.vec(), RIGHT.getHeading())
                                .build(),
                S_JUNCTION_TO_LEFT =
                        b -> b.apply(S_JUNCTION)
                                .splineTo(LEFT.vec(), LEFT.getHeading())
                                .build(),
                S_JUNCTION_TO_MIDDLE =
                        b -> b.apply(S_JUNCTION)
                                .splineTo(MIDDLE.vec(), MIDDLE.getHeading())
                                .build(),
                S_JUNCTION_TO_RIGHT =
                        b -> b.apply(S_JUNCTION)
                                .splineTo(RIGHT.vec(), RIGHT.getHeading())
                                .build();
    }

    public static class Away {
        public static Pose2d START = new Pose2d(36, -66, toRadians(90));
        public static Pose2d STACK = new Pose2d(60, -12, toRadians(0));

        public static Pose2d PARK_LEFT = new Pose2d(12, -36, toRadians(-90));
        public static Pose2d PARK_MIDDLE = new Pose2d(36, -36, toRadians(-90));
        public static Pose2d PARK_RIGHT = new Pose2d(60, -36, toRadians(180));

        public static Pose2d E_JUNCTION = new Pose2d(27, -5, toRadians(120));
        public static Pose2d S_JUNCTION = new Pose2d(4, -28, toRadians(135));

        public static Pose2d BETWEEN = new Pose2d(33, -10, toRadians(10));

        // These are 'trajectory pieces' which should be named like this:
        // {STARTING_POSITION}_TO_{ENDING_POSITION}
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>

                START_TO_E_JUNCTION =
                b -> b.apply(START).splineTo(E_JUNCTION.vec(), E_JUNCTION.getHeading()).build(),
        //START_TO_SOUTH_JUNCTION =
        // ()-> function.apply(START).lineToLinearHeading(SOUTH_JUNCTION).build(),
        E_JUNCTION_TO_STACK =
                b -> b.apply(E_JUNCTION).lineToLinearHeading(STACK).build(),
        //SOUTH_JUNCTION_TO_STACK = b->b.apply(JUNCTION).lineToLinearHeading(STACK).build(),
        STACK_TO_E_JUNCTION =
                b -> b.apply(STACK).lineToLinearHeading(E_JUNCTION).build(),
        //STACK_TO_SOUTH_JUNCTION =
        // ()->function.apply(STACK).lineToLinearHeading(JUNCTION).build(),
        E_JUNCTION_TO_PARK_LEFT =
                b -> b.apply(E_JUNCTION).lineToLinearHeading(PARK_LEFT).build(),
                E_JUNCTION_TO_PARK_MIDDLE =
                        b -> b.apply(E_JUNCTION).lineToLinearHeading(PARK_MIDDLE).build(),
                E_JUNCTION_TO_PARK_RIGHT =
                        b -> b.apply(E_JUNCTION).lineToLinearHeading(PARK_RIGHT).build();
        //SOUTH_JUNCTION_TO_PARK_LEFT =
        // ()->function.apply(JUNCTION).lineToLinearHeading(PARK_LEFT).build()
        //SOUTH_JUNCTION_TO_PARK_MIDDLE =
        // ()->function.apply(JUNCTION).lineToLinearHeading(PARK_MIDDLE).build()
        //SOUTH_JUNCTION_TO_PARK_RIGHT =
        // ()->function.apply(JUNCTION).lineToLinearHeading(PARK_RIGHT).build()

    }
}