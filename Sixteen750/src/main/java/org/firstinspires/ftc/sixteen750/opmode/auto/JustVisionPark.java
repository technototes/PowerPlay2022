package org.firstinspires.ftc.sixteen750.opmode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.hardware2.HardwareBuilder;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.command.autonomous.StartingPosition;
import org.firstinspires.ftc.sixteen750.subsystem.SimpleMecanumDriveSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.VisionSubsystem;

@Config
@Autonomous(name = "JustVisionPark")
public class JustVisionPark extends LinearOpMode {
    public static double DEFAULT_POWER = 0.3;


    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBuilder.initMap(hardwareMap);
        Hardware hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.VISION_DRIVE);
        SimpleMecanumDriveSubsystem drive = new SimpleMecanumDriveSubsystem(hardware);
        drive.setEncoderZero();
        VisionSubsystem vision = new VisionSubsystem(hardware.camera, Alliance.BLUE, StartingPosition.AWAY);
        vision.startVisionPipeline();

        boolean parkLeft = vision.visionPipeline.left();
        boolean parkRight = vision.visionPipeline.right();

        waitForStart();

        if (isStopRequested()) return;

        while (!isStopRequested() && opModeIsActive() && Math.abs(drive.getAdjustedEncoderValues()[0]) < SimpleMecanumDriveSubsystem.autoGoForwardTicks) {
            drive.goStraightForward(DEFAULT_POWER);

            telemetry.addData("Left Front Encoder", drive.getAdjustedEncoderValues()[0]);
            telemetry.addData("Left Rear Encoder", drive.getAdjustedEncoderValues()[1]);
            telemetry.addData("Right Front Encoder", drive.getAdjustedEncoderValues()[2]);
            telemetry.addData("Right Rear Encoder", drive.getAdjustedEncoderValues()[3]);

            telemetry.update();
        }

        drive.stopAndWait();
        System.out.println("Forward Auto Finished");

        drive.setEncoderZero();

        if (parkLeft) {
            System.out.println("Parking Left");
            while (!isStopRequested() && opModeIsActive() && Math.abs(drive.getAdjustedEncoderValues()[0]) < SimpleMecanumDriveSubsystem.autoGoLeftTicks) {
                drive.goLeft(DEFAULT_POWER);

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
        else if (parkRight) {
            System.out.println("Parking Right");
            while (!isStopRequested() && opModeIsActive() && Math.abs(drive.getAdjustedEncoderValues()[0]) < SimpleMecanumDriveSubsystem.autoGoRightTicks) {
                drive.goRight(DEFAULT_POWER);

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
        else {
            System.out.println("Staying in the middle");
        }

        drive.stop();

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

        vision.stopVisionPipeline();
        System.out.println("Auto Finished");
    }
}
