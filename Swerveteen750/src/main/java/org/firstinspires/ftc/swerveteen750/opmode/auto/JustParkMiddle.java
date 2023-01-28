package org.firstinspires.ftc.swerveteen750.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.hardware2.HardwareBuilder;

import org.firstinspires.ftc.swerveteen750.Hardware;
import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;

@Autonomous(group = "Will Not Work")
public class JustParkMiddle extends LinearOpMode {
    public static double DEFAULT_POWER = 0.3;

    public void safeSleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        HardwareBuilder.initMap(hardwareMap);
//        Hardware hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.S_DRIVE_ONLY);
        ConfigurableSwerveDriveSubsystem drive = new ConfigurableSwerveDriveSubsystem(hardwareMap);
        drive.setSwerveMotorEncoderZero();

        waitForStart();

        drive.setModuleOrientations(0, 0, 0, 0);
        ElapsedTime timer = new ElapsedTime();
        while (timer.milliseconds() < 1000 * 1) {
            drive.update();
            telemetry.addData("LF Encoder Voltage ", drive.leftFrontModule.getEncoderVoltage());
            telemetry.addData("power ", drive.leftFrontModule.getServoPower());
            telemetry.update();
        }


        safeSleep(1000 * 1);

        while (!isStopRequested() && opModeIsActive() && Math.abs(drive.getAdjustedMotorEncoderValue()[2]) < 30) {
            drive.setPowerForAllMotor(DEFAULT_POWER);
            drive.update();

            telemetry.addData("Left Front Encoder", drive.getAdjustedMotorEncoderValue()[0]);
            telemetry.addData("Left Rear Encoder", drive.getAdjustedMotorEncoderValue()[1]);
            telemetry.addData("Right Front Encoder", drive.getAdjustedMotorEncoderValue()[2]);
            telemetry.addData("Right Rear Encoder", drive.getAdjustedMotorEncoderValue()[3]);
            telemetry.addData("LF Encoder Voltage ", drive.leftFrontModule.getEncoderVoltage());
            telemetry.addData("power ", drive.leftFrontModule.getServoPower());

            telemetry.update();
        }

        if (isStopRequested()) return;

        drive.stopAndWait(1000 * 1);
    }
}