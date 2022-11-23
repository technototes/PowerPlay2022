package org.firstinspires.ftc.sixteen750.opmode.diagnosis;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.technototes.path.trajectorysequence.TrajectorySequence;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;

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
        Hardware hardware = new Hardware(hardwareMap, true, false, false, false);
        Robot robot = new Robot(hardware, true, false, false, false);

        TrajectorySequence backandFourthTrajectory = robot.mecanumDriveSubsystem.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                .forward(DISTANCE)
                .back(DISTANCE)
                .build();

        waitForStart();

        if (isStopRequested()) return;

        while (!isStopRequested() && opModeIsActive()) {
            robot.mecanumDriveSubsystem.followTrajectorySequence(backandFourthTrajectory);
            lastPoseEstimate = robot.mecanumDriveSubsystem.getPoseEstimate();
            telemetry.addData("Loop Count", 0);
            telemetry.addData("Last Pose Estimate", lastPoseEstimate);
            telemetry.update();
            loopCount++;
        }
    }
}
