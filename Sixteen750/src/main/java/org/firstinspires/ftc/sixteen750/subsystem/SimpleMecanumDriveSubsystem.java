package org.firstinspires.ftc.sixteen750.subsystem;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;

import org.firstinspires.ftc.sixteen750.Hardware;

public class SimpleMecanumDriveSubsystem {
    public EncodedMotor<DcMotorEx> leftFrontMotor;
    public EncodedMotor<DcMotorEx> leftRearMotor;
    public EncodedMotor<DcMotorEx> rightFrontMotor;
    public EncodedMotor<DcMotorEx> rightRearMotor;

    public SimpleMecanumDriveSubsystem(Hardware hardware) {
        if (hardware.leftFrontMotor != null){
            this.leftFrontMotor = hardware.leftFrontMotor;
        }
        else {
            throw new NullPointerException("leftFrontMotor is null");
        }
        if (hardware.leftRearMotor != null){
            this.leftRearMotor = hardware.leftRearMotor;
        }
        else {
            throw new NullPointerException("leftRearMotor is null");
        }
        if (hardware.rightFrontMotor != null){
            this.rightFrontMotor = hardware.rightFrontMotor;
        }
        else {
            throw new NullPointerException("rightFrontMotor is null");
        }
        if (hardware.rightRearMotor != null){
            this.rightRearMotor = hardware.rightRearMotor;
        }
        else {
            throw new NullPointerException("rightRearMotor is null");
        }
    }

    public void stop() {
        leftFrontMotor.setSpeed(0);
        leftRearMotor.setSpeed(0);
        rightFrontMotor.setSpeed(0);
        rightRearMotor.setSpeed(0);
    }

    public void goStraightForward(double power) {
        leftFrontMotor.setSpeed(power);
        leftRearMotor.setSpeed(power);
        rightFrontMotor.setSpeed(-power);
        rightRearMotor.setSpeed(-power);
    }

    public void goStraightBackward(double power) {
        leftFrontMotor.setSpeed(-power);
        leftRearMotor.setSpeed(-power);
        rightFrontMotor.setSpeed(power);
        rightRearMotor.setSpeed(power);
    }

    public void goLeft(double power) {
        leftFrontMotor.setSpeed(-power);
        leftRearMotor.setSpeed(power);
        rightFrontMotor.setSpeed(-power);
        rightRearMotor.setSpeed(power);
    }

    public void goRight(double power) {
        leftFrontMotor.setSpeed(power);
        leftRearMotor.setSpeed(-power);
        rightFrontMotor.setSpeed(power);
        rightRearMotor.setSpeed(-power);
    }

    public void turnAroundClockwise(double power) {
        leftFrontMotor.setSpeed(power);
        leftRearMotor.setSpeed(power);
        rightFrontMotor.setSpeed(power);
        rightRearMotor.setSpeed(power);
    }

    public void turnAroundCounterClockwise(double power) {
        leftFrontMotor.setSpeed(-power);
        leftRearMotor.setSpeed(-power);
        rightFrontMotor.setSpeed(-power);
        rightRearMotor.setSpeed(-power);
    }

    public void goDiagonal45(double power) {
        leftFrontMotor.setSpeed(0);
        leftRearMotor.setSpeed(power);
        rightFrontMotor.setSpeed(-power);
        rightRearMotor.setSpeed(0);
    }

    public void goDiagonal135(double power) {
        leftFrontMotor.setSpeed(-power);
        leftRearMotor.setSpeed(0);
        rightFrontMotor.setSpeed(0);
        rightRearMotor.setSpeed(-power);
    }

    public void goDiagonal225(double power) {
        leftFrontMotor.setSpeed(0);
        leftRearMotor.setSpeed(-power);
        rightFrontMotor.setSpeed(power);
        rightRearMotor.setSpeed(0);
    }

    public void goDiagonal315(double power) {
        leftFrontMotor.setSpeed(power);
        leftRearMotor.setSpeed(0);
        rightFrontMotor.setSpeed(0);
        rightRearMotor.setSpeed(power);
    }
}
