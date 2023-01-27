package org.firstinspires.ftc.swerveteen750.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
        HardwareBuilder.initMap(hardwareMap);
//        Hardware hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.S_DRIVE_ONLY);
        ConfigurableSwerveDriveSubsystem drive = new ConfigurableSwerveDriveSubsystem(hardwareMap);
        drive.setSwerveMotorEncoderZero();

        drive.setModuleOrientations(0, 0, 0, 0);

        safeSleep(1000 * 1);

        while (!isStopRequested() && opModeIsActive() && Math.abs(drive.getAdjustedMotorEncoderValue()[0]) < 10) {
            drive.setPowerForAllMotor(DEFAULT_POWER);
        }

        if (isStopRequested()) return;

        drive.stopAndWait(1000 * 1);
    }
}