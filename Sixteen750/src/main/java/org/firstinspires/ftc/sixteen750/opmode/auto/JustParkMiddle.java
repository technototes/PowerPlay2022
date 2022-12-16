package org.firstinspires.ftc.sixteen750.opmode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.technototes.library.hardware2.HardwareBuilder;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.subsystem.drive.SimpleMecanumDriveSubsystem;

@Config
@Autonomous(name = "JustParkMiddle")
public class JustParkMiddle extends LinearOpMode {
    public static double DEFAULT_POWER = 0.3;
    public static int goForwardTicks = 2100;

    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBuilder.initMap(hardwareMap);
        Hardware hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.M_DRIVE_ONLY);
        SimpleMecanumDriveSubsystem drive = new SimpleMecanumDriveSubsystem(hardware);
        drive.setEncoderZero();

        waitForStart();

        if (isStopRequested()) return;

        while (!isStopRequested() && opModeIsActive() && drive.getAdjustedEncoderValues()[0] < goForwardTicks) {
            drive.goStraightForward(DEFAULT_POWER);

            telemetry.addData("Left Front Encoder - Real", drive.getEncoderValues()[0]);
            telemetry.addData("Left Rear Encoder - Real", drive.getEncoderValues()[1]);
            telemetry.addData("Right Front Encoder - Real", drive.getEncoderValues()[2]);
            telemetry.addData("Right Rear Encoder - Real", drive.getEncoderValues()[3]);

            telemetry.addData("Left Front Encoder", drive.getAdjustedEncoderValues()[0]);
            telemetry.addData("Left Rear Encoder", drive.getAdjustedEncoderValues()[1]);
            telemetry.addData("Right Front Encoder", drive.getAdjustedEncoderValues()[2]);
            telemetry.addData("Right Rear Encoder", drive.getAdjustedEncoderValues()[3]);

            telemetry.update();
        }

        drive.stop();
        System.out.println("Forward Auto Finished");

        while (!isStopRequested() && opModeIsActive()) {
            telemetry.addData("Left Front Encoder - Real", drive.getEncoderValues()[0]);
            telemetry.addData("Left Rear Encoder - Real", drive.getEncoderValues()[1]);
            telemetry.addData("Right Front Encoder - Real", drive.getEncoderValues()[2]);
            telemetry.addData("Right Rear Encoder - Real", drive.getEncoderValues()[3]);

            telemetry.addData("Left Front Encoder", drive.getAdjustedEncoderValues()[0]);
            telemetry.addData("Left Rear Encoder", drive.getAdjustedEncoderValues()[1]);
            telemetry.addData("Right Front Encoder", drive.getAdjustedEncoderValues()[2]);
            telemetry.addData("Right Rear Encoder", drive.getAdjustedEncoderValues()[3]);

            telemetry.update();
        }
    }
}
