package org.firstinspires.ftc.twenty403.opmode.testing;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.technototes.library.hardware2.HardwareBuilder;
import com.technototes.library.util.Alliance;

@Config
@Disabled
@Autonomous(name = "Drive Motor Test")
@SuppressWarnings("unused")
public class DriveMotorTest extends LinearOpMode {
    /*
     * This is for doing testing without requiring the TechnoLib command scheduler
     */
    public Robot robot;
    public Hardware hardware;

    @Config
    public static class MotorScales {
        public static double FL = 0.41;
        public static double FR = 0.44;
        public static double RL = 0.5;
        public static double RR = 0.39;
        public static double SPEED = 2.0;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        // To use TechnoLib stuff in a normal opmode, you first need to do this:
        HardwareBuilder.initMap(hardwareMap);

        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.NONE, StartingPosition.NEUTRAL);

        waitForStart();

        ElapsedTime time = new ElapsedTime();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double pos = time.seconds() * MotorScales.SPEED % 16.0;
            double power = 0;
            if (pos < 4.0) {
                power = pos / 4.0;
            } else if (pos < 8.0) {
                power = (8.0 - pos) / 4.0;
            } else if (pos < 12.0) {
                power = (8.0 - pos) / 4.0;
            } else { // pos < 16
                power = (pos - 16.0) / 4.0;
            }

            hardware.flDriveMotor.setSpeed(power * MotorScales.FL);
            hardware.frDriveMotor.setSpeed(power * MotorScales.FR);
            hardware.rlDriveMotor.setSpeed(power * MotorScales.RL);
            hardware.rrDriveMotor.setSpeed(power * MotorScales.RR);

            telemetry.addData("Power", power);
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
