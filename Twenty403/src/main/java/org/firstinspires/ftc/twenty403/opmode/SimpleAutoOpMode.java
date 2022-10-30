package org.firstinspires.ftc.twenty403.opmode;

import android.util.Log;

import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class SimpleAutoOpMode extends LinearOpMode {
    public Robot robot;
    public Hardware hardware;

    private String ReadIMU() {
        return String.format("%d", hardware.imu.gyroHeadingInDegrees());
    }

    @Override
    public void runOpMode() throws InterruptedException {
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            Log.d("imu", ReadIMU());
            hardware.flDriveMotor.setSpeed(.5);
            Log.d("imu", ReadIMU());
            hardware.flDriveMotor.setSpeed(0);
            Log.d("imu", ReadIMU());
            hardware.frDriveMotor.setSpeed(.5);
            Log.d("imu", ReadIMU());
            hardware.frDriveMotor.setSpeed(0);
            Log.d("imu", ReadIMU());
            hardware.rlDriveMotor.setSpeed(.5);
            Log.d("imu", ReadIMU());
            hardware.rlDriveMotor.setSpeed(0);
            Log.d("imu", ReadIMU());
            hardware.rrDriveMotor.setSpeed(.5);
            Log.d("imu", ReadIMU());
            hardware.rrDriveMotor.setSpeed(0);
            Log.d("imu", ReadIMU());
        }
    }
}
