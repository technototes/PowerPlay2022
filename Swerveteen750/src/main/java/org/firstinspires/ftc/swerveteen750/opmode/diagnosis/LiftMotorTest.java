package org.firstinspires.ftc.swerveteen750.opmode.diagnosis;

import org.firstinspires.ftc.swerveteen750.ControlsDriver;
import org.firstinspires.ftc.swerveteen750.ControlsOperator;
import org.firstinspires.ftc.swerveteen750.Hardware;
import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.command.autonomous.StartingPosition;

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

        robot = new Robot(hardwareMap, hardware, Robot.SubsystemCombo.LIFT_ONLY, Alliance.NONE, StartingPosition.NEUTRAL);
        controlsDriver = new ControlsDriver(driverGamepad, robot, Robot.SubsystemCombo.LIFT_ONLY);
        operatorControls = new ControlsOperator(codriverGamepad, robot, Robot.SubsystemCombo.LIFT_ONLY);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void runLoop() {
        if (gamepad1.circle) {
            robot.liftSubsystem.turretServoPositionRear();
        }
        if (gamepad1.square) {
            robot.liftSubsystem.turretServoPositionFront();
        }
        if (gamepad1.cross) {
            robot.liftSubsystem.turretServoPositionSide();
        }
        if (gamepad1.right_bumper) {
            robot.liftSubsystem.turretIncrementUp();
        }
        telemetry.addData("Left Lift Motor Target Position", robot.liftSubsystem.getLeftTargetPos());
        telemetry.addData("Left Lift Motor Current Position", robot.liftSubsystem.getLeftPos());
        telemetry.addData("Left lift Motor Encoder", hardware.leftLiftMotor.getDevice().getCurrentPosition());
        telemetry.update();
    }
}
