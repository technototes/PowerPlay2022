package org.firstinspires.ftc.sixteen750.opmode.teleop;

import org.firstinspires.ftc.sixteen750.subsystem.TankDriveSubsystem;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import com.technototes.library.structure.CommandOpMode;

@Config
@TeleOp(name = "BasicTankDrive", group = "Tank")
@SuppressWarnings("unused")
public class BasicTankDriveOpMode extends CommandOpMode {
    TankDriveSubsystem drive;

    public static final double DEAD_ZONE = 0.1;

    @Override
    public void uponInit() {
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
        double leftStickValue, rightStickValue;
        if (Math.abs(gamepad1.left_stick_y) > DEAD_ZONE) {
            leftStickValue = gamepad1.left_stick_y;
        } else {
            leftStickValue = 0;
        }
        if (Math.abs(gamepad1.right_stick_y) > DEAD_ZONE) {
            rightStickValue = gamepad1.right_stick_y;
        } else {
            rightStickValue = 0;
        }
        drive.setMotorPowers(Range.clip(leftStickValue * -1, -1, 1), Range.clip(rightStickValue * 1, -1, 1));
        drive.update();

        telemetry.addData("Left Stick", leftStickValue);
        telemetry.addData("Right Stick", rightStickValue);

        telemetry.addData("LeftFront Motor Power", drive.getLeftFrontMotorVelocity());
        telemetry.addData("LeftRear Motor Power", drive.getLeftRearMotorVelocity());
        telemetry.addData("RightFront Motor Power", drive.getRightFrontMotorVelocity());
        telemetry.addData("RightRear Motor Power", drive.getRightRearMotorVelocity());
        telemetry.update();
    }
}
