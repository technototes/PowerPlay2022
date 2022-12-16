package org.firstinspires.ftc.sixteen750.subsystem.drive;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;

import org.firstinspires.ftc.sixteen750.Hardware;

@Config
public class SimpleMecanumDriveSubsystem {
    public static int brakeTime = 1500;
    public static int autoGoForwardTicks = 2100;
    // should be fine to hitting the wall
    public static int autoGoLeftTicks = 1200;
    public static int autoGoRightTicks = 1200;

    public EncodedMotor<DcMotorEx> leftFrontMotor;
    public EncodedMotor<DcMotorEx> leftRearMotor;
    public EncodedMotor<DcMotorEx> rightFrontMotor;
    public EncodedMotor<DcMotorEx> rightRearMotor;

    double leftFrontZero = 0;
    double leftRearZero = 0;
    double rightFrontZero = 0;
    double rightRearZero = 0;

    public SimpleMecanumDriveSubsystem(Hardware hardware) {
        if (hardware.leftFrontMotorT != null){
            this.leftFrontMotor = hardware.leftFrontMotorT;
        }
        else {
            throw new NullPointerException("leftFrontMotor is null");
        }
        if (hardware.leftRearMotorT != null){
            this.leftRearMotor = hardware.leftRearMotorT;
        }
        else {
            throw new NullPointerException("leftRearMotor is null");
        }
        if (hardware.rightFrontMotorT != null){
            this.rightFrontMotor = hardware.rightFrontMotorT;
        }
        else {
            throw new NullPointerException("rightFrontMotor is null");
        }
        if (hardware.rightRearMotorT != null){
            this.rightRearMotor = hardware.rightRearMotorT;
        }
        else {
            throw new NullPointerException("rightRearMotor is null");
        }
    }

    public double[] getEncoderValues() {
        return new double[] {
                leftFrontMotor.getEncoder().getPosition(),
                leftRearMotor.getEncoder().getPosition(),
                rightFrontMotor.getEncoder().getPosition(),
                rightRearMotor.getEncoder().getPosition(),
        };
    }

    public void setEncoderZero() {
        leftFrontZero = leftFrontMotor.getEncoder().getPosition();
        leftRearZero = leftRearMotor.getEncoder().getPosition();
        rightFrontZero = rightFrontMotor.getEncoder().getPosition();
        rightRearZero = rightRearMotor.getEncoder().getPosition();
    }

    public double[] getAdjustedEncoderValues() {
        return new double[] {
                leftFrontMotor.getEncoder().getPosition() - leftFrontZero,
                leftRearMotor.getEncoder().getPosition() - leftRearZero,
                rightFrontMotor.getEncoder().getPosition() - rightFrontZero,
                rightRearMotor.getEncoder().getPosition() - rightRearZero,
        };
    }

    public void stop() {
        leftFrontMotor.setSpeed(0);
        leftRearMotor.setSpeed(0);
        rightFrontMotor.setSpeed(0);
        rightRearMotor.setSpeed(0);
    }

    public void stopAndWait() {
        stop();
        try {
            Thread.sleep(brakeTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
