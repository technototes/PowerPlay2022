package org.firstinspires.ftc.swerveteen750.opmode.diagnosis;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.TankDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.swerve_util.TrajectorySequence;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Config
@Autonomous(group = "Test-Path")
@SuppressWarnings("unused")
public class TankInfinityStraightTest extends LinearOpMode {
    public static double DISTANCE = 20; // in
    int loopCount = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        TankDriveSubsystem drive = new TankDriveSubsystem(hardwareMap);

        TrajectorySequence trajectory = drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                .forward(DISTANCE)
                .back(DISTANCE)
                .build();

        waitForStart();

        if (isStopRequested()) return;

        while (!isStopRequested() && opModeIsActive()) {
            drive.followTrajectorySequence(trajectory);
            loopCount++;
        }
    }
}
