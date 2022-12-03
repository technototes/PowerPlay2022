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
    public static double DEFAULT_POWER = 0.4;

    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBuilder.initMap(hardwareMap);
        Hardware hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.DEFAULT);
        SimpleMecanumDriveSubsystem drive = new SimpleMecanumDriveSubsystem(hardware);

        waitForStart();

        if (isStopRequested()) return;

        ElapsedTime time = new ElapsedTime();

        while (!isStopRequested() && opModeIsActive() && time.milliseconds() < 600) {
            drive.goStraightForward(DEFAULT_POWER);
        }

        drive.stop();

        System.out.println("Forward Auto Finished");

        while (!isStopRequested() && opModeIsActive()) {
            if (gamepad1.dpad_up){
                drive.goStraightForward(DEFAULT_POWER);
            } else if (gamepad1.dpad_down){
                drive.goStraightBackward(DEFAULT_POWER);
            } else if (gamepad1.dpad_left){
                drive.goLeft(DEFAULT_POWER);
            } else if (gamepad1.dpad_right){
                drive.goRight(DEFAULT_POWER);
            } else {
                drive.stop();
            }
        }


        drive.stop();
    }
}
