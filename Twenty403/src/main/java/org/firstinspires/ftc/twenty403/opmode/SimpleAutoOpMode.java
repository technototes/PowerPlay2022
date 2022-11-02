package org.firstinspires.ftc.twenty403.opmode;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.technototes.library.hardware2.HardwareBuilder;
import com.technototes.library.util.Alliance;

@Autonomous(name = "Encoder/IMU Testing")
public class SimpleAutoOpMode extends LinearOpMode {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void runOpMode() throws InterruptedException {
        // To use TechnoLib stuff in a normal opmode, you first need to do this:
        HardwareBuilder.initMap(hardwareMap);

        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.NONE, StartingPosition.NEUTRAL);

        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("FL Encoder", hardware.flDriveMotor.getSensorValue());
            telemetry.addData("FR Encoder", hardware.frDriveMotor.getSensorValue());
            telemetry.addData("RL Encoder", hardware.rlDriveMotor.getSensorValue());
            telemetry.addData("RR Encoder", hardware.rrDriveMotor.getSensorValue());
            telemetry.addData("IMU Heading", ReadIMU());
            telemetry.update();
        }
    }

    // Get the data from the IMU to make sure the orientation all makes sense
    private String ReadIMU() {
        Orientation o = hardware.imu.getAngularOrientation();
        return String.format(
                "A:%f B:%f C:%f (%f)",
                Math.toDegrees(o.firstAngle),
                Math.toDegrees(o.secondAngle),
                Math.toDegrees(o.thirdAngle),
                hardware.imu.gyroHeadingInDegrees());
    }
}
