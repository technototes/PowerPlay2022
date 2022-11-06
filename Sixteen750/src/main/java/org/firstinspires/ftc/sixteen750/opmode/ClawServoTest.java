package org.firstinspires.ftc.sixteen750.opmode;

import org.firstinspires.ftc.sixteen750.ControlDriver;
import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.structure.CommandOpMode;

@Config
@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class ClawServoTest extends CommandOpMode {
    public Robot robot;
    public ControlDriver controls;
    public Hardware hardware;

    Servo targetServo;

    // clockwise is order
    public static double upBtnServoPosition = 0.2;
    public static double rightBtnServoPosition = 0.4;
    public static double downBtnServoPosition = 0.6;
    public static double leftBtnServoPosition = 0.8;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        controls = new ControlDriver(driverGamepad, robot);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        targetServo = hardware.elbowServo;
    }

    @Override
    public void runLoop() {
        /*
         * GoBilda Super Speed Servo
         * Min: 0.4
         * Firmly grab: 0.45
         * Open: 0.6
         *
         * 25 KG Servo
         * 1: Fully Open
         * 0: Fully closed
         * Ideal Range: 0.5-0.6
         * */
        if (gamepad1.dpad_up) {
            targetServo.setPosition(upBtnServoPosition); // better not to go below this value
        } else if (gamepad1.dpad_right) {
            targetServo.setPosition(rightBtnServoPosition);
        } else if (gamepad1.dpad_down) {
            targetServo.setPosition(downBtnServoPosition);
        } else if (gamepad1.dpad_left) {
            targetServo.setPosition(leftBtnServoPosition);
        } else if (gamepad1.square) {
            targetServo.setPosition(1);
        } else if (gamepad1.circle) {
            targetServo.setPosition(0);
        } else if (gamepad1.right_bumper) {
            hardware.clawServo.setPosition(0.5);
        } else if (gamepad1.left_bumper) {
            hardware.clawServo.setPosition(0.6);
        } else if (gamepad1.triangle) {
            hardware.flipperServo.setPosition(0.17);
        }

        telemetry.addData("Claw Servo Position", robot.clawSubsystem.getClawPosition());
        telemetry.addData("Flipper Servo Position", robot.clawSubsystem.getFlipperPosition());
        telemetry.addData("Elbow Servo Position", robot.clawSubsystem.getElbowPosition());
        telemetry.update();
    }
}
