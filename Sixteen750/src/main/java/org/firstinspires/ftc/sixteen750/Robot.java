package org.firstinspires.ftc.sixteen750;

import java.util.ArrayList;

import com.acmerobotics.dashboard.config.Config;

import com.technototes.library.logger.Loggable;
import com.technototes.path.trajectorysequence.TrajectorySequence;

public class Robot implements Loggable {
    @Config
    public static class RobotConstant {
        public static boolean DRIVE_CONNECTED = false;
    }

    public static class Trajectories {
        public static TrajectorySequence BLUE_AWAY_STACK = new TrajectorySequence(new ArrayList<>());
        public static TrajectorySequence BLUE_HOME_STACK = new TrajectorySequence(new ArrayList<>());
        public static TrajectorySequence HIGH_JUNCTION_HOME = new TrajectorySequence(new ArrayList<>());
        public static TrajectorySequence HIGH_JUNCTION_AWAY = new TrajectorySequence(new ArrayList<>());
        public static TrajectorySequence PARK_AWAY = new TrajectorySequence(new ArrayList<>());
        public static TrajectorySequence PARK_HOME = new TrajectorySequence(new ArrayList<>());
        public static TrajectorySequence CONESTACK_HOME = new TrajectorySequence(new ArrayList<>());
    }

    public Robot(Hardware hardware) {
    }
}
