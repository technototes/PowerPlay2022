package org.firstinspires.ftc.sixteen750.opmode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.hardware2.HardwareBuilder;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.subsystem.SimpleMecanumDriveSubsystem;

@Config
@Autonomous(name = "JustParkLeft")
public class JustParkLeft extends LinearOpMode {
    public static double DEFAULT_POWER = 0.3;
    public static int goForwardTime = 3000;
    public static int goLeftTime = 2500; // Mecanum wheels having resistance when going side way

    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBuilder.initMap(hardwareMap);
        Hardware hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.DEFAULT);
        SimpleMecanumDriveSubsystem drive = new SimpleMecanumDriveSubsystem(hardware);

        waitForStart();

        if (isStopRequested()) return;

        ElapsedTime time = new ElapsedTime();

        while (!isStopRequested() && opModeIsActive() && time.milliseconds() < goForwardTime) {
            drive.goStraightForward(DEFAULT_POWER);
        }

        drive.stopAndWait();
        System.out.println("Forward Auto Finished");

        time.reset();

        while (!isStopRequested() && opModeIsActive() && time.milliseconds() < goLeftTime) {
            drive.goLeft(DEFAULT_POWER);
        }

        drive.stop();
        System.out.println("Left Auto Finished");
    }
}
