package org.firstinspires.ftc.twenty403.opmode;

import org.firstinspires.ftc.twenty403.Controls.Controls;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;
import com.technototes.path.command.TrajectorySequenceCommand;

@Autonomous(name = "Straight Test")
@SuppressWarnings("unused")
public class StraightTest extends CommandOpMode {
    public Robot robot;
    public Controls controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.NONE, StartingPosition.RIGHT);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstants.Right.START.toPose());
        CommandScheduler.getInstance()
                .scheduleForState(
                        new SequentialCommandGroup(
                                new TrajectorySequenceCommand(
                                        robot.drivebaseSubsystem, AutoConstants.Right.START_TO_LEFTSIDE),
                                new TrajectorySequenceCommand(
                                        robot.drivebaseSubsystem, AutoConstants.Right.LEFTSIDE_TO_START)),
                        CommandOpMode.OpModeState.RUN);
    }

    @Override
    public void runLoop() {
        telemetry.addData("Heading", robot.drivebaseSubsystem.getExternalHeading());
        telemetry.addData("Pose", robot.drivebaseSubsystem.getPoseEstimate());
        System.out.println("Pose: " + robot.drivebaseSubsystem.getPoseEstimate());
        System.out.println("Wheel positions: " + robot.drivebaseSubsystem.getWheelPositions());
        telemetry.addData("Wheel positions", robot.drivebaseSubsystem.getWheelPositions());
        telemetry.update();
    }
}
