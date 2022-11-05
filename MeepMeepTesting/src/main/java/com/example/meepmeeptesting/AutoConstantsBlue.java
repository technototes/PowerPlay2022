package com.example.meepmeeptesting;

import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import java.util.Arrays;
import java.util.function.Function;

import java.util.function.Supplier;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;


public class AutoConstantsBlue {
    // "Home" locations: (The side with the Red terminal)
    public static class Away {
        //        public static Pose2d START = new Pose2d(36, 66, toRadians(-90));
//        public static Pose2d STACK = new Pose2d(60, 12, toRadians(0));
//        public static Pose2d LEFT = new Pose2d(60, 36, toRadians(150));
//        public static Pose2d MIDDLE = new Pose2d(36, 36, toRadians(90));
//        public static Pose2d RIGHT = new Pose2d(12, 36, toRadians(90));
//        // These have "home/away" modifiers, because we want to stay on "our side" during auto
//        // 12 O'Clock is on the Blue side: Probably stay away
//        public static Pose2d N_JUNCTION = new Pose2d(18, -3, toRadians(-135));
//        // 3 O'Clock is on the Away side, so only use if we know our alliance partner won't be
//        // in the way
//        public static Pose2d E_JUNCTION = new Pose2d(-28, 4, toRadians(-45));
//        public static Pose2d S_JUNCTION = new Pose2d(-10, 30, toRadians(-135));
//        public static Pose2d W_JUNCTION = new Pose2d(27, 8, toRadians(-115));
        public static Pose2d START = new Pose2d(36, 66, toRadians(-90));
        public static Pose2d STACK = new Pose2d(60, 12, toRadians(0));
        public static Pose2d BETWEEN = new Pose2d(-40, 12, toRadians(180));
        public static Pose2d BETWEEN_2 = new Pose2d(-36, 6, toRadians(90));
        public static Pose2d BETWEEN_3 = new Pose2d(-36, 36, toRadians(90));
        public static Pose2d LEFT = new Pose2d(12, 36, toRadians(-90));
        public static Pose2d MIDDLE = new Pose2d(36, 36, toRadians(-90));
        public static Pose2d RIGHT = new Pose2d(60, 36, toRadians(-90));
        // These have "home/away" modifiers, because we want to stay on "our side" during auto
        public static Pose2d N_JUNCTION = new Pose2d(18, -3, toRadians(-135));
        public static Pose2d E_JUNCTION = new Pose2d(-30, 6, toRadians(-45));
        public static Pose2d S_JUNCTION = new Pose2d(-10, 30, toRadians(-135));
        public static Pose2d W_JUNCTION = new Pose2d(28, 4, toRadians(-135));

        public static Pose2d BETWEEN_TO_STACK = new Pose2d(37, 12, toRadians(0));
        public static Pose2d BETWEEN_TO_JUNCTION = new Pose2d(37, 12, toRadians(180));

        // These are 'trajectory pieces' which should be named like this:
        // {STARTING_POSITION}_TO_{ENDING_POSITION}
        public static double MAX_VEL = 50;
        public static double MAX_ACCEL = 40;
        public static double MAX_ANG_VEL = Math.toRadians(200);
        public static double MAX_ANG_ACCEL = Math.toRadians(200);
        public static double TRACK_WIDTH = 9.5;

        public static MinVelocityConstraint MIN_VEL = new MinVelocityConstraint(Arrays.asList(
                new AngularVelocityConstraint(MAX_ANG_VEL),
                new MecanumVelocityConstraint(MAX_VEL, TRACK_WIDTH)
        ));
        public static ProfileAccelerationConstraint PROF_ACCEL = new ProfileAccelerationConstraint(MAX_ACCEL);
        public static Function<Pose2d, TrajectoryBuilder> function = pose -> new TrajectoryBuilder(pose, MIN_VEL, PROF_ACCEL);
        public static Supplier<Trajectory>
                START_TO_W_JUNCTION =
                () -> function.apply(START)
                        .splineTo(W_JUNCTION.vec(), W_JUNCTION.getHeading())
                        .build(),
                START_TO_S_JUNCTION =
                        () -> function.apply(START)
                                .splineTo(S_JUNCTION.vec(), S_JUNCTION.getHeading())
                                .build(),
                W_JUNCTION_TO_BETWEEN_TO_STACK =
                        () -> function.apply(W_JUNCTION)
                                .lineToLinearHeading(BETWEEN_TO_STACK)
                                .build(),
                BETWEEN_T0_STACK_TO_STACK =
                        () -> function.apply(BETWEEN_TO_STACK)
                                .lineToLinearHeading(STACK)
                                .build(),

