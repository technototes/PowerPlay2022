package org.firstinspires.ftc.sixteen750.subsystem;

import androidx.core.math.MathUtils;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.technototes.library.subsystem.Subsystem;

@Config
public class LiftSubsystem implements Subsystem {
    // TODO: THESE VALUES ARE ALL WRONG! THEY NEED TO BE SET TO THE RIGHT VALUES!!!!
    public static double TICKS_INCH = 750;
    public static double LOW_JUNCTION = .5 * TICKS_INCH;
    public static double MEDIUM_JUNCTION = 12 * TICKS_INCH;
    public static double HIGH_JUNCTION = 24 * TICKS_INCH;
    public static double MAX_HEIGHT = 27 * TICKS_INCH;
    public static double MIN_HEIGHT = 0;
    public static double MAX_DISTANCE_FOR_FULLPOWER = 8 * TICKS_INCH;
    public static double DEAD_ZONE = .25 * TICKS_INCH;

    private DcMotorEx liftMotor;

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

    public LiftSubsystem(DcMotorEx motor) {
        liftMotor = motor;
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

    public void intake() {
        // TODO:
    }

    public void carry() {
        // TODO:
    }
}
