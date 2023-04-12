package org.firstinspires.ftc.forteaching.OpModes;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Two Motor Driving")
public class TrialOpMode extends LinearOpMode {

    private DcMotorEx motorL;
    private DcMotorEx motorR;

    @Override
    public void runOpMode() throws InterruptedException {
        // User has hit "init" button but hasn't hit "start"

        // Initialize drive motors
        motorL = hardwareMap.get(DcMotorEx.class, "motorL");
        motorR = hardwareMap.get(DcMotorEx.class, "motorR");
        motorR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorL.setPower(0.0);
        motorR.setPower(0.0);

        waitForStart();
        // User has hit "start"
        int startL = motorL.getCurrentPosition();
        int startR = motorR.getCurrentPosition();
        motorL.setPower(1.0);
        motorR.setPower(1.0);
        int mode = 0;
        int dist = 5000;
        int dead = 100;
        ElapsedTime total = new ElapsedTime();
        while (opModeIsActive() && mode != 2) {
            // user hasn't hit "stop" yet
            if (
                mode == 0 &&
                (
                    Math.abs(motorL.getCurrentPosition() - startL) > dist ||
                    Math.abs(motorR.getCurrentPosition() - startR) > dist
                )
            ) {
                mode = 1;
                ElapsedTime t = new ElapsedTime();
                while (t.milliseconds() < 1000) {
                    motorL.setPower(0.0);
                    motorR.setPower(0.0);
                }
                motorL.setPower(-1);
                motorR.setPower(-1);
            } else if (
                mode == 1 &&
                (
                    Math.abs(motorL.getCurrentPosition() - startL) <= dead ||
                    Math.abs(motorR.getCurrentPosition() - startR) <= dead
                )
            ) {
                mode = 2;
            }
        }
        ElapsedTime t = new ElapsedTime();
        while (t.milliseconds() < 1000) {
            motorL.setPower(0.0);
            motorR.setPower(0.0);
        }
        telemetry.addData("Total Time: ", total.seconds());
        telemetry.update();
        ElapsedTime t2 = new ElapsedTime();
        while (t2.milliseconds() < 10000) {}
    }
}
