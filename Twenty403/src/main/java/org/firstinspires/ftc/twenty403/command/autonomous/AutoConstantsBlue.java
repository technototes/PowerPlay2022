package org.firstinspires.ftc.twenty403.command.autonomous;

import static java.lang.Math.toRadians;

import java.util.function.Function;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.path.geometry.ConfigurablePose;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

public class AutoConstantsBlue {
    @Config
    public static class Home {
        public static ConfigurablePose START = new ConfigurablePose(36, -66, toRadians(90));
        public static ConfigurablePose STACK = new ConfigurablePose(60, -12, toRadians(0));
        public static ConfigurablePose BETWEEN = new ConfigurablePose(-40, 12, toRadians(180));
        public static ConfigurablePose BETWEEN_2 = new ConfigurablePose(-36, 6, toRadians(90));
        public static ConfigurablePose BETWEEN_3 = new ConfigurablePose(-36, 36, toRadians(90));
        public static ConfigurablePose LEFT = new ConfigurablePose(-12, 36, toRadians(-90));
        public static ConfigurablePose MIDDLE = new ConfigurablePose(-36, 36, toRadians(90));
        public static ConfigurablePose RIGHT = new ConfigurablePose(-60, 36, toRadians(-90));
        // These have "home/away" modifiers, because we want to stay on "our side" during auto
        public static ConfigurablePose N_JUNCTION = new ConfigurablePose(18, -3, toRadians(-135));
        public static ConfigurablePose E_JUNCTION = new ConfigurablePose(-30, 6, toRadians(-45));
        public static ConfigurablePose S_JUNCTION = new ConfigurablePose(-10, 30, toRadians(-135));
        public static ConfigurablePose W_JUNCTION = new ConfigurablePose(26, -9, 2.2);
        public static ConfigurablePose TEST_A = new ConfigurablePose(36, -66, toRadians(90));
        public static ConfigurablePose TEST_B = new ConfigurablePose(28, -4, toRadians(-135));
        public static ConfigurablePose BETWEEN_START_W_JUNCTION = new ConfigurablePose(36, -21, 2);
        public static ConfigurablePose BETWEEN_W_JUNCTION_STACK = new ConfigurablePose(36, -14, .4);
        // These are 'trajectory pieces' which should be named like this:
        // {STARTING_POSITION}_TO_{ENDING_POSITION}
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                TEST_PATH = b -> b.apply(TEST_A.toPose())
                .lineToLinearHeading(TEST_B.toPose()).build(),
                START_TO_E_JUNCTION =
                        b -> b.apply(START.toPose())
                                .splineTo(E_JUNCTION.toVec(), E_JUNCTION.getHeading())
                                .build(),
                START_TO_W_JUNCTION =
                        b -> b.apply(START.toPose())
                                .lineToLinearHeading(BETWEEN_START_W_JUNCTION.toPose())
                                .lineToLinearHeading(W_JUNCTION.toPose())
                                .build(),
                W_JUNCTION_TO_STACK =
                        b -> b.apply(W_JUNCTION.toPose())
                                .lineToLinearHeading(BETWEEN_W_JUNCTION_STACK.toPose())
                                .lineToLinearHeading(STACK.toPose())
                                .build(),
                START_TO_S_JUNCTION =
                        b -> b.apply(START.toPose())
                                .splineTo(S_JUNCTION.toVec(), S_JUNCTION.getHeading())
                                .build(),
                E_JUNCTION_TO_STACK =
                        b -> b.apply(E_JUNCTION.toPose())
                                .lineToLinearHeading(STACK.toPose())
                                .build(),
                E_JUNCTION_TO_BETWEEN =
                        b -> b.apply(E_JUNCTION.toPose())
                                .lineToLinearHeading(BETWEEN.toPose())
                                .build(),
                E_JUNCTION_TO_BETWEEN_2 =
                        b -> b.apply(E_JUNCTION.toPose())
                                .lineToLinearHeading(BETWEEN_2.toPose())
                                .build(),
                BETWEEN_TO_STACK =
                        b -> b.apply(BETWEEN.toPose())
                                .lineToLinearHeading(STACK.toPose())
                                .build(),
                STACK_TO_BETWEEN =
                        b -> b.apply(STACK.toPose())
                                .lineToLinearHeading(BETWEEN.toPose())
                                .build(),
                BETWEEN_TO_E_JUNCTION =
                        b -> b.apply(BETWEEN.toPose())
                                .lineToLinearHeading(E_JUNCTION.toPose())
                                .build(),
                START_TO_LEFT_PARK =
                        b -> b.apply(START.toPose())
                                .lineToLinearHeading(LEFT.toPose())
                                .build(),
                START_TO_MIDDLE_PARK =
                        b -> b.apply(START.toPose())
                                .lineToLinearHeading(MIDDLE.toPose())
                                .build(),
                START_TO_RIGHT_PARK =
                        b -> b.apply(START.toPose())
                                .lineToLinearHeading(RIGHT.toPose())
                                .build(),

