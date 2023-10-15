package org.firstinspires.ftc.twenty403.command.autonomous;

import static java.lang.Math.toRadians;

import java.util.function.Function;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.path.geometry.ConfigurablePose;
import com.technototes.path.trajectorysequence.TrajectoryPath;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import org.firstinspires.ftc.twenty403.subsystem.OdoSubsystem;

public class AutoConstants {
    @Config
    public static class Right {
        public static double finalCycleStartTime = 20;
        public static ConfigurablePose ZERO = new ConfigurablePose(0, 0, toRadians(90));
        public static ConfigurablePose START = new ConfigurablePose(36, -66, toRadians(90));
        public static ConfigurablePose STRAIGHT = new ConfigurablePose(36, -18, toRadians(90));
        public static ConfigurablePose LEFTSIDE = new ConfigurablePose(-12, -66, toRadians(90));
        public static ConfigurablePose TELESTART = new ConfigurablePose(0, 0, toRadians(90));
        public static ConfigurablePose FORWARD_MOVE = new ConfigurablePose(0, 24, toRadians(90));
        public static ConfigurablePose BACKWARD_MOVE = new ConfigurablePose(0, 24, toRadians(90));
        public static ConfigurablePose LEFT_MOVE = new ConfigurablePose(-24, 0, toRadians(90));
        public static ConfigurablePose RIGHT_MOVE = new ConfigurablePose(24, 0, toRadians(90));

        public static ConfigurablePose STACK_ONE = new ConfigurablePose(66, -16, toRadians(0));
        public static ConfigurablePose STACK_TWO = new ConfigurablePose(68, -16, toRadians(0));
        public static ConfigurablePose STACK_THREE = new ConfigurablePose(73, -16, toRadians(0));
        //x: 66, y: -16, 0
        public static ConfigurablePose LEFT = new ConfigurablePose(18, -18, toRadians(90));
        public static ConfigurablePose MIDDLE = new ConfigurablePose(41, -16, toRadians(90));
        public static ConfigurablePose RIGHT = new ConfigurablePose(61, -17, toRadians(90));
        public static ConfigurablePose W_JUNCTION_ONE = new ConfigurablePose(29, -13, 1.9);
        public static ConfigurablePose W_JUNCTION_TWO = new ConfigurablePose(32, -10, 1.9);
        public static ConfigurablePose W_JUNCTION_THREE = new ConfigurablePose(37, -11, 2);


