package org.firstinspires.ftc.sixteen750.opmode.diagnosis;

import static org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem._ELBOW_INTAKE_FLIPPER;
import static org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem._ELBOW_SCORE;
import static org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem._ELBOW_SCORE_FLIPPER;
import static org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem._ELBOW_UPWARD;
import static org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem._ELBOW_INTAKE;
import static org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem._ELBOW_UPWARD_FLIPPER;
import static org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem.CLAW_CLOSE;
import static org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem.CLAW_OPEN;

import org.firstinspires.ftc.sixteen750.ControlsDriver;
import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.command.autonomous.StartingPosition;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

@Config
@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class ClawArmServoTest extends CommandOpMode {
    public Robot robot;
    public ControlsDriver controls;
    public Hardware hardware;

    Servo targetServo;

    // clockwise is order
    public static double upBtnServoPosition = 0.2;
    public static double rightBtnServoPosition = 0.4;
    public static double downBtnServoPosition = 0.6;
    public static double leftBtnServoPosition = 0.8;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap, false, false, true, true, false);
        robot = new Robot(hardware, Robot.SubsystemCombo.ARM_CLAW_ONLY, Alliance.NONE, StartingPosition.NEUTRAL);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        targetServo = hardware.flipperServo;
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
        } else if (gamepad1.right_bumper) {
            hardware.clawServo.setPosition(CLAW_CLOSE);
        } else if (gamepad1.left_bumper) {
            hardware.clawServo.setPosition(CLAW_OPEN);
        } else if (gamepad1.square) {
            hardware.elbowServo.setPosition(_ELBOW_SCORE);
            hardware.flipperServo.setPosition(_ELBOW_SCORE_FLIPPER);
        } else if (gamepad1.triangle) {
            hardware.elbowServo.setPosition(_ELBOW_UPWARD);
            hardware.flipperServo.setPosition(_ELBOW_UPWARD_FLIPPER);
        } else if (gamepad1.circle) {
            hardware.elbowServo.setPosition(_ELBOW_INTAKE);
            hardware.flipperServo.setPosition(_ELBOW_INTAKE_FLIPPER);
        }

        if (gamepad2.dpad_up) {
            hardware.flipperServo.setPosition(upBtnServoPosition);
        } else if (gamepad2.dpad_right) {
            hardware.flipperServo.setPosition(rightBtnServoPosition);
        } else if (gamepad2.dpad_down) {
            hardware.flipperServo.setPosition(downBtnServoPosition);
        } else if (gamepad2.dpad_left) {
            hardware.flipperServo.setPosition(leftBtnServoPosition);
        } else if (gamepad1.right_bumper) {
            hardware.clawServo.setPosition(CLAW_CLOSE);
        } else if (gamepad1.left_bumper) {
            hardware.clawServo.setPosition(CLAW_OPEN);
        } else if (gamepad2.square) {
            hardware.elbowServo.setPosition(_ELBOW_SCORE);
            hardware.flipperServo.setPosition(_ELBOW_SCORE_FLIPPER);
        } else if (gamepad2.triangle) {
            hardware.elbowServo.setPosition(_ELBOW_UPWARD);
            hardware.flipperServo.setPosition(_ELBOW_UPWARD_FLIPPER);
        } else if (gamepad2.circle) {
            hardware.elbowServo.setPosition(_ELBOW_INTAKE);
            hardware.flipperServo.setPosition(_ELBOW_INTAKE_FLIPPER);
        }

        telemetry.addData("Claw Servo Position", robot.clawSubsystem.getClawPosition());
        telemetry.addData("Flipper Servo Position", robot.armSubsystem.getFlipperPosition());
        telemetry.addData("Elbow Servo Position", robot.armSubsystem.getElbowPosition());
        telemetry.update();
    }
}
