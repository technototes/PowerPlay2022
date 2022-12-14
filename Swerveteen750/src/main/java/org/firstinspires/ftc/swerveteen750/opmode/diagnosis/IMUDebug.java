package org.firstinspires.ftc.swerveteen750.opmode.diagnosis;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.swerveteen750.Hardware;
import org.firstinspires.ftc.swerveteen750.Robot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.technototes.library.hardware2.HardwareBuilder;


@Disabled
@Config
@TeleOp()
@SuppressWarnings("unused")
public class IMUDebug extends LinearOpMode {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        HardwareBuilder.initMap(hardwareMap);
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.DEFAULT);
        //Robot robot = new Robot(hardware, Robot.SubsystemCombo.DEFAULT, Alliance.NONE, StartingPosition.NEUTRAL);

        waitForStart();

        ElapsedTime time = new ElapsedTime();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
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
