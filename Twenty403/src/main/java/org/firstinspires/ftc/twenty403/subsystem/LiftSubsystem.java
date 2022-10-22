package org.firstinspires.ftc.twenty403.subsystem;

import androidx.core.math.MathUtils;

import java.util.function.Supplier;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

@Config
public class LiftSubsystem implements Subsystem, Supplier<Double>, Loggable {
    // TODO: THESE VALUES ARE ALL WRONG! THEY NEED TO BE SET TO THE RIGHT VALUES!!!!
    public static double TICKS_INCH = 750;
    public static double LOW_JUNCTION = .5 * TICKS_INCH;
    public static double MEDIUM_JUNCTION = 12 * TICKS_INCH;
    public static double HIGH_JUNCTION = 24 * TICKS_INCH;
    public static double MAX_HEIGHT = 27 * TICKS_INCH;
    public static double MIN_HEIGHT = 0;
    public static double MAX_DISTANCE_FOR_FULLPOWER = 8 * TICKS_INCH;
    public static double DEAD_ZONE = .25 * TICKS_INCH;
    public static double MAX_MOTOR_SPEED = 0.8;
    public static double MIN_MOTOR_SPEED = -0.4; // Gravity
    public static PIDCoefficients PID = new PIDCoefficients(0.008, 0, 0.0005);

    private DcMotorEx liftMotor;

    private EncodedMotor<DcMotorEx> motor;
    private PIDFController pidController;

    public LiftSubsystem(EncodedMotor<DcMotorEx> m) {
        motor = m;
        pidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
    }

    public void setPosition(double pos) {
        pidController.setTargetPosition(Range.clip(pos, MIN_HEIGHT, MAX_HEIGHT));
    }

    public void setTop() {
        pidController.setTargetPosition(MAX_HEIGHT);
    }

    public void setBottom() {
        pidController.setTargetPosition(MAX_HEIGHT);
    }

    public void stop() {
        // By resetting the pidController, it stops the periodic function from making changes
        pidController.reset();
    }

    public void halt() {
        // Just set the target position to the current position to get the motor to stop, yes?
        pidController.setTargetPosition(motor.get());
    }

    private void setEncMotor(double val) {
        setPosition(val);
        pAndActual = String.format("%d (%d)", (int) val, motor.get().intValue());
    }

    public void stopEncMotor() {
        setEncMotor(0);
    }
    // This is run for every loop (Feature of the TechnoLib Subsystem!)
    // So you can just call "setTop" in a command and it will get there "as soon as it can"
    @Override
    public void periodic() {
        double targetSpeed = pidController.update(motor.get());
        double clippedSpeed = Range.clip(targetSpeed, MIN_MOTOR_SPEED, MAX_MOTOR_SPEED);
        motor.setSpeed(clippedSpeed);
        // For logging purposes, I'm also doing this, to ensure that both values are updated
        setEncMotor(get());
    }

    public double delta() {
        return pidController.getTargetPosition() - motor.getDevice().getCurrentPosition();
    }

    public boolean isAtTarget() {
        return Math.abs(delta()) < DEAD_ZONE;
    }

    @Log(name = "EncMotor Pos (Actual)")
    public volatile String pAndActual = "(unknown)";

    @Override
    public Double get() {
        return pidController.getTargetPosition();
    }

    private boolean closeEnough(double currentPostion, double targetPostion) {
        double error = currentPostion - targetPostion;
        if (error <= DEAD_ZONE && error >= -DEAD_ZONE) {
            return true;
        } else {
            return false;
        }
    }

    private void powerMotorForError(double error) {
        // If we're error distance away from position,
        // set motor power to something that can reach position
        double power = error / MAX_DISTANCE_FOR_FULLPOWER;
        power = MathUtils.clamp(power, -1, 1);
        power = Math.cbrt(power);
        liftMotor.setPower(power);
    }

    public void highPole() {
        double position = liftMotor.getCurrentPosition();
        if (closeEnough(position, HIGH_JUNCTION)) {
            return;
        }
        double error = position - HIGH_JUNCTION;
        powerMotorForError(error);
    }

    public void midPole() {
        double position = liftMotor.getCurrentPosition();
        if (closeEnough(position, MEDIUM_JUNCTION)) {
            return;
        }
        double error = position - MEDIUM_JUNCTION;
        powerMotorForError(error);
    }

    public void lowPole() {
        double position = liftMotor.getCurrentPosition();
        if (closeEnough(position, LOW_JUNCTION)) {
            return;
        }
        double error = position - LOW_JUNCTION;
        powerMotorForError(error);
    }

    public void moveUp() {
        double position = liftMotor.getCurrentPosition();
        if (position >= MAX_HEIGHT) {
            return;
        }
        double error = position - MAX_HEIGHT;
        powerMotorForError(error);
    }

    public void moveDown() {

        double position = liftMotor.getCurrentPosition();
        if (position <= MIN_HEIGHT) {
            return;
        }
        double error = position - MIN_HEIGHT;
        powerMotorForError(error);
    }
}
