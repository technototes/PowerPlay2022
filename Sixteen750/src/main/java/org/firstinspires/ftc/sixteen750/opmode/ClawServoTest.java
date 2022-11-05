package org.firstinspires.ftc.sixteen750.opmode;

import org.firstinspires.ftc.sixteen750.ControlDriver;
import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.technototes.library.structure.CommandOpMode;

@Config
@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class ClawServoTest extends CommandOpMode {
    public Robot robot;
    public ControlDriver controls;
    public Hardware hardware;

    // clockwise is order
    public static double upBtnServoPosition = 0.49;
    public static double rightBtnServoPosition = 0.5;
    public static double downBtnServoPosition = 0.51;
    public static double leftBtnServoPosition = 0.6;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        controls = new ControlDriver(driverGamepad, robot);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
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
            hardware.clawServo.setPosition(upBtnServoPosition); // better not to go below this value
        } else if (gamepad1.dpad_right) {
            hardware.clawServo.setPosition(rightBtnServoPosition);
        } else if (gamepad1.dpad_down) {
            hardware.clawServo.setPosition(downBtnServoPosition);
        } else if (gamepad1.dpad_left) {
            hardware.clawServo.setPosition(leftBtnServoPosition);
        } else if (gamepad1.square) {
            hardware.clawServo.setPosition(1);
        } else if (gamepad1.circle) {
            hardware.clawServo.setPosition(0);
        }

        telemetry.addData("Claw Servo Position", robot.clawSubsystem.getClawPosition());
        telemetry.addData("Flipper Servo Position", robot.clawSubsystem.getFlipperPosition());
        telemetry.addData("Elbow Servo Position", robot.clawSubsystem.getElbowPosition());
        telemetry.update();
    }
}
