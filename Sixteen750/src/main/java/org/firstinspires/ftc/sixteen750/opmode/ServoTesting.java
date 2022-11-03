package org.firstinspires.ftc.sixteen750.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.structure.CommandOpMode;


@TeleOp(group = "drive")
public class ServoTesting extends CommandOpMode {
    ElapsedTime t;

    CRServo leftFrontServo, leftRearServo, rightRearServo, rightFrontServo;
    boolean isLeftFrontPressed, isLeftRearPressed, isRightRearPressed, isRightFrontPressed = false;

    public static double servoPower = 0.1;
    public static double servoStopPower = 0.0;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        leftFrontServo = hardwareMap.get(CRServo.class, "leftFrontServo");
        leftRearServo = hardwareMap.get(CRServo.class, "leftRearServo");
        rightRearServo = hardwareMap.get(CRServo.class, "rightRearServo");
        rightFrontServo = hardwareMap.get(CRServo.class, "rightFrontServo");
    }

    @Override
    public void uponStart() {
        t = new ElapsedTime();
    }

    @Override
    public void runLoop(){
        double loopSeconds = t.seconds();

        if (this.gamepad1.dpad_left){
            leftFrontServo.setPower(servoPower);
            isLeftFrontPressed = true;
        }
        else {
            leftFrontServo.setPower(servoStopPower);
            isLeftFrontPressed = false;
        }
        if (this.gamepad1.dpad_down){
            leftRearServo.setPower(servoPower);
            isLeftRearPressed = true;
        }
        else {
            leftRearServo.setPower(servoStopPower);
            isLeftRearPressed = false;
        }
        if (this.gamepad1.dpad_up){
            rightRearServo.setPower(servoPower);
            isRightRearPressed = true;
        }
        else {
            rightRearServo.setPower(servoStopPower);
            isRightRearPressed = false;
        }
        if (this.gamepad1.dpad_right){
            rightFrontServo.setPower(servoPower);
            isRightFrontPressed = true;
        }
        else {
            rightFrontServo.setPower(servoStopPower);
            isRightFrontPressed = false;
        }

        t.reset();
        telemetry.addData("looptime",1/loopSeconds);
        telemetry.addData("LeftFront - Servo", isLeftFrontPressed);
        telemetry.addData("LeftRear - Servo", isLeftRearPressed);
        telemetry.addData("RightRear - Servo", isRightRearPressed);
        telemetry.addData("RightFront - Servo", isRightFrontPressed);
        telemetry.update();
    }
}
