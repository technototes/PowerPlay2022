package org.firstinspires.ftc.sixteen750.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.hardware2.HardwareBuilder;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.subsystem.drive.SimpleMecanumDriveSubsystem;

@Disabled
@TeleOp(group = "Mecanum")
public class SketchyMecanumDriveMode extends LinearOpMode {
    public static double DEFAULT_POWER = 0.4;

    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBuilder.initMap(hardwareMap);
        Hardware hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.M_DRIVE_ONLY);
        SimpleMecanumDriveSubsystem drive = new SimpleMecanumDriveSubsystem(hardware);
        drive.setEncoderZero();

        waitForStart();

        if (isStopRequested()) return;

        while (!isStopRequested() && opModeIsActive()) {
            if (gamepad1.dpad_up){
                drive.goStraightForward(DEFAULT_POWER);
            } else if (gamepad1.dpad_down){
                drive.goStraightBackward(DEFAULT_POWER);
            } else if (gamepad1.dpad_left){
                drive.goLeft(DEFAULT_POWER);
            } else if (gamepad1.dpad_right){
                drive.goRight(DEFAULT_POWER);
            } else if (gamepad1.left_trigger > 0.1){
                drive.turnAroundClockwise(DEFAULT_POWER);
            } else if (gamepad1.right_trigger > 0.1){
                drive.turnAroundCounterClockwise(DEFAULT_POWER);
            } else if (gamepad1.triangle){
                drive.goDiagonal45(DEFAULT_POWER);
            } else if (gamepad1.circle){
                drive.goDiagonal135(DEFAULT_POWER);
            } else if (gamepad1.x){
                drive.goDiagonal225(DEFAULT_POWER);
            } else if (gamepad1.square){
                drive.goDiagonal315(DEFAULT_POWER);
            } else {
                drive.stop();
            }

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
    }
}
