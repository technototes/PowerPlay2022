package org.firstinspires.ftc.swerveteen750.opmode.diagnosis;

import static org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem.FLIPPER_WHEN_ELBOW_INTAKE;
import static org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem.ELBOW_SCORE;
import static org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem.FLIPPER_WHEN_ELBOW_SCORE;
import static org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem.ELBOW_UPWARD;
import static org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem.ELBOW_INTAKE;
import static org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem.FLIPPER_WHEN_ELBOW_UPWARD;
import static org.firstinspires.ftc.swerveteen750.subsystem.ClawSubsystem.CLAW_CLOSE;
import static org.firstinspires.ftc.swerveteen750.subsystem.ClawSubsystem.CLAW_OPEN;

import org.firstinspires.ftc.swerveteen750.ControlsDriver;
import org.firstinspires.ftc.swerveteen750.Hardware;
import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.command.autonomous.StartingPosition;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.util.Range;
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
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.ARM_CLAW_ONLY);
        robot = new Robot(hardware, Robot.SubsystemCombo.ARM_CLAW_ONLY, Alliance.NONE, StartingPosition.NEUTRAL);
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
            hardware.elbowServo.setPosition(ELBOW_SCORE);
            hardware.flipperServo.setPosition(FLIPPER_WHEN_ELBOW_SCORE);
        } else if (gamepad1.triangle) {
            hardware.elbowServo.setPosition(ELBOW_UPWARD);
            hardware.flipperServo.setPosition(FLIPPER_WHEN_ELBOW_UPWARD);
        } else if (gamepad1.circle) {
            hardware.elbowServo.setPosition(ELBOW_INTAKE);
            hardware.flipperServo.setPosition(FLIPPER_WHEN_ELBOW_INTAKE);
        } else if (gamepad1.left_trigger > 0.5){
            hardware.elbowServo.setPosition(Range.clip(robot.armSubsystem.getElbowPosition() + 0.05, 0, 1));
        } else if (gamepad1.right_trigger > 0.5){
            hardware.elbowServo.setPosition(Range.clip(robot.armSubsystem.getElbowPosition() - 0.05, 0, 1));
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
            hardware.elbowServo.setPosition(ELBOW_SCORE);
            hardware.flipperServo.setPosition(FLIPPER_WHEN_ELBOW_SCORE);
        } else if (gamepad2.triangle) {
            hardware.elbowServo.setPosition(ELBOW_UPWARD);
            hardware.flipperServo.setPosition(FLIPPER_WHEN_ELBOW_UPWARD);
        } else if (gamepad2.circle) {
            hardware.elbowServo.setPosition(ELBOW_INTAKE);
            hardware.flipperServo.setPosition(FLIPPER_WHEN_ELBOW_INTAKE);
        } else if (gamepad2.left_trigger > 0.5){
            hardware.flipperServo.setPosition(Range.clip(robot.armSubsystem.getFlipperPosition() + 0.05, 0, 1));
        } else if (gamepad2.right_trigger > 0.5){
            hardware.flipperServo.setPosition(Range.clip(robot.armSubsystem.getFlipperPosition() - 0.05, 0, 1));
        }

        if (gamepad1.right_bumper) {
            hardware.clawServo.setPosition(CLAW_CLOSE);
        } else if (gamepad1.left_bumper) {
            hardware.clawServo.setPosition(CLAW_OPEN);
        }
        if (gamepad2.right_bumper) {
            hardware.clawServo.setPosition(CLAW_CLOSE);
        } else if (gamepad2.left_bumper) {
            hardware.clawServo.setPosition(CLAW_OPEN);
        }

        telemetry.addData("Claw Servo Position", robot.clawSubsystem.getClawPosition());
        telemetry.addData("Flipper Servo Position", robot.armSubsystem.getFlipperPosition());
        telemetry.addData("Elbow Servo Position", robot.armSubsystem.getElbowPosition());
        telemetry.update();
    }
}
