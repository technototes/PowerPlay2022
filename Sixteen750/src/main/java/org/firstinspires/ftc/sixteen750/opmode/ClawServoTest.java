package org.firstinspires.ftc.sixteen750.opmode;

import static org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem.CLAW_CLOSE;
import static org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem.CLAW_OPEN;
import static org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem.ELBOW_UPWARD;
import static org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem.FLIPPER_NORMAL;

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
            hardware.clawServo.setPosition(CLAW_CLOSE);
        } else if (gamepad1.left_bumper) {
            hardware.clawServo.setPosition(CLAW_OPEN);
        } else if (gamepad1.triangle) {
            hardware.flipperServo.setPosition(FLIPPER_NORMAL);
        } else if (gamepad1.x) {
            hardware.elbowServo.setPosition(ELBOW_UPWARD);
        }

        telemetry.addData("Claw Servo Position", robot.clawSubsystem.getClawPosition());
        telemetry.addData("Flipper Servo Position", robot.clawSubsystem.getFlipperPosition());
        telemetry.addData("Elbow Servo Position", robot.clawSubsystem.getElbowPosition());
        telemetry.update();
    }
}
