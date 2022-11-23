package org.firstinspires.ftc.sixteen750.opmode.diagnosis;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;

public class MecanumInfinityStraightTest extends LinearOpMode {
    public static double DISTANCE = 20; // in
    int loopCount = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        Hardware hardware = new Hardware(hardwareMap, true, false, false, false);
        Robot robot = new Robot(hardware, true, false, false, false);

        waitForStart();

        if (isStopRequested()) return;

        while (!isStopRequested() && opModeIsActive()) {
            telemetry.addData("Loop Count", 0);
            telemetry.update();
            loopCount++;
        }
    }
}
