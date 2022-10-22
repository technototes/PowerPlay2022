package org.firstinspires.ftc.sixteen750.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.sixteen750.subsystem.SwerveDrivebaseSubsystem;
import org.firstinspires.ftc.sixteen750.swerve_util.TrajectorySequence;

@Config
@Autonomous(group = "drive")
public class InfinityStraightTest extends LinearOpMode {
    public static double DISTANCE = 100; // in
    int loopCount = 0;

    public Integer updateCallback(SwerveDrivebaseSubsystem drive, Telemetry telemetry){
        Pose2d poseEstimate = drive.getPoseEstimate();
        telemetry.addData("X", poseEstimate.getX());
        telemetry.addData("Y", poseEstimate.getY());
        telemetry.addData("Heading", poseEstimate.getHeading());
        telemetry.addData("Loop", loopCount);
        telemetry.addData("LeftFrontTargetOrientation", drive.leftFrontModuleTargetOrientation);
        telemetry.addData("LeftFrontCurrentOrientation", drive.leftFrontModuleCurrentOrientation);
        telemetry.addData("LeftRearTargetOrientation", drive.leftRearModuleTargetOrientation);
        telemetry.addData("LeftRearCurrentOrientation", drive.leftRearModuleCurrentOrientation);
        telemetry.addData("RightFrontTargetOrientation", drive.rightFrontModuleTargetOrientation);
        telemetry.addData("RightFrontCurrentOrientation", drive.rightFrontModuleCurrentOrientation);
        telemetry.addData("RightRearTargetOrientation", drive.rightRearModuleTargetOrientation);
        telemetry.addData("RightRearCurrentOrientation", drive.rightRearModuleCurrentOrientation);
        telemetry.update();
        return 0;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        SwerveDrivebaseSubsystem drive = new SwerveDrivebaseSubsystem(hardwareMap);

        TrajectorySequence trajectory = drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                .forward(DISTANCE)
                .back(DISTANCE)
                .build();

        drive.updateCallback = d -> updateCallback(d, telemetry);

        waitForStart();

        drive.enableDiagnoseTelemetry(telemetry, false);
        drive.startIMUThread(this);
        if (isStopRequested()) return;

        while (!isStopRequested() && opModeIsActive()) {
            drive.followTrajectorySequence(trajectory);
            loopCount++;
        }
    }
}
