package org.firstinspires.ftc.sixteen750.opmode;

import static org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem.CLAW_CLOSE;
import static org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem.CLAW_OPEN;
import static org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem.ELBOW_INTAKE;
import static org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem.ELBOW_UPWARD;
import static org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem.FLIPPER_UPPER_NORMAL;

import org.firstinspires.ftc.sixteen750.DriverControls;
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
    public DriverControls controls;
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
        controls = new DriverControls(driverGamepad, robot);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        targetServo = hardware.elbowServo;
    }

    @Override
    public void runLoop() {
        if (gamepad1.dpad_up) {
            hardware.elbowServo.setPosition(upBtnServoPosition); // better not to go below this value
        } else if (gamepad1.dpad_right) {
            hardware.elbowServo.setPosition(rightBtnServoPosition);
        } else if (gamepad1.dpad_down) {
            hardware.elbowServo.setPosition(downBtnServoPosition);
        } else if (gamepad1.dpad_left) {
            hardware.elbowServo.setPosition(leftBtnServoPosition);
        } else if (gamepad1.square) {
            hardware.elbowServo.setPosition(1);
        } else if (gamepad1.circle) {
            hardware.elbowServo.setPosition(0);
        } else if (gamepad1.right_bumper) {
            hardware.clawServo.setPosition(CLAW_CLOSE);
        } else if (gamepad1.left_bumper) {
            hardware.clawServo.setPosition(CLAW_OPEN);
        } else if (gamepad1.triangle) {
            hardware.elbowServo.setPosition(ELBOW_UPWARD);
        } else if (gamepad1.x) {
            hardware.elbowServo.setPosition(ELBOW_INTAKE);
        }

        if (gamepad2.dpad_up) {
            hardware.flipperServo.setPosition(upBtnServoPosition);
        } else if (gamepad2.dpad_right) {
            hardware.flipperServo.setPosition(rightBtnServoPosition);
        } else if (gamepad2.dpad_down) {
            hardware.flipperServo.setPosition(downBtnServoPosition);
        } else if (gamepad2.dpad_left) {
            hardware.flipperServo.setPosition(leftBtnServoPosition);
        } else if (gamepad2.square) {
            hardware.flipperServo.setPosition(1);
        } else if (gamepad2.circle) {
            hardware.flipperServo.setPosition(0);
        } else if (gamepad2.triangle) {
            hardware.flipperServo.setPosition(FLIPPER_UPPER_NORMAL);
        } else if (gamepad2.x) {

        }

        telemetry.addData("Claw Servo Position", robot.clawSubsystem.getClawPosition());
        telemetry.addData("Flipper Servo Position", robot.clawSubsystem.getFlipperPosition());
        telemetry.addData("Elbow Servo Position", robot.clawSubsystem.getElbowPosition());
        telemetry.update();
    }
}