        S_JUNCTION_TO_STACK =
                b -> b.apply(S_JUNCTION.toPose())
                        .lineToLinearHeading(STACK.toPose())
                        .build(),
                STACK_TO_E_JUNCTION =
                        b -> b.apply(STACK.toPose())
                                .lineToLinearHeading(E_JUNCTION.toPose())
                                .build(),
                STACK_TO_S_JUNCTION =
                        b -> b.apply(STACK.toPose())
                                .lineToLinearHeading(S_JUNCTION.toPose())
                                .build(),
                BETWEEN_TO_MIDDLE =
                        b -> b.apply(BETWEEN_2.toPose())
                                .splineTo(MIDDLE.toVec(), MIDDLE.getHeading())
                                .build(),
                BETWEEN_2_TO_BETWEEN_3 =
                        b -> b.apply(BETWEEN_2.toPose())
                                .splineTo(BETWEEN_3.toVec(), BETWEEN_3.getHeading())
                                .build(),
                BETWEEN_3_TO_LEFT =
                        b -> b.apply(BETWEEN_3.toPose())
                                .lineToLinearHeading(LEFT.toPose())
                                .build(),
                BETWEEN_3_TO_RIGHT =
                        b -> b.apply(BETWEEN_3.toPose())
                                .lineToLinearHeading(RIGHT.toPose())
                                .build(),
                E_JUNCTION_TO_MIDDLE =
                        b -> b.apply(E_JUNCTION.toPose())
                                .splineTo(MIDDLE.toVec(), LEFT.getHeading())
                                .build(),
                E_JUNCTION_TO_RIGHT =
                        b -> b.apply(E_JUNCTION.toPose())
                                .splineTo(RIGHT.toVec(), LEFT.getHeading())
                                .build();


    }

    public static class Away {
        public static ConfigurablePose START = new ConfigurablePose(-36, -66, toRadians(90));
        public static ConfigurablePose JUNCTION = new ConfigurablePose(-28, -4, toRadians(180));
        public static ConfigurablePose STACK = new ConfigurablePose(-62, -12, toRadians(180));
        public static ConfigurablePose PARK_LEFT = new ConfigurablePose(-60, -36, toRadians(0));
        public static ConfigurablePose PARK_MIDDLE = new ConfigurablePose(-36, -36, toRadians(-90));
        public static ConfigurablePose PARK_RIGHT = new ConfigurablePose(-12, -36, toRadians(-90));

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                START_TO_JUNCTION =
                b -> b.apply(START.toPose())
                        .splineTo(JUNCTION.toVec(), JUNCTION.getHeading())
                        .build(),
                JUNCTION_TO_STACK =
                        b -> b.apply(JUNCTION.toPose())
                                .lineToLinearHeading(STACK.toPose())
                                .build(),
                JUNCTION_TO_PARK_LEFT =
                        b -> b.apply(JUNCTION.toPose())
                                .lineToLinearHeading(PARK_LEFT.toPose())
                                .build(),
                JUNCTION_TO_PARK_MIDDLE =
                        b -> b.apply(JUNCTION.toPose())
                                .lineToLinearHeading(PARK_MIDDLE.toPose())
                                .build(),
                JUNCTION_TO_PARK_RIGHT =
                        b -> b.apply(JUNCTION.toPose())
                                .lineToLinearHeading(PARK_RIGHT.toPose())
                                .build(),
                STACK_TO_JUNCTION =
                        b -> b.apply(STACK.toPose())
                                .lineToLinearHeading(JUNCTION.toPose())
                                .build(),
                START_TO_LEFT_PARK =
                        b -> b.apply(START.toPose())
                                .lineToLinearHeading(PARK_LEFT.toPose())
                                .build(),
                START_TO_MIDDLE_PARK =
                        b -> b.apply(START.toPose())
                                .lineToLinearHeading(PARK_MIDDLE.toPose())
                                .build(),
                START_TO_RIGHT_PARK =
                        b -> b.apply(START.toPose())
                                .lineToLinearHeading(PARK_RIGHT.toPose())
                                .build();


    }
}
