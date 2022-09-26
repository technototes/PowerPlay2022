package org.firstinspires.ftc.forteaching.TechnoBot.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

public class MotorAsServoSubsystem implements Subsystem, Supplier<Double>, Loggable {
    @Config
    public static class MotorAsServoConstants {
        public static double LOWER_LIMIT = 0.0;
        public static double UPPER_LIMIT = 2000.0;
        public static double DEAD_ZONE = 10.0;
        public static double MAX_MOTOR_SPEED = 0.8;
        public static double MIN_MOTOR_SPEED = -0.4; // Gravity
        public static PIDCoefficients PID = new PIDCoefficients(0.008, 0, 0.0005);
    }

    private EncodedMotor<DcMotorEx> motor;
    private PIDFController pidController;

    public MotorAsServoSubsystem(EncodedMotor<DcMotorEx> m) {
        motor = m;
        pidController = new PIDFController(
                MotorAsServoConstants.PID, 0, 0, 0, (x, y) -> 0.1);
    }

    public void setPosition(double pos) {
        pidController.setTargetPosition(Range.clip(
                pos, MotorAsServoConstants.LOWER_LIMIT, MotorAsServoConstants.UPPER_LIMIT));
    }

    public void setTop() {
        pidController.setTargetPosition(MotorAsServoConstants.UPPER_LIMIT);
    }

    public void setBottom() {
        pidController.setTargetPosition(MotorAsServoConstants.LOWER_LIMIT);
    }

    public void halt() {
        // By resetting the pidController, it stops the periodic function from making changes
        pidController.reset();
    }

    private void setEncMotor(double val) {
        setPosition(val);
        pAndActual = String.format("%d (%d)", (int) val, motor.get().intValue());
    }

    public void increaseEncMotor() {
        setEncMotor(get() + MovementTestingSubsystem.TestingValues.ENCODED_MOTOR_DELTA);
    }

    public void decreaseEncMotor() {
        setEncMotor(get() - MovementTestingSubsystem.TestingValues.ENCODED_MOTOR_DELTA);
    }

    public void stopEncMotor() {
        setEncMotor(0);
    }

    // This is run for every loop (Feature of the TechnoLib Subsystem!)
    // So you can just call "setTop" in a command and it will get there "as soon as it can"
    @Override
    public void periodic() {
        double targetSpeed = pidController.update(motor.get());
        double clippedSpeed = Range.clip(targetSpeed, MotorAsServoConstants.MIN_MOTOR_SPEED, MotorAsServoConstants.MAX_MOTOR_SPEED);
        motor.setSpeed(clippedSpeed);
        // For logging purposes, I'm also doing this, to ensure that both values are updated
        setEncMotor(get());
    }

    public double delta() {
        return pidController.getTargetPosition() - motor.getDevice().getCurrentPosition();
    }

    public boolean isAtTarget() {
        return Math.abs(delta()) < MotorAsServoConstants.DEAD_ZONE;
    }

    @Override
    public Double get() {
        return pidController.getTargetPosition();
    }

    @Log(name = "EncMotor Pos (Actual)")
    public volatile String pAndActual = "(unknown)";
}
