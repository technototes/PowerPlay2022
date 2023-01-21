package org.firstinspires.ftc.swerveteen750.opmode.diagnosis;

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
public class ClawServoTest extends CommandOpMode {
    public Robot robot;
    public ControlsDriver controls;
    public Hardware hardware;

    // clockwise is order
    public static double upBtnServoPosition = 0.2;
    public static double rightBtnServoPosition = 0.4;
    public static double downBtnServoPosition = 0.6;
    public static double leftBtnServoPosition = 0.8;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.CLAW_ONLY);
        robot = new Robot(hardwareMap, hardware, Robot.SubsystemCombo.CLAW_ONLY, Alliance.NONE, StartingPosition.NEUTRAL);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void runLoop() {
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
        telemetry.update();
    }
}
