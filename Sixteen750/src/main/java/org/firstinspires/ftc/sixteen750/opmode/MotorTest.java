package org.firstinspires.ftc.sixteen750.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.structure.CommandOpMode;


@Config
@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class MotorTest extends CommandOpMode {
    ElapsedTime t;

    EncodedMotor<DcMotorEx> leftFrontMotor, leftRearMotor, rightRearMotor, rightFrontMotor;

    boolean isLeftFrontPressed, isLeftRearPressed, isRightRearPressed, isRightFrontPressed = false;

    public static double motorSpeed = 0.3; // 0.1 is too little, the motor trying to move but not enough to move the robot; 0.2 is slightly better
    public static double motorStopSpeed = 0.0;

    boolean isLeftSideConnected, isRightSideConnected = true;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        /// Note: here is using the hardware from TechnoLib
        try {
            leftFrontMotor = new EncodedMotor<>(hardwareMap.get(DcMotorEx.class, "leftFrontMotor"));
            leftRearMotor = new EncodedMotor<>(hardwareMap.get(DcMotorEx.class, "leftRearMotor"));
        } catch (Exception e) {
            isLeftSideConnected = false;
        }

        try {
            rightRearMotor = new EncodedMotor<>(hardwareMap.get(DcMotorEx.class, "rightRearMotor"));
            rightFrontMotor = new EncodedMotor<>(hardwareMap.get(DcMotorEx.class, "rightFrontMotor"));
        } catch (Exception e) {
            isRightSideConnected = false;
        }
    }

    @Override
    public void uponStart() {
        t = new ElapsedTime();
    }

    @Override
    public void runLoop(){
        double loopSeconds = t.seconds();

        if (this.gamepad1.dpad_left && isLeftSideConnected){
            leftFrontMotor.setSpeed(motorSpeed);
            isLeftFrontPressed = true;
        }
        else {
            leftFrontMotor.setSpeed(motorStopSpeed);
            isLeftFrontPressed = false;
        }
        if (this.gamepad1.dpad_down && isLeftSideConnected){
            leftRearMotor.setSpeed(motorSpeed);
            isLeftRearPressed = true;
        }
        else {
            leftRearMotor.setSpeed(motorStopSpeed);
            isLeftRearPressed = false;
        }
        if (this.gamepad1.dpad_up && isRightSideConnected){
            rightRearMotor.setSpeed(motorSpeed);
            isRightRearPressed = true;
        }
        else {
            rightRearMotor.setSpeed(motorStopSpeed);
            isRightRearPressed = false;
        }
        if (this.gamepad1.dpad_right && isRightSideConnected){
            rightFrontMotor.setSpeed(motorSpeed);
            isRightFrontPressed = true;
        }
        else {
            rightFrontMotor.setSpeed(motorStopSpeed);
            isRightFrontPressed = false;
        }

        telemetry.addData("Loop Seconds", loopSeconds);

        if (isLeftSideConnected) {
            telemetry.addData("LeftFront - Motor - Encoder", leftFrontMotor.getEncoder().getPosition());
            telemetry.addData("LeftRear - Motor - Encoder", leftRearMotor.getEncoder().getPosition());
        }
        if (isRightSideConnected) {
            telemetry.addData("RightRear - Motor - Encoder", rightRearMotor.getEncoder().getPosition());
            telemetry.addData("RightFront - Motor - Encoder", rightFrontMotor.getEncoder().getPosition());
        }

        telemetry.addData("LeftFront - Motor - Pressed", isLeftFrontPressed);
        telemetry.addData("LeftRear - Motor - Pressed", isLeftRearPressed);
        telemetry.addData("RightRear - Motor - Pressed", isRightRearPressed);
        telemetry.addData("RightFront - Motor - Pressed", isRightFrontPressed);

        telemetry.update();
    }
}
