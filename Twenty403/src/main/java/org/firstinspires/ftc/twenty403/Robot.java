package org.firstinspires.ftc.twenty403;

import com.acmerobotics.dashboard.config.Config;

import com.technototes.library.logger.Loggable;

public class Robot implements Loggable {
    @Config
    public static class RobotConstant {
        public static boolean DRIVE_CONNECTED = false;
    }

    public Robot(Hardware hardware) {}
}
