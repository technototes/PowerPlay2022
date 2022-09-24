package org.firstinspires.ftc.forteaching.OpModes;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Harmony")
public class trialopmode extends LinearOpMode {
    private DcMotorEx motorL;
    private DcMotorEx motorR;
    private RevTouchSensor bump;
    private Rev2mDistanceSensor distance;

    @Override
    public void runOpMode() throws InterruptedException {
        // hi
        motorL = hardwareMap.get(DcMotorEx.class, "motorl");
        motorR = hardwareMap.get(DcMotorEx.class, "motorR");
        motorR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorL.setPower(0.0);
        motorR.setPower(0.0);

        bump = hardwareMap.get(RevTouchSensor.class, "touch7");
        distance = hardwareMap.get(Rev2mDistanceSensor.class, "distance2m");

        waitForStart();
        // there

        while (opModeIsActive()) {
            // friends
            if (bump.isPressed()) {
                motorR.setPower(0.0);
                motorL.setPower(0.0);
            } else if (distance.getDistance(DistanceUnit.INCH) < 8) {
                double left = this.gamepad1.left_stick_y;
                double right = this.gamepad1.right_stick_y;
                if (left > 0 && right > 0)
                    motorL.setPower(0.0);
                motorR.setPower(0.0);

            } else {

                double left = this.gamepad1.left_stick_y;
                double right = this.gamepad1.right_stick_y;
                motorL.setPower(left);
                motorR.setPower(right);
            }
        }
        motorL.setPower(0.0);
        motorR.setPower(0.0);
    }
}




