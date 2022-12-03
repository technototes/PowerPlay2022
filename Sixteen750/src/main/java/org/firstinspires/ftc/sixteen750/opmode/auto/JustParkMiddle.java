package org.firstinspires.ftc.sixteen750.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.hardware2.HardwareBuilder;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.subsystem.SimpleMecanumDriveSubsystem;

@Autonomous(name = "JustParkMiddle")
public class JustParkMiddle extends LinearOpMode {
    public static double DEFAULT_POWER = 0.3;
    public static int goForwardTime = 1200;
    public static int goLeftTime = 400;
    public static int goRightTime = 400;

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

        drive.stop();
        System.out.println("Forward Auto Finished");

        time.reset();

        while (!isStopRequested() && opModeIsActive() && time.milliseconds() < goLeftTime && false) {
            drive.goLeft(DEFAULT_POWER);
        }

        while (!isStopRequested() && opModeIsActive() && time.milliseconds() < goRightTime && false) {
            drive.goRight(DEFAULT_POWER);
        }

        drive.stop();
        System.out.println("Left/Right Auto Finished");
    }
}