        //public static ConfigurablePose BETWEEN_START_W_jUNCTION_ONE = new ConfigurablePose(40, -48, toRadians(180));
        public static ConfigurablePose BETWEEN_START_W_JUNCTION = new ConfigurablePose(41, -11, 4);
        public static ConfigurablePose BETWEEN_START_W_JUNCTION_TWO = new ConfigurablePose(42, -11, 3.9);
        public static ConfigurablePose BETWEEN_W_JUNCTION_STACK = new ConfigurablePose(43, -19, 1.5);
        public static ConfigurablePose BETWEEN_STACK_W_JUNCTION = new ConfigurablePose(40, -15, 2.4);
        public static ConfigurablePose BETWEEN_START_LEFT = new ConfigurablePose(15, -60, toRadians(90));
        public static ConfigurablePose BETWEEN_START_RIGHT = new ConfigurablePose(60, -60, toRadians(90));
        public static ConfigurablePose TERMINAL = new ConfigurablePose(61, -64, toRadians(180));
        // These are 'trajectory pieces' which should be named like this:
        // {STARTING_POSITION}_TO_{ENDING_POSITION}
        public static final TrajectoryPath
                TELESTART_TO_FORWARD_MOVE = TrajectoryPath.lineToLinearHeading(TELESTART, FORWARD_MOVE),
                TELESTART_TO_BACKWARD_MOVE = TrajectoryPath.lineToLinearHeading(TELESTART, BACKWARD_MOVE),
                TELESTART_TO_LEFT_MOVE = TrajectoryPath.lineToLinearHeading(TELESTART, LEFT_MOVE),
                TELESTART_TO_RIGHT_MOVE = TrajectoryPath.lineToLinearHeading(TELESTART, RIGHT_MOVE);
        //Testing Straights
        public static final TrajectoryPath
                START_TO_STRAIGHT = TrajectoryPath.lineTo(START, STRAIGHT),
                STRAIGHT_TO_START = TrajectoryPath.lineTo(STRAIGHT, START),
                START_TO_LEFTSIDE = TrajectoryPath.lineTo(START, LEFTSIDE),
                LEFTSIDE_TO_START = TrajectoryPath.lineTo(LEFTSIDE, START);
        public static final TrajectoryPath
                START_TO_W_JUNCTION = TrajectoryPath.linesToLinearHeadings(START, BETWEEN_START_W_JUNCTION, W_JUNCTION_ONE),
                W_JUNCTION_TO_STACK_ONE = TrajectoryPath.linesToLinearHeadings(W_JUNCTION_ONE, BETWEEN_W_JUNCTION_STACK, STACK_ONE),
                W_JUNCTION_TO_STACK_TWO = TrajectoryPath.linesToLinearHeadings(W_JUNCTION_TWO, BETWEEN_W_JUNCTION_STACK, STACK_TWO),
                W_JUNCTION_TO_STACK_THREE = TrajectoryPath.linesToLinearHeadings(W_JUNCTION_THREE, BETWEEN_W_JUNCTION_STACK, STACK_THREE),
                STACK_TO_W_JUNCTION_ONE = TrajectoryPath.linesToLinearHeadings(STACK_ONE, BETWEEN_STACK_W_JUNCTION, W_JUNCTION_ONE),
                STACK_TO_W_JUNCTION_TWO = TrajectoryPath.linesToLinearHeadings(STACK_TWO, BETWEEN_STACK_W_JUNCTION, W_JUNCTION_TWO),
                STACK_TO_W_JUNCTION_THREE = TrajectoryPath.linesToLinearHeadings(STACK_THREE, BETWEEN_STACK_W_JUNCTION, W_JUNCTION_THREE),
                W_JUNCTION_TO_LEFT_PARK = TrajectoryPath.linesToLinearHeadings(W_JUNCTION_THREE, BETWEEN_STACK_W_JUNCTION, LEFT),
                W_JUNCTION_TO_MIDDLE_PARK = TrajectoryPath.linesToLinearHeadings(W_JUNCTION_THREE, BETWEEN_STACK_W_JUNCTION, MIDDLE),
                W_JUNCTION_TO_RIGHT_PARK = TrajectoryPath.linesToLinearHeadings(W_JUNCTION_THREE, BETWEEN_STACK_W_JUNCTION, RIGHT);
        public static final TrajectoryPath
                START_TO_LEFT_PARK = TrajectoryPath.linesToLinearHeadings(START, /* TERMINAL, */ BETWEEN_START_LEFT, LEFT),
                START_TO_MIDDLE_PARK = TrajectoryPath.linesToLinearHeadings(START, /* TERMINAL, START, */ MIDDLE),
                START_TO_RIGHT_PARK = TrajectoryPath.linesToLinearHeadings(START, /* TERMINAL, */ BETWEEN_START_RIGHT, RIGHT);

    }

    @Config
    public static class Left {
        public static ConfigurablePose START = new ConfigurablePose(-36, -66, toRadians(90));
        public static ConfigurablePose E_JUNCTION = new ConfigurablePose(-29, -10, 1.1);
        public static ConfigurablePose E_JUNCTION_2 = new ConfigurablePose(-31, -8, 1.1);
        public static ConfigurablePose STACK = new ConfigurablePose(-62, -17, toRadians(180));
        public static ConfigurablePose STACK2 = new ConfigurablePose(-65, -17, toRadians(180));
        public static ConfigurablePose LEFT = new ConfigurablePose(-59, -20, toRadians(90));
        public static ConfigurablePose LEFT2 = new ConfigurablePose(-58, -18, toRadians(180));
        //-58,-20,90
        public static ConfigurablePose MIDDLE = new ConfigurablePose(-35, -17, toRadians(90));
        public static ConfigurablePose RIGHT = new ConfigurablePose(-16, -15, toRadians(90));
        public static ConfigurablePose BETWEEN_START_E_JUNCTION = new ConfigurablePose(-36, -12, 5.9);
        public static ConfigurablePose BETWEEN_E_JUNCTION_STACK = new ConfigurablePose(-36, -14, 3.14159);
        public static ConfigurablePose BETWEEN_STACK_E_JUNCTION = new ConfigurablePose(-38, -13, 1.3089969389957472);
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
                STACK_TO_E_JUNCTION_TWO =
                        b -> b.apply(STACK.toPose())
                                .lineToLinearHeading(BETWEEN_STACK_E_JUNCTION.toPose())
                                .lineToLinearHeading(E_JUNCTION_2.toPose())
                                .build(),
                E_JUNCTION_TO_STACK_TWO =
                        b -> b.apply(E_JUNCTION_2.toPose())
                                .lineToLinearHeading(BETWEEN_E_JUNCTION_STACK.toPose())
                                .lineToLinearHeading(STACK2.toPose())
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
                LEFT_1_2 =
                        b -> b.apply(LEFT.toPose())
                                .lineToLinearHeading(LEFT2.toPose())
                                .lineToLinearHeading(LEFT.toPose())
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
