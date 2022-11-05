package org.firstinspires.ftc.twenty403.command.autonomous;

import static java.lang.Math.E;
import static java.lang.Math.toRadians;

import java.util.function.Function;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.logger.LogConfig;
import com.technototes.path.geometry.ConfigurablePose;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

public class AutoConstantsBlue {
    @Config
    public static class Home {
        public static ConfigurablePose START = new ConfigurablePose(36, -66, toRadians(90));
        public static ConfigurablePose STACK = new ConfigurablePose(60, -12, toRadians(0));
        public static ConfigurablePose LEFT = new ConfigurablePose(12, -14, 4.71);
        public static ConfigurablePose MIDDLE = new ConfigurablePose(36, -14, 4.71);
        public static ConfigurablePose RIGHT = new ConfigurablePose(60, -11, 3.93);
        public static ConfigurablePose W_JUNCTION = new ConfigurablePose(26, -9, 2.2);
        public static ConfigurablePose BETWEEN_START_W_JUNCTION = new ConfigurablePose(36, -21, 2);
        public static ConfigurablePose BETWEEN_W_JUNCTION_STACK = new ConfigurablePose(36, -14, .4);
        public static ConfigurablePose BETWEEN_STACK_W_JUNCTION = new ConfigurablePose(36, -14, 3);
        // These are 'trajectory pieces' which should be named like this:
        // {STARTING_POSITION}_TO_{ENDING_POSITION}
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
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
                STACK_TO_W_JUNCTION =
                        b -> b.apply(STACK.toPose())
                                .lineToLinearHeading(BETWEEN_STACK_W_JUNCTION.toPose())
                                .lineToLinearHeading(W_JUNCTION.toPose())
                                .build(),
                W_JUNCTION_TO_LEFT_PARK =
                        b -> b.apply(W_JUNCTION.toPose())
                                .lineToLinearHeading(BETWEEN_STACK_W_JUNCTION.toPose())
                                .lineToLinearHeading(LEFT.toPose())
                                .build(),
                W_JUNCTION_TO_MIDDLE_PARK =
                        b -> b.apply(W_JUNCTION.toPose())
                                .lineToLinearHeading(BETWEEN_STACK_W_JUNCTION.toPose())
                                .lineToLinearHeading(MIDDLE.toPose())
                                .build(),
                W_JUNCTION_TO_RIGHT_PARK =
                        b -> b.apply(W_JUNCTION.toPose())
                                .lineToLinearHeading(BETWEEN_STACK_W_JUNCTION.toPose())
                                .lineToLinearHeading(RIGHT.toPose())
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
                                .build();


    }

    @Config
    public static class Away {
        public static ConfigurablePose START = new ConfigurablePose(-36, -66, toRadians(90));
        public static ConfigurablePose E_JUNCTION = new ConfigurablePose(-30, -8, 0.63);
        public static ConfigurablePose STACK = new ConfigurablePose(-60, -17, toRadians(180));
        public static ConfigurablePose LEFT = new ConfigurablePose(-60, -14, 4.88);
        public static ConfigurablePose MIDDLE = new ConfigurablePose(-37, -16, 5.41);
        public static ConfigurablePose RIGHT = new ConfigurablePose(-12, -13, 5.41);
        public static ConfigurablePose BETWEEN_START_E_JUNCTION = new ConfigurablePose(-36, -21, toRadians(75));
        public static ConfigurablePose BETWEEN_E_JUNCTION_STACK = new ConfigurablePose(-36, -14, toRadians(180));
        public static ConfigurablePose BETWEEN_STACK_E_JUNCTION = new ConfigurablePose(-36, -14, toRadians(75));

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                START_TO_E_JUNCTION =
                b -> b.apply(START.toPose())
                        .splineTo(BETWEEN_START_E_JUNCTION.toPose().vec(), BETWEEN_START_E_JUNCTION.toPose().getHeading())
                        .splineTo(E_JUNCTION.toPose().vec(), E_JUNCTION.toPose().getHeading())
                        .build(),
                E_JUNCTION_TO_STACK =
                        b -> b.apply(E_JUNCTION.toPose())
                                .lineToLinearHeading(BETWEEN_E_JUNCTION_STACK.toPose())
                                .lineToLinearHeading(STACK.toPose())
                                .build(),
                STACK_TO_E_JUNCTION =
                        b -> b.apply(STACK.toPose())
                                .lineToLinearHeading(BETWEEN_STACK_E_JUNCTION.toPose())
                                .lineToLinearHeading(E_JUNCTION.toPose())
                                .build(),
                E_JUNCTION_TO_LEFT_PARK =
                        b -> b.apply(E_JUNCTION.toPose())
                                .lineToLinearHeading(BETWEEN_STACK_E_JUNCTION.toPose())
                                .lineToLinearHeading(LEFT.toPose())
                                .build(),
                E_JUNCTION_TO_MIDDLE_PARK =
                        b -> b.apply(E_JUNCTION.toPose())
                                .lineToLinearHeading(BETWEEN_STACK_E_JUNCTION.toPose())
                                .lineToLinearHeading(MIDDLE.toPose())
                                .build(),
                E_JUNCTION_TO_RIGHT_PARK =
                        b -> b.apply(E_JUNCTION.toPose())
                                .lineToLinearHeading(BETWEEN_STACK_E_JUNCTION.toPose())
                                .lineToLinearHeading(RIGHT.toPose())
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
                        .build();
    }
}
