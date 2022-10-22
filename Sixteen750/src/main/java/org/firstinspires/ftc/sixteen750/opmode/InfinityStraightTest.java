package org.firstinspires.ftc.sixteen750.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.sixteen750.subsystem.SwerveDrivebaseSubsystem;
import org.firstinspires.ftc.sixteen750.swerve_util.TrajectorySequence;

@Config
@Autonomous(group = "drive")
public class InfinityStraightTest extends LinearOpMode {
    public static double DISTANCE = 30; // in
    int loopCount = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        SwerveDrivebaseSubsystem drive = new SwerveDrivebaseSubsystem(hardwareMap);

        TrajectorySequence trajectory = drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                .forward(DISTANCE)
                .back(DISTANCE)
                .build();

        waitForStart();
        drive.startIMUThread(this);
        if (isStopRequested()) return;

        while (!isStopRequested() && opModeIsActive()) {
            drive.followTrajectorySequence(trajectory);

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("finalX", poseEstimate.getX());
            telemetry.addData("finalY", poseEstimate.getY());
            telemetry.addData("finalHeading", poseEstimate.getHeading());
            telemetry.addData("Loop", loopCount);
            telemetry.update();
            loopCount++;
        }
    }
}
