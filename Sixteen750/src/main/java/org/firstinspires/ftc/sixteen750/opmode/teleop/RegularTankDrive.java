package org.firstinspires.ftc.sixteen750.opmode.teleop;

import org.firstinspires.ftc.sixteen750.ControlsDriver;
import org.firstinspires.ftc.sixteen750.ControlsOperator;
import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.command.autonomous.StartingPosition;
import org.firstinspires.ftc.sixteen750.subsystem.TankDriveSubsystem;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

@Config
@TeleOp(group = "Tank")
@SuppressWarnings("unused")
public class RegularTankDrive extends CommandOpMode {
    TankDriveSubsystem drive;
    Robot robot;
    Hardware hardware;
    ControlsDriver driverControls;
    ControlsOperator operatorControls;

    public static final double STICK_DEAD_ZONE = 0.05;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.DEFAULT);
        robot = new Robot(hardware, Robot.SubsystemCombo.DEFAULT, Alliance.NONE, StartingPosition.NEUTRAL);
        driverControls = new ControlsDriver(driverGamepad, robot, Robot.SubsystemCombo.DEFAULT);
        operatorControls = new ControlsOperator(codriverGamepad, robot, Robot.SubsystemCombo.DEFAULT);

        drive = new TankDriveSubsystem(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void uponStart() {}

    @Override
    public void runLoop() {
        // TODO: maybe use setWeightedDrivePower()?
        // TODO: use controls class since this might conflict with CommandGamepad
        double leftStickValue, rightStickValue;
        if (Math.abs(gamepad1.left_stick_y) > STICK_DEAD_ZONE) {
            leftStickValue = gamepad1.left_stick_y;
        } else {
            leftStickValue = 0;
        }
        if (Math.abs(gamepad1.right_stick_y) > STICK_DEAD_ZONE) {
            rightStickValue = gamepad1.right_stick_y;
        } else {
            rightStickValue = 0;
        }
        double adjustedLeftStickValue = Range.clip(leftStickValue * -1, -1, 1);
        double adjustedRightStickValue = Range.clip(rightStickValue * 1, -1, 1);
        drive.setMotorPowers(adjustedLeftStickValue, adjustedRightStickValue);
        drive.update();

        telemetry.addData("Left Stick Value", leftStickValue);
        telemetry.addData("Left Stick Adjusted Value", adjustedLeftStickValue);
        telemetry.addData("Right Stick", adjustedRightStickValue);
        telemetry.addData("Right Stick Adjusted Value", adjustedRightStickValue);

        telemetry.addData("LeftFront Motor Power", drive.getLeftFrontMotorVelocity());
        telemetry.addData("LeftRear Motor Power", drive.getLeftRearMotorVelocity());
        telemetry.addData("RightFront Motor Power", drive.getRightFrontMotorVelocity());
        telemetry.addData("RightRear Motor Power", drive.getRightRearMotorVelocity());

        telemetry.addData("Claw Servo Position", robot.clawSubsystem.getClawPosition());
        telemetry.addData("Flipper Servo Position", robot.armSubsystem.getFlipperPosition());
        telemetry.addData("Elbow Servo Position", robot.armSubsystem.getElbowPosition());
        telemetry.update();
    }
}
