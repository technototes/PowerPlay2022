package org.firstinspires.ftc.sixteen750.command.autonomous;

import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.Function;

public class AutoConstantBlue {
    public static class Home {
        public static Pose2d START = new Pose2d(36, -66, toRadians(-90));
        public static Pose2d JUNCTION = new Pose2d(28, -4, toRadians(135));
        public static Pose2d STACK = new Pose2d(62, -12, toRadians(0));
        public static Pose2d PARK_LEFT = new Pose2d(12, -36, toRadians(-90));
        public static Pose2d PARK_MIDDLE = new Pose2d(36, -36, toRadians(-90));
        public static Pose2d PARK_RIGHT = new Pose2d(60, -36, toRadians(180));

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>

            START_TO_WEST_JUNCTION = b->b.apply(START).lineToLinearHeading(JUNCTION).build(),
            //START_TO_SOUTH_JUNCTION = b->b.apply(START).lineToLinearHeading(SOUTH_JUNCTION).build(),
            WEST_JUNCTION_TO_STACK = b->b.apply(JUNCTION).lineToLinearHeading(STACK).build(),
            //SOUTH_JUNCTION_TO_STACK = b->b.apply(JUNCTION).lineToLinearHeading(STACK).build(),
            STACK_TO_WEST_JUNCTION = b->b.apply(STACK).lineToLinearHeading(JUNCTION).build(),
            //STACK_TO_SOUTH_JUNCTION = b->b.apply(STACK).lineToLinearHeading(JUNCTION).build(),
            WEST_JUNCTION_TO_PARK_LEFT = b->b.apply(JUNCTION).lineToLinearHeading(PARK_LEFT).build(),
            WEST_JUNCTION_TO_PARK_MIDDLE = b->b.apply(JUNCTION).lineToLinearHeading(PARK_MIDDLE).build(),
            WEST_JUNCTION_TO_PARK_RIGHT = b->b.apply(JUNCTION).lineToLinearHeading(PARK_RIGHT).build();
            //SOUTH_JUNCTION_TO_PARK_LEFT = b -> b.apply(JUNCTION).lineToLinearHeading(PARK_LEFT).build()
            //SOUTH_JUNCTION_TO_PARK_MIDDLE = b -> b.apply(JUNCTION).lineToLinearHeading(PARK_MIDDLE).build()
            //SOUTH_JUNCTION_TO_PARK_RIGHT = b -> b.apply(JUNCTION).lineToLinearHeading(PARK_RIGHT).build()

    }

    public static class Away {
        public static Pose2d START = new Pose2d(-36, -66, toRadians(90));
        public static Pose2d JUNCTION = new Pose2d(-28, -4, toRadians(180));
        public static Pose2d STACK = new Pose2d(-62, -12, toRadians(180));
        public static Pose2d PARK_LEFT = new Pose2d(-60, -36, toRadians(0));
        public static Pose2d PARK_MIDDLE = new Pose2d(-36, -36, toRadians(-90));
        public static Pose2d PARK_RIGHT = new Pose2d(-12, -36, toRadians(-90));
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
            START_TO_NINEOCLOCK_JUNCTION=
                b -> b.apply(START).lineToLinearHeading(JUNCTION).build(),
            //START_TO_SIXOCLOCK_JUNCTION=
                 //   b -> b.apply(START).lineToLinearHeading().build()
            NINEOCLOCK_TO_STACK=
                    b -> b.apply(JUNCTION).lineToLinearHeading(STACK).build(),
            STACK_TO_NINEOCLOCK_JUNCTION=
                    b -> b.apply(STACK).lineToLinearHeading(JUNCTION).build(),
            //STACK_TO_SIXOCLOCK_JUNCTION=
                    //b -> b.apply(STACK).lineToLinearHeading().build(),
            NINEOCLOCK_JUNCTION_TO_PARK_LEFT=
                    b -> b.apply(JUNCTION).lineToLinearHeading(PARK_LEFT).build(),
            NINEOCLOCK_JUNCTION_TO_PARK_RIGHT=
                    b -> b.apply(JUNCTION).lineToLinearHeading(PARK_RIGHT).build(),
            NINEOCLOCK_JUNCTION_TO_PARK_MIDDLE=
                    b -> b.apply(JUNCTION).lineToLinearHeading(PARK_MIDDLE).build(),
            SIXOCLOCK_JUNCTION_TO_PARK_LEFT=
                    b -> b.apply(JUNCTION).lineToLinearHeading(PARK_LEFT).build(),
            SIXOCLOCK_JUNCTION_TO_PARK_RIGHT=
                    b -> b.apply(JUNCTION).lineToLinearHeading(PARK_RIGHT).build(),
            SIXOCLOCK_JUNCTION_TO_PARK_MIDDLE=
                    b -> b.apply(JUNCTION).lineToLinearHeading(PARK_MIDDLE).build();
    }

}
