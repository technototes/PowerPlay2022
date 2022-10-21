package org.firstinspires.ftc.twenty403.command.autonomous;

import static java.lang.Math.toRadians;

import java.util.function.Function;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

public class AutoConstantsBlue {
    public static class Home {
        public static Pose2d START = new Pose2d(36, -66, toRadians(-90));
        public static Pose2d JUNCTION = new Pose2d(28, -4, toRadians(135));
        public static Pose2d STACK = new Pose2d(62, -12, toRadians(0));
        public static Pose2d PARK_LEFT = new Pose2d(12, -36, toRadians(-90));
        public static Pose2d PARK_MIDDLE = new Pose2d(36, -36, toRadians(-90));
        public static Pose2d PARK_RIGHT = new Pose2d(60, -36, toRadians(180));

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                START_TO_JUNCTION = b -> b.apply(START).build();
    }

    public static class Away {
        public static Pose2d START = new Pose2d(-36, -66, toRadians(90));
        public static Pose2d JUNCTION = new Pose2d(-28, -4, toRadians(180));
        public static Pose2d STACK = new Pose2d(-62, -12, toRadians(180));
        public static Pose2d PARK_LEFT = new Pose2d(-60, -36, toRadians(0));
        public static Pose2d PARK_MIDDLE = new Pose2d(-36, -36, toRadians(-90));
        public static Pose2d PARK_RIGHT = new Pose2d(-12, -36, toRadians(-90));
    }
}
