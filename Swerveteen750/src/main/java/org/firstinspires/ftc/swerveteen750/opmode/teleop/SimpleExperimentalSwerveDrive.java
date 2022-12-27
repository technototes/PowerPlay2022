package org.firstinspires.ftc.swerveteen750.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.swerveteen750.Hardware;
import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.SimpleSwerveDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.swerve_util.swerve_module.AnotherSwerveModule;


@TeleOp(group = "Swerve")
public class SimpleExperimentalSwerveDrive extends CommandOpMode {
    Hardware hardware;
    SimpleSwerveDriveSubsystem drive;
    AnotherSwerveModule leftFrontModule;
    AnotherSwerveModule leftRearModule;
    AnotherSwerveModule rightFrontModule;
    AnotherSwerveModule rightRearModule;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.DEFAULT);
        // in this case the back being called as rear, and left rear after left front
        leftFrontModule = new AnotherSwerveModule(hardware.leftFrontMotorQ, hardware.leftFrontServo, hardware.leftFrontEncoder, ConfigurableSwerveDriveSubsystem.LF_SERVO_ROTATION_PID_COEF);
        leftRearModule = new AnotherSwerveModule(hardware.leftRearMotorQ, hardware.leftRearServo, hardware.leftRearEncoder, ConfigurableSwerveDriveSubsystem.LR_SERVO_ROTATION_PID_COEF);
        rightFrontModule = new AnotherSwerveModule(hardware.rightFrontMotorQ, hardware.rightFrontServo, hardware.rightFrontEncoder, ConfigurableSwerveDriveSubsystem.RF_SERVO_ROTATION_PID_COEF);
        rightRearModule = new AnotherSwerveModule(hardware.rightRearMotorQ, hardware.rightRearServo, hardware.rightRearEncoder, ConfigurableSwerveDriveSubsystem.RR_SERVO_ROTATION_PID_COEF);
        leftFrontModule.enableMotor = false;
        leftRearModule.enableMotor = false;
        rightFrontModule.enableMotor = false;
        rightRearModule.enableMotor = false;
        drive = new SimpleSwerveDriveSubsystem(leftFrontModule, leftRearModule, rightFrontModule, rightRearModule);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void runLoop() {
        if (gamepad1.circle){
            drive.rotatingClockwise();
            System.out.println("Rotating Clockwise");
        }

        if (gamepad1.triangle){
            drive.setModulePowers(new double[]{0.5, 0.5, 0.5, 0.5});
            System.out.println("Powering all modules");
        }
        else {
            drive.setModulePowers(new double[]{0, 0, 0, 0});
        }


        drive.periodic();

        telemetry.addData("LF - Target Orientation", Math.toDegrees(leftFrontModule.getTargetRotation()));
        telemetry.addData("LR - Target Orientation", Math.toDegrees(leftRearModule.getTargetRotation()));
        telemetry.addData("RF - Target Orientation", Math.toDegrees(rightFrontModule.getTargetRotation()));
        telemetry.addData("RR - Target Orientation", Math.toDegrees(rightRearModule.getTargetRotation()));

        telemetry.addData("LF - Current Orientation", Math.toDegrees(leftFrontModule.getModuleRotation()));
        telemetry.addData("LR - Current Orientation", Math.toDegrees(leftRearModule.getModuleRotation()));
        telemetry.addData("RF - Current Orientation", Math.toDegrees(rightFrontModule.getModuleRotation()));
        telemetry.addData("RR - Current Orientation", Math.toDegrees(rightRearModule.getModuleRotation()));

        telemetry.update();
    }
}