        S_JUNCTION_TO_STACK =
                () -> function.apply(S_JUNCTION)
                        .lineToLinearHeading(STACK)
                        .build(),
                STACK_TO_BETWEEN_TO_JUNCTION =
                        () -> function.apply(STACK)
                                .lineToLinearHeading(BETWEEN_TO_JUNCTION)
                                .build(),
                BETWEEN_TO_JUNCTION_TO_JUNCTION =
                        () -> function.apply(BETWEEN_TO_JUNCTION)
                                .lineToLinearHeading(W_JUNCTION)
                                .build(),
                STACK_TO_W_JUNCTION =
                        () -> function.apply(STACK)
                                .lineToLinearHeading(W_JUNCTION)
                                .build(),
                STACK_TO_S_JUNCTION =
                        () -> function.apply(STACK)
                                .lineToLinearHeading(S_JUNCTION)
                                .build(),
                STACK_TO_LEFT =
                        () -> function.apply(STACK)
                                //.splineTo(LEFT.vec(), LEFT.getHeading())
                                .lineToLinearHeading(LEFT)
                                .build(),
                W_JUNCTION_TO_MIDDLE =
                        () -> function.apply(W_JUNCTION)
                                .lineToLinearHeading(MIDDLE)
                                .build(),
                W_JUNCTION_TO_RIGHT =
                        () -> function.apply(W_JUNCTION)
                                .splineTo(RIGHT.vec(), RIGHT.getHeading())
                                .build(),
                MIDDLE_TO_RIGHT =
                        () -> function.apply(MIDDLE)
                                .lineToLinearHeading(RIGHT)
                                .build(),
                MIDDLE_TO_LEFT =
                        () -> function.apply(MIDDLE)
                                .lineToLinearHeading(LEFT)
                                .build(),
                S_JUNCTION_TO_LEFT =
                        () -> function.apply(S_JUNCTION)
                                .splineTo(LEFT.vec(), LEFT.getHeading())
                                .build(),
                S_JUNCTION_TO_MIDDLE =
                        () -> function.apply(S_JUNCTION)
                                .lineToLinearHeading(MIDDLE)
                                .build(),
                SIXCLOCK_JUNCTION_TO_RIGHT =
                        () -> function.apply(S_JUNCTION)
                                .splineTo(RIGHT.vec(), RIGHT.getHeading())
                                .build();
    }

    // Away locations:
    public static class Home {
        public static Pose2d START = new Pose2d(36, 66, toRadians(-90));
        public static Pose2d STACK = new Pose2d(60, 12, toRadians(180));
        public static Pose2d BETWEEN = new Pose2d(40, 12, toRadians(180));
        public static Pose2d BETWEEN_2 = new Pose2d(36, 6, toRadians(90));
        public static Pose2d BETWEEN_3 = new Pose2d(36, 36, toRadians(90));
        public static Pose2d LEFT = new Pose2d(12, 36, toRadians(-90));
        public static Pose2d MIDDLE = new Pose2d(36, 36, toRadians(90));
        public static Pose2d RIGHT = new Pose2d(60, 36, toRadians(-90));
        // These have "home/away" modifiers, because we want to stay on "our side" during auto
        public static Pose2d N_JUNCTION = new Pose2d(-18, -3, toRadians(-135));
        public static Pose2d E_JUNCTION = new Pose2d(30, 6, toRadians(-45));
        public static Pose2d S_JUNCTION = new Pose2d(10, 30, toRadians(-135));
        public static Pose2d W_JUNCTION = new Pose2d(-28, 4, toRadians(-135));
        // These are 'trajectory pieces' which should be named like this:
        // {STARTING_POSITION}_TO_{ENDING_POSITION}
        public static double MAX_VEL = 50;
        public static double MAX_ACCEL = 40;
        public static double MAX_ANG_VEL = Math.toRadians(180);
        public static double MAX_ANG_ACCEL = Math.toRadians(120);
        public static double TRACK_WIDTH = 9.5;

