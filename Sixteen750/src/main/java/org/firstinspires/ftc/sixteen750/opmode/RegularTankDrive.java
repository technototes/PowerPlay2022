package org.firstinspires.ftc.sixteen750.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.sixteen750.DriverControls;
import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.OperatorControls;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.subsystem.TankDrivebaseSubsystem;

@Config
@TeleOp(group = "Tank")
public class RegularTankDrive extends CommandOpMode {
    TankDrivebaseSubsystem drive;
    Robot robot;
    Hardware hardware;
    DriverControls driverControls;
    OperatorControls operatorControls;

    public static final double STICK_DEAD_ZONE = 0.1;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        driverControls = new DriverControls(driverGamepad, robot);
        operatorControls = new OperatorControls(codriverGamepad, robot);

        drive = new TankDrivebaseSubsystem(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void uponStart() {}

    @Override
    public void runLoop(){
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
        drive.setMotorPowers(Range.clip(leftStickValue, -1, 1), -Range.clip(rightStickValue, -1, 1));
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