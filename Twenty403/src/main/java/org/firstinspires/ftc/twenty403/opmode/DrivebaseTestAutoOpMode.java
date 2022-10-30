package org.firstinspires.ftc.twenty403.opmode;

import org.firstinspires.ftc.twenty403.Controls;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoTestDrivebaseCommand;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;

@Autonomous(name = "Drive Base Test Auto")
public class DrivebaseTestAutoOpMode extends CommandOpMode {
    public Robot robot;
    public Controls controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        robot.drivebaseSubsystem.setPoseEstimate(new Pose2d(0, 0, 0));
        CommandScheduler.getInstance()
                .scheduleForState(
                        new AutoTestDrivebaseCommand(robot.drivebaseSubsystem), CommandOpMode.OpModeState.RUN);
    }
}
