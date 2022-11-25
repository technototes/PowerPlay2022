package org.firstinspires.ftc.sixteen750.opmode.diagnosis;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.sixteen750.Hardware;

@Config
@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class DriveMotorTest extends CommandOpMode {
    ElapsedTime t;

    EncodedMotor<DcMotorEx> leftFrontMotor;
    EncodedMotor<DcMotorEx> leftRearMotor;
    EncodedMotor<DcMotorEx> rightFrontMotor;
    EncodedMotor<DcMotorEx> rightRearMotor;

    boolean isLeftFrontPressed, isLeftRearPressed, isRightRearPressed, isRightFrontPressed = false;

    // 0.1 is too little, the motor trying to move but not enough to move the robot; 0.2 is slightly better
    public static double motorSpeed = 0.3;
    public static double motorStopSpeed = 0.0;

    boolean isLeftSideConnected = true;
    boolean isRightSideConnected = true;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        /// Note: here is using the hardware from TechnoLib
        try {
            leftFrontMotor = new EncodedMotor<>(Hardware.HardwareConstant.LF_MOTOR);
            leftRearMotor = new EncodedMotor<>(Hardware.HardwareConstant.LR_MOTOR);
        } catch (Exception e) {
            isLeftSideConnected = false;
        }

        try {
            rightFrontMotor = new EncodedMotor<>(Hardware.HardwareConstant.RF_MOTOR);
            rightRearMotor = new EncodedMotor<>(Hardware.HardwareConstant.RR_MOTOR);
        } catch (Exception e) {
            isRightSideConnected = false;
        }
    }

    @Override
    public void uponStart() {
        t = new ElapsedTime();
    }

    @Override
    public void runLoop() {
        double loopSeconds = t.seconds();

        if (this.gamepad1.dpad_left && isLeftSideConnected) {
            leftFrontMotor.setSpeed(motorSpeed);
            isLeftFrontPressed = true;
        } else {
            isLeftFrontPressed = false;
        }
        if (this.gamepad1.dpad_down && isLeftSideConnected) {
            leftRearMotor.setSpeed(motorSpeed);
            isLeftRearPressed = true;
        } else {
            isLeftRearPressed = false;
        }
        if (this.gamepad1.dpad_up && isRightSideConnected) {
            rightRearMotor.setSpeed(motorSpeed);
            isRightRearPressed = true;
        } else {
            isRightRearPressed = false;
        }
        if (this.gamepad1.dpad_right && isRightSideConnected) {
            rightFrontMotor.setSpeed(motorSpeed);
            isRightFrontPressed = true;
        } else {
            isRightFrontPressed = false;
        }

        telemetry.addLine("Visit 192.168.43.1:8080/dash to see the FTC-Dashboard");
        telemetry.addData("Loop Seconds", loopSeconds);

        if (isLeftSideConnected) {
            telemetry.addData(
                    "LeftFront - Motor - Encoder", leftFrontMotor.getEncoder().getPosition());
            telemetry.addData(
                    "LeftRear - Motor - Encoder", leftRearMotor.getEncoder().getPosition());
        } else {
            telemetry.addLine("WARNING: Left Disconnected");
        }
        if (isRightSideConnected) {
            telemetry.addData(
                    "RightRear - Motor - Encoder", rightRearMotor.getEncoder().getPosition());
            telemetry.addData(
                    "RightFront - Motor - Encoder", rightFrontMotor.getEncoder().getPosition());
        } else {
            telemetry.addLine("WARNING: Right Disconnected");
        }

        telemetry.addData("LeftFront - Motor - Pressed", isLeftFrontPressed);
        telemetry.addData("LeftRear - Motor - Pressed", isLeftRearPressed);
        telemetry.addData("RightRear - Motor - Pressed", isRightRearPressed);
        telemetry.addData("RightFront - Motor - Pressed", isRightFrontPressed);

        telemetry.update();
    }
}