        public static MinVelocityConstraint MIN_VEL = new MinVelocityConstraint(Arrays.asList(
                new AngularVelocityConstraint(MAX_ANG_VEL),
                new MecanumVelocityConstraint(MAX_VEL, TRACK_WIDTH)
        ));
        public static ProfileAccelerationConstraint PROF_ACCEL = new ProfileAccelerationConstraint(MAX_ACCEL);
        public static Function<Pose2d, TrajectoryBuilder> function = pose -> new TrajectoryBuilder(pose, MIN_VEL, PROF_ACCEL);
        public static Supplier<Trajectory>
                START_TO_E_JUNCTION =
                () -> function.apply(START)
                        .splineTo(E_JUNCTION.vec(), E_JUNCTION.getHeading())
                        .build(),
                START_TO_S_JUNCTION =
                        () -> function.apply(START)
                                .splineTo(S_JUNCTION.vec(), S_JUNCTION.getHeading())
                                .build(),
                E_JUNCTION_TO_STACK =
                        () -> function.apply(E_JUNCTION)
                                .lineToLinearHeading(STACK)
                                .build(),
                E_JUNCTION_TO_BETWEEN =
                        () -> function.apply(E_JUNCTION)
                                .lineToLinearHeading(BETWEEN)
                                .build(),
                E_JUNCTION_TO_BETWEEN_2 =
                        () -> function.apply(E_JUNCTION)
                                .lineToLinearHeading(BETWEEN_2)
                                .build(),
                BETWEEN_TO_STACK =
                        () -> function.apply(BETWEEN)
                                .lineToLinearHeading(STACK)
                                .build(),
                STACK_TO_BETWEEN =
                        () -> function.apply(STACK)
                                .lineToLinearHeading(BETWEEN)
                                .build(),
                BETWEEN_TO_E_JUNCTION =
                        () -> function.apply(BETWEEN)
                                .lineToLinearHeading(E_JUNCTION)
                                .build(),
                START_TO_LEFT_PARK =
                        () -> function.apply(START)
                                .lineToLinearHeading(LEFT)
                                .build(),
                START_TO_MIDDLE_PARK =
                        () -> function.apply(START)
                                .lineToLinearHeading(START)
                                .lineToLinearHeading(MIDDLE)
                                .build(),
                START_TO_RIGHT_PARK =
                        () -> function.apply(START)
                                .lineToLinearHeading(START)
                                .lineToLinearHeading(RIGHT)
                                .build(),

        S_JUNCTION_TO_STACK =
                () -> function.apply(S_JUNCTION)
                        .lineToLinearHeading(STACK)
                        .build(),
                STACK_TO_E_JUNCTION =
                        () -> function.apply(STACK)
                                .lineToLinearHeading(E_JUNCTION)
                                .build(),
                STACK_TO_S_JUNCTION =
                        () -> function.apply(STACK)
                                .lineToLinearHeading(S_JUNCTION)
                                .build(),
                BETWEEN_TO_MIDDLE =
                        () -> function.apply(BETWEEN_2)
                                .splineTo(MIDDLE.vec(), MIDDLE.getHeading())
                                .build(),
                BETWEEN_2_TO_BETWEEN_3 =
                        () -> function.apply(BETWEEN_2)
                                .splineTo(BETWEEN_3.vec(), BETWEEN_3.getHeading())
                                .build(),
                BETWEEN_3_TO_LEFT =
                        () -> function.apply(BETWEEN_3)
                                .lineToLinearHeading(LEFT)
                                .build(),
                BETWEEN_3_TO_RIGHT =
                        () -> function.apply(BETWEEN_3)
                                .lineToLinearHeading(RIGHT)
                                .build(),
                E_JUNCTION_TO_MIDDLE =
                        () -> function.apply(E_JUNCTION)
                                .splineTo(MIDDLE.vec(), LEFT.getHeading())
                                .build(),
                E_JUNCTION_TO_RIGHT =
                        () -> function.apply(E_JUNCTION)
                                .splineTo(RIGHT.vec(), LEFT.getHeading())
                                .build();


    }
}
