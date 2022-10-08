package org.firstinspires.ftc.sixteen750.command.autonomous;

import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class AutoConstantBlue {
    // Home
    public static Pose2d HOME_START = new Pose2d(36, -66, toRadians(-90));
    public static Pose2d HOME_JUNCTION = new Pose2d(28, -4, toRadians(135));
    public static Pose2d HOME_STACK = new Pose2d(62, -12, toRadians(0));
    public static Pose2d HOME_PARK_LEFT = new Pose2d(12, -36, toRadians(-90));
    public static Pose2d HOME_PARK_MIDDLE = new Pose2d(36, -36, toRadians(-90));
    public static Pose2d HOME_PARK_RIGHT = new Pose2d(60, -36, toRadians(180));

    // Away
    public static Pose2d AWAY_START = new Pose2d(-36, -66, toRadians(90));
    public static Pose2d AWAY_JUNCTION = new Pose2d(-28, -4, toRadians(180));
    public static Pose2d AWAY_STACK = new Pose2d(-62, -12, toRadians(180));
    public static Pose2d AWAY_PARK_LEFT = new Pose2d(-60, -36, toRadians(0));
    public static Pose2d AWAY_PARK_MIDDLE = new Pose2d(-36, -36, toRadians(-90));
    public static Pose2d AWAY_PARK_RIGHT = new Pose2d(-12, -36, toRadians(-90));
}
