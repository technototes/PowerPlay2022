package org.firstinspires.ftc.sixteen750.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.sixteen750.ControlDriver;
import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;


@Config
@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class JustClawTest extends CommandOpMode {
    public Robot robot;
    public ControlDriver controls;
    public Hardware hardware;

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
        * */
        if (gamepad1.dpad_up){
            hardware.claw.setPosition(0.45); // better not to go below this value
        }
        else if (gamepad1.dpad_right){
            hardware.claw.setPosition(0.47);
        }
        else if (gamepad1.dpad_down){
            hardware.claw.setPosition(0.5);
        }
        else if (gamepad1.dpad_left){
            hardware.claw.setPosition(0.6);
        }
        else if (gamepad1.square) {
            hardware.claw.setPosition(1);
        }
        else if (gamepad1.circle) {
            hardware.claw.setPosition(0);
        }


        telemetry.addData("Claw Servo Position", robot.clawSubsystem.getClawPosition());
        telemetry.addData("Flipper Servo Position", robot.clawSubsystem.getFlipperPosition());
        telemetry.addData("Elbow Servo Position", robot.clawSubsystem.getElbowPosition());
        telemetry.update();
    }
}
