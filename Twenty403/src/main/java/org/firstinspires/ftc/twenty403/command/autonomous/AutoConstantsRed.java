package org.firstinspires.ftc.twenty403.command.autonomous;

import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class AutoConstantsRed {
    // Home
    public static Pose2d HOME_START = new Pose2d(36, -66, toRadians(-90));
    public static Pose2d HOME_JUNCTION = new Pose2d(28, 4, toRadians(-135));
    public static Pose2d HOME_STACK = new Pose2d(62, 12, toRadians(0));
    public static Pose2d HOME_LEFT = new Pose2d(60, 36, toRadians(180));
    public static Pose2d HOME_MIDDLE = new Pose2d(36, 36, toRadians(90));
    public static Pose2d HOME_RIGHT = new Pose2d(12, 36, toRadians(90));

    // Away
    public static Pose2d AWAY_START = new Pose2d(-36, 66, toRadians(-90));
    public static Pose2d AWAY_JUNCTION = new Pose2d(-28, 4, toRadians(-45));
    public static Pose2d AWAY_STACK = new Pose2d(-62, 12, toRadians(180));
    public static Pose2d AWAY_LEFT = new Pose2d(-12, 36, toRadians(90));
    public static Pose2d AWAY_MIDDLE = new Pose2d(-36, 36, toRadians(90));
    public static Pose2d AWAY_RIGHT = new Pose2d(-60, 36, toRadians(0));
}
