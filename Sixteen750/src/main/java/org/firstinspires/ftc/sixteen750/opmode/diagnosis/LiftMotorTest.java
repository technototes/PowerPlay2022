package org.firstinspires.ftc.sixteen750.opmode.diagnosis;

import org.firstinspires.ftc.sixteen750.ControlsDriver;
import org.firstinspires.ftc.sixteen750.ControlsOperator;
import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.command.autonomous.StartingPosition;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class LiftMotorTest extends CommandOpMode {
    public Robot robot;
    public ControlsDriver controlsDriver;
    public ControlsOperator operatorControls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.LIFT_ONLY);
        robot = new Robot(hardware, Robot.SubsystemCombo.LIFT_ONLY, Alliance.NONE, StartingPosition.NEUTRAL);
        controlsDriver = new ControlsDriver(driverGamepad, robot, Robot.SubsystemCombo.LIFT_ONLY);
        operatorControls = new ControlsOperator(codriverGamepad, robot, Robot.SubsystemCombo.LIFT_ONLY);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void runLoop() {
        telemetry.addData("Left Lift Motor Encoder", robot.liftSubsystem.getLeftPos());
        telemetry.addData("Left lift motor position", hardware.leftLiftMotor.getDevice().getCurrentPosition());
        telemetry.update();
    }
}
