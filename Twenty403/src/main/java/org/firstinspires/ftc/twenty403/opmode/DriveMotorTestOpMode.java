package org.firstinspires.ftc.twenty403.opmode;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.technototes.library.hardware2.HardwareBuilder;
import com.technototes.library.util.Alliance;

@Disabled
@Autonomous(name = "Drive Motor Test")
@SuppressWarnings("unused")
public class DriveMotorTestOpMode extends LinearOpMode {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void runOpMode() throws InterruptedException {
        // To use TechnoLib stuff in a normal opmode, you first need to do this:
        HardwareBuilder.initMap(hardwareMap);

        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.NONE, StartingPosition.NEUTRAL);

        waitForStart();

        ElapsedTime time = new ElapsedTime();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            switch ((int) Math.floor(time.seconds() / 4) % 4) {
                case 0:
                    hardware.flDriveMotor.setSpeed(0.2);
                    hardware.frDriveMotor.setSpeed(0.0);
                    hardware.rlDriveMotor.setSpeed(0.0);
                    hardware.rrDriveMotor.setSpeed(0.0);
                    telemetry.addData("MOTOR", "FL");
                    break;
                case 1:
                    hardware.flDriveMotor.setSpeed(0.0);
                    hardware.frDriveMotor.setSpeed(0.2);
                    hardware.rlDriveMotor.setSpeed(0.0);
                    hardware.rrDriveMotor.setSpeed(0.0);
                    telemetry.addData("MOTOR", "FR");
                    break;
                case 2:
                    hardware.flDriveMotor.setSpeed(0.0);
                    hardware.frDriveMotor.setSpeed(0.0);
                    hardware.rlDriveMotor.setSpeed(0.2);
                    hardware.rrDriveMotor.setSpeed(0.0);
                    telemetry.addData("MOTOR", "RL");
                    break;
                case 3:
                    hardware.flDriveMotor.setSpeed(0.0);
                    hardware.frDriveMotor.setSpeed(0.0);
                    hardware.rlDriveMotor.setSpeed(0.0);
                    hardware.rrDriveMotor.setSpeed(0.2);
                    telemetry.addData("MOTOR", "RR");
                    break;
                default:
            }
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
