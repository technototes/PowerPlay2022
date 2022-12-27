package org.firstinspires.ftc.swerveteen750.opmode.diagnosis;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.technototes.library.util.Alliance;
import com.technototes.path.trajectorysequence.TrajectorySequence;

import org.firstinspires.ftc.swerveteen750.Hardware;
import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.command.autonomous.StartingPosition;

@Disabled
@Config
@Autonomous(group = "Test-Path")
@SuppressWarnings("unused")
public class MecanumInfinityStraightTest extends LinearOpMode {
    public static double DISTANCE = 20; // in
    int loopCount = 0;
    Pose2d lastPoseEstimate;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        Hardware hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.M_DRIVE_ONLY);
        Robot robot = new Robot(hardwareMap, hardware, Robot.SubsystemCombo.M_DRIVE_ONLY, Alliance.NONE, StartingPosition.NEUTRAL);

        TrajectorySequence backandFourthTrajectory = robot.mecanumDriveSubsystem
                .trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                .forward(DISTANCE)
                .back(DISTANCE)
                .build();

        waitForStart();

        if (isStopRequested()) return;

        while (!isStopRequested() && opModeIsActive()) {
            robot.mecanumDriveSubsystem.followTrajectorySequence(backandFourthTrajectory);
            lastPoseEstimate = robot.mecanumDriveSubsystem.getPoseEstimate();
            telemetry.addData("Loop Count", loopCount);
            telemetry.addData("Last Pose Estimate", lastPoseEstimate);
            telemetry.update();
            loopCount++;
        }
    }
}
