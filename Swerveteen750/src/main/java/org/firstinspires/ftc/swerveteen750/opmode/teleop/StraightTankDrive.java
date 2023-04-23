package org.firstinspires.ftc.swerveteen750.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.AnotherTankDriveSubsystem;

@TeleOp(group = "Tank")
public class StraightTankDrive extends CommandOpMode {
    AnotherTankDriveSubsystem drive;

    public static final double STICK_DEAD_ZONE = 0.05;

    @Override
    public void uponInit() {
        drive = new AnotherTankDriveSubsystem(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void uponStart() {}

    @Override
    public void runLoop() {
        double leftStickValue;
        if (Math.abs(gamepad1.left_stick_y) > STICK_DEAD_ZONE) {
            leftStickValue = gamepad1.left_stick_y;
        } else {
            leftStickValue = 0;
        }

        double adjustedLeftStickValue = Range.clip(leftStickValue * -1, -1, 1);
        drive.drive(adjustedLeftStickValue);

        telemetry.addData("Left Stick Value", leftStickValue);
        telemetry.addData("Left Stick Adjusted Value", adjustedLeftStickValue);

        telemetry.addData("LeftFront Motor Velocity", drive.leftFrontMotor.getVelocity());
        telemetry.addData("LeftRear Motor Velocity", drive.leftRearMotor.getVelocity());
        telemetry.addData("RightFront Motor Velocity", drive.rightFrontMotor.getVelocity());
        telemetry.addData("RightRear Motor Velocity", drive.rightRearMotor.getVelocity());

        telemetry.update();
    }
}
