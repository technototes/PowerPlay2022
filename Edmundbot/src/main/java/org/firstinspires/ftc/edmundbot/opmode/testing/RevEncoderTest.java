package org.firstinspires.ftc.edmundbot.opmode.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@Autonomous(name = "Rev Encoder Test")
@SuppressWarnings("unused")
public class RevEncoderTest extends LinearOpMode {

    public String REV_ENCODER = "revThruEnc";

    /*
     * This is for doing testing without requiring the TechnoLib command scheduler
     */

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        // To use TechnoLib stuff in a normal opmode, you first need to do this:
        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, REV_ENCODER);
        motor.setPower(0.0);
        waitForStart();

        ElapsedTime time = new ElapsedTime();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Position", motor.getCurrentPosition());
            telemetry.update();
        }
    }
}
