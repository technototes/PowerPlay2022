package org.firstinspires.ftc.sixteen750.opmode.diagnosis;

import org.firstinspires.ftc.sixteen750.ControlsDriver;
import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.technototes.library.structure.CommandOpMode;

@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class LiftMotorTest extends CommandOpMode {
    public Robot robot;
    public ControlsDriver controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap, false, true, false, false);
        robot = new Robot(hardware, false, true, false, false);
//        controls = new ControlsDriver(driverGamepad, robot);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void runLoop() {
        telemetry.addData("Lift LeftMotor", robot.liftSubsystem.getLeftPos());
        telemetry.addData("Lift RightMotor", robot.liftSubsystem.getRightPos());
        telemetry.update();
    }
}
