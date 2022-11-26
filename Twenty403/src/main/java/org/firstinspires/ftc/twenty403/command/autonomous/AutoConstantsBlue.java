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
        public static ConfigurablePose TELESTART = new ConfigurablePose(0, 0, toRadians(90));
        public static ConfigurablePose FORWARD_MOVE = new ConfigurablePose(0, 24, toRadians(90));
        public static ConfigurablePose BACKWARD_MOVE = new ConfigurablePose(0, 24, toRadians(90));
        public static ConfigurablePose LEFT_MOVE = new ConfigurablePose(-24, 0, toRadians(90));
        public static ConfigurablePose RIGHT_MOVE = new ConfigurablePose(24, 0, toRadians(90));

        public static ConfigurablePose STACK = new ConfigurablePose(66, -14, toRadians(0));
        public static ConfigurablePose LEFT = new ConfigurablePose(15, -16, toRadians(90));
        public static ConfigurablePose MIDDLE = new ConfigurablePose(36, -16, toRadians(90));
        public static ConfigurablePose RIGHT = new ConfigurablePose(60, -17, toRadians(90));
        public static ConfigurablePose W_JUNCTION = new ConfigurablePose(31, -8, 2.5);

        //public static ConfigurablePose BETWEEN_START_W_jUNCTION_ONE = new ConfigurablePose(40, -48, toRadians(180));n
        public static ConfigurablePose BETWEEN_START_W_JUNCTION_TWO = new ConfigurablePose(42, -20, 4);
        public static ConfigurablePose BETWEEN_W_JUNCTION_STACK = new ConfigurablePose(35, -16, .4);
        public static ConfigurablePose BETWEEN_STACK_W_JUNCTION = new ConfigurablePose(36, -14, 2.4);
        public static ConfigurablePose BETWEEN_START_LEFT = new ConfigurablePose(15, -60, toRadians(90));
        public static ConfigurablePose BETWEEN_START_RIGHT = new ConfigurablePose(60, -60, toRadians(90));
        public static ConfigurablePose TERMINAL = new ConfigurablePose(61, -64, toRadians(180));
        // These are 'trajectory pieces' which should be named like this:
        // {STARTING_POSITION}_TO_{ENDING_POSITION}
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                TELESTART_TO_FORWARD_MOVE =
                b -> b.apply(TELESTART.toPose())
                        .lineToLinearHeading(FORWARD_MOVE.toPose())
                        .build(),
                TELESTART_TO_BACKWARD_MOVE =
                        b -> b.apply(TELESTART.toPose())
                                .lineToLinearHeading(BACKWARD_MOVE.toPose())
                                .build(),
                TELESTART_TO_LEFT_MOVE =
                        b -> b.apply(TELESTART.toPose())
                                .lineToLinearHeading(LEFT_MOVE.toPose())
                                .build(),
                TELESTART_TO_RIGHT_MOVE =
                        b -> b.apply(TELESTART.toPose())
                                .lineToLinearHeading(RIGHT_MOVE.toPose())
                                .build(),

        START_TO_W_JUNCTION =
                b -> b.apply(START.toPose())
                        //.lineToLinearHeading(BETWEEN_START_W_jUNCTION_ONE.toPose())
                        .lineToLinearHeading(BETWEEN_START_W_JUNCTION_TWO.toPose())
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
                                .lineToLinearHeading(LEFT_MOVE.toPose())
                                .build(),
                W_JUNCTION_TO_MIDDLE_PARK =
                        b -> b.apply(W_JUNCTION.toPose())
                                .lineToLinearHeading(BETWEEN_STACK_W_JUNCTION.toPose())
                                .lineToLinearHeading(MIDDLE.toPose())
                                .build(),
                W_JUNCTION_TO_RIGHT_PARK =
                        b -> b.apply(W_JUNCTION.toPose())
                                .lineToLinearHeading(BETWEEN_STACK_W_JUNCTION.toPose())
                                .lineToLinearHeading(RIGHT_MOVE.toPose())
                                .build(),


        START_TO_LEFT_PARK =
                b -> b.apply(START.toPose())
                        //.lineToLinearHeading(TERMINAL.toPose())
                        .lineToLinearHeading(BETWEEN_START_LEFT.toPose())
                        .lineToLinearHeading(LEFT_MOVE.toPose())
                        .build(),
                START_TO_MIDDLE_PARK =
                        b -> b.apply(START.toPose())
                                //.lineToLinearHeading(TERMINAL.toPose())
                                //.lineToLinearHeading(START.toPose())
                                .lineToLinearHeading(MIDDLE.toPose())
                                .build(),
                START_TO_RIGHT_PARK =
                        b -> b.apply(START.toPose())
                                //.lineToLinearHeading(TERMINAL.toPose())
                                .lineToLinearHeading(BETWEEN_START_RIGHT.toPose())
                                .lineToLinearHeading(RIGHT_MOVE.toPose())
                                .build();


    }

    @Config
    public static class Away {
        public static ConfigurablePose START = new ConfigurablePose(-36, -66, toRadians(90));
        public static ConfigurablePose E_JUNCTION = new ConfigurablePose(-30, -8, 0.63);
        public static ConfigurablePose STACK = new ConfigurablePose(-60, -17, toRadians(180));
        public static ConfigurablePose LEFT = new ConfigurablePose(-58, -22, toRadians(90));
        public static ConfigurablePose MIDDLE = new ConfigurablePose(-37, -16, toRadians(90));
        public static ConfigurablePose RIGHT = new ConfigurablePose(-14, -15, toRadians(90));
        public static ConfigurablePose BETWEEN_START_E_JUNCTION = new ConfigurablePose(-36, -15, toRadians(45));
        public static ConfigurablePose BETWEEN_E_JUNCTION_STACK = new ConfigurablePose(-38, -17, toRadians(180));
        public static ConfigurablePose BETWEEN_STACK_E_JUNCTION = new ConfigurablePose(-36, -14, toRadians(75));
        public static ConfigurablePose BETWEEN_START_LEFT = new ConfigurablePose(-58, -60, toRadians(90));
        public static ConfigurablePose BETWEEN_START_RIGHT = new ConfigurablePose(-14, -60, toRadians(90));
        public static ConfigurablePose TERMINAL = new ConfigurablePose(-61, -64, toRadians(180));

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                START_TO_E_JUNCTION =
                b -> b.apply(START.toPose())
                        .lineToLinearHeading(BETWEEN_START_E_JUNCTION.toPose())
                        .lineToLinearHeading(E_JUNCTION.toPose())
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
                        //.lineToLinearHeading(TERMINAL.toPose())
                        .lineToLinearHeading(BETWEEN_START_LEFT.toPose())
                        .lineToLinearHeading(LEFT.toPose())
                        .build(),
                START_TO_MIDDLE_PARK =
                        b -> b.apply(START.toPose())
                                //.lineToLinearHeading(TERMINAL.toPose())
                                //.lineToLinearHeading(START.toPose())
                                .lineToLinearHeading(MIDDLE.toPose())
                                .build(),

        START_TO_RIGHT_PARK =
                b -> b.apply(START.toPose())
                        //.lineToLinearHeading(TERMINAL.toPose())
                        .lineToLinearHeading(BETWEEN_START_RIGHT.toPose())
                        .lineToLinearHeading(RIGHT.toPose())
                        .build();
    }
}
