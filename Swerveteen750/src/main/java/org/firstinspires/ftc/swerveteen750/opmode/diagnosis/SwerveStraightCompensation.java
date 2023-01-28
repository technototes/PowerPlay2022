package org.firstinspires.ftc.swerveteen750.opmode.diagnosis;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.hardware2.HardwareBuilder;

import org.firstinspires.ftc.swerveteen750.Hardware;
import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.SimpleSwerveDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.swerve_util.swerve_module.AnotherSwerveModule;

@TeleOp(group = "Swerve")
public class SwerveStraightCompensation extends LinearOpMode {
    public static double DEFAULT_POWER = 0.6;
    public static int goForwardTime = 30000;

    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBuilder.initMap(hardwareMap);
        Hardware hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.S_DRIVE_ONLY);

        AnotherSwerveModule leftFrontModule = new AnotherSwerveModule(hardware.leftFrontMotorQ, hardware.leftFrontServo, hardware.leftFrontEncoder, ConfigurableSwerveDriveSubsystem.LF_SERVO_ROTATION_PID_COEF, null, ConfigurableSwerveDriveSubsystem.kStatic);
        AnotherSwerveModule leftRearModule = new AnotherSwerveModule(hardware.leftRearMotorQ, hardware.leftRearServo, hardware.leftRearEncoder, ConfigurableSwerveDriveSubsystem.LR_SERVO_ROTATION_PID_COEF, null, ConfigurableSwerveDriveSubsystem.kStatic);
        AnotherSwerveModule rightFrontModule = new AnotherSwerveModule(hardware.rightFrontMotorQ, hardware.rightFrontServo, hardware.rightFrontEncoder, ConfigurableSwerveDriveSubsystem.RF_SERVO_ROTATION_PID_COEF, null, ConfigurableSwerveDriveSubsystem.kStatic);
        AnotherSwerveModule rightRearModule = new AnotherSwerveModule(hardware.rightRearMotorQ, hardware.rightRearServo, hardware.rightRearEncoder, ConfigurableSwerveDriveSubsystem.RR_SERVO_ROTATION_PID_COEF, null, ConfigurableSwerveDriveSubsystem.kStatic);

        SimpleSwerveDriveSubsystem drive = new SimpleSwerveDriveSubsystem(leftFrontModule, leftRearModule, rightFrontModule, rightRearModule);
        drive.setMotorEncoderZero();

        waitForStart();

        if (isStopRequested()) return;

        ElapsedTime time = new ElapsedTime();

        while (!isStopRequested() && opModeIsActive() && time.milliseconds() < goForwardTime) {
//            drive.setModulePowers(DEFAULT_POWER, DEFAULT_POWER, DEFAULT_POWER, DEFAULT_POWER);
            leftFrontModule.setMotorPower(DEFAULT_POWER * 1);
            leftRearModule.setMotorPower(DEFAULT_POWER * 1);
            rightFrontModule.setMotorPower(DEFAULT_POWER * 1);
            rightRearModule.setMotorPower(DEFAULT_POWER * 1);

            telemetry.addData("Left Front Encoder - Adjusted1", drive.getAdjustedMotorEncoderValues()[0]);
            telemetry.addData("Left Rear Encoder - Adjusted1", drive.getAdjustedMotorEncoderValues()[1]);
            telemetry.addData("Right Front Encoder - Adjusted1", drive.getAdjustedMotorEncoderValues()[2]);
            telemetry.addData("Right Rear Encoder  - Adjusted1", drive.getAdjustedMotorEncoderValues()[3]);

            telemetry.update();
        }

//        drive.setModulePowers(0, 0, 0, 0);
        leftFrontModule.setMotorPower(0);
        leftRearModule.setMotorPower(0);
        rightFrontModule.setMotorPower(0);
        rightRearModule.setMotorPower(0);

        telemetry.addLine("Stopped");
        telemetry.update();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        telemetry.addData("Left Front Encoder - Adjusted", drive.getAdjustedMotorEncoderValues()[0]);
        telemetry.addData("Left Rear Encoder - Adjusted", drive.getAdjustedMotorEncoderValues()[1]);
        telemetry.addData("Right Front Encoder - Adjusted", drive.getAdjustedMotorEncoderValues()[2]);
        telemetry.addData("Right Rear Encoder  - Adjusted", drive.getAdjustedMotorEncoderValues()[3]);

        telemetry.update();
    }
}
