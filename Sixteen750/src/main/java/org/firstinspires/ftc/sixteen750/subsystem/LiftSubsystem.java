package org.firstinspires.ftc.sixteen750.subsystem;

import java.util.function.Supplier;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

@Config
@SuppressWarnings("unused")
public class LiftSubsystem implements Subsystem, Supplier<Double>, Loggable {
    // Assuming the 0 position for both lift motor might be different
    // The LiftSubsystem should be able to any of the motor combination
    // TODO: THESE VALUES ARE ALL WRONG! THEY NEED TO BE SET TO THE RIGHT VALUES!!!!
    public static double TICKS_INCH = 750; // WRONG,
    public static double L_INTAKE;
    public static double R_INTAKE;
    public static double L_GROUND_JUNCTION = 0.5 * TICKS_INCH; // WRONG
    public static double R_GROUND_JUNCTION = 0.5 * TICKS_INCH; // WRONG
    public static double L_LOW_JUNCTION = 12 * TICKS_INCH; // WRONG
    public static double R_LOW_JUNCTION = 12 * TICKS_INCH; // WRONG
    public static double L_MEDIUM_JUNCTION = 24 * TICKS_INCH; // WRONG
    public static double R_MEDIUM_JUNCTION = 24 * TICKS_INCH; // WRONG
    public static double L_HIGH_JUNCTION = 36 * TICKS_INCH; // WRONG
    public static double R_HIGH_JUNCTION = 36 * TICKS_INCH; // WRONG
    public static double L_ABSOLUTE_MIN_HEIGHT = 15;
    public static double L_ABSOLUTE_MAX_HEIGHT = 1651;
    public static double R_ABSOLUTE_MIN_HEIGHT = 15; // COPIED FROM LEFT
    public static double R_ABSOLUTE_MAX_HEIGHT = 1651; // COPIED FROM LEFT
    public static double L_MAX_MOTOR_SPEED = 0.8; // Unverified
    public static double L_MIN_MOTOR_SPEED = -0.4; // Unverified, Gravity
    public static double R_MAX_MOTOR_SPEED = 0.8; // Unverified
    public static double R_MIN_MOTOR_SPEED = -0.4; // Unverified, Gravity
    public static double L_REGULAR_MOVE = 10;
    public static double R_REGULAR_MOVE = 10;
    public static double L_TINY_MOVE = 5; // When close to the upper limit
    public static double R_TINY_MOVE = 5; // When close to the upper limit
    public static PIDCoefficients L_PID = new PIDCoefficients(0.008, 0, 0.0005); // Unverified
    public static PIDCoefficients R_PID = new PIDCoefficients(0.008, 0, 0.0005); // Unverified
    public static double TOLERANCE_ZONE = 2; // Unverified

    private EncodedMotor<DcMotorEx> leftMotor;
    private PIDFController leftPidController;
    private final boolean isLeftConnected;

    private EncodedMotor<DcMotorEx> rightMotor;
    private PIDFController rightPidController;
    private final boolean isRightConnected;

    public LiftSubsystem(EncodedMotor<DcMotorEx> leftM, EncodedMotor<DcMotorEx> rightM) {
        if (leftM != null) {
            this.leftMotor = leftM;
            this.leftPidController = new PIDFController(L_PID, 0, 0, 0, (x, y) -> 0.1);
            this.isLeftConnected = true;
        } else {
            this.isLeftConnected = false;
        }

        if (rightM != null) {
            this.rightMotor = rightM;
            this.rightPidController = new PIDFController(R_PID, 0, 0, 0, (x, y) -> 0.1);
            this.isRightConnected = true;
        } else {
            this.isRightConnected = false;
        }
    }

    private void setTargetPosition(double lPos, double rPos) {
        if (isLeftConnected) {
            leftPidController.setTargetPosition(Range.clip(lPos, L_ABSOLUTE_MIN_HEIGHT, L_ABSOLUTE_MAX_HEIGHT));
        }
        if (isRightConnected) {
            rightPidController.setTargetPosition(Range.clip(rPos, R_ABSOLUTE_MIN_HEIGHT, R_ABSOLUTE_MAX_HEIGHT));
        }
    }

    private void setTargetPositionWithLogging(double lPos, double rPos) {
        setTargetPosition(lPos, rPos);
        if (isLeftConnected) {
            lpAndActual = String.format("%d (%d)", (int) lPos, leftMotor.get().intValue());
        }
        if (isRightConnected) {
            rpAndActual = String.format("%d (%d)", (int) rPos, rightMotor.get().intValue());
        }
    }

    // This is run for every loop (Feature of the TechnoLib Subsystem!)
    // So you can just call "setTop" in a command and it will get there "as soon as it can"
    @Override
    public void periodic() {
        if (isLeftConnected) {
            double leftTargetSpeed = leftPidController.update(getLeftPos());
            double leftClippedSpeed = Range.clip(leftTargetSpeed, L_MIN_MOTOR_SPEED, L_MAX_MOTOR_SPEED);
            leftMotor.setSpeed(leftClippedSpeed);
        }
        if (isRightConnected) {
            double rTargetSpeed = rightPidController.update(getRightPos());
            double rClippedSpeed = Range.clip(rTargetSpeed, L_MIN_MOTOR_SPEED, L_MAX_MOTOR_SPEED);
            rightMotor.setSpeed(rClippedSpeed);
        }
    }

    public double leftDelta() {
        return leftPidController.getTargetPosition() - leftMotor.getDevice().getCurrentPosition();
    }

    public double rightDelta() {
        return rightPidController.getTargetPosition() - rightMotor.getDevice().getCurrentPosition();
    }

    public boolean isLeftAtTarget() {
        return Math.abs(leftDelta()) < TOLERANCE_ZONE;
    }

    public boolean isRightAtTarget() {
        return Math.abs(rightDelta()) < TOLERANCE_ZONE;
    }

    public double getLeftPos() {
        if (isLeftConnected) {
            return leftMotor.getEncoder().getPosition();
        }
        return 0.0;
    }

    public double getRightPos() {
        if (isRightConnected) {
            // Invert the sign on this one to make it look like it's rotating the same way...
            return -rightMotor.getEncoder().getPosition();
        }
        return 0.0;
    }

    @Override
    public Double get() {
        // Not sure about this one
        if (isLeftConnected) {
            return leftPidController.getTargetPosition();
        }
        else if (isRightConnected) {
            return rightPidController.getTargetPosition();
        }
        else {
            return 0.0;
        }
    }

    // TODO: enable as needed
    // @Log(name = "lEncMotor Pos (Actual)")
    public volatile String lpAndActual = "(unknown)";

    // TODO: enable as needed
    // @Log(name = "rEncMotor Pos (Actual)")
    public volatile String rpAndActual = "(unknown)";

    public void stop() {
        // By resetting the pidController, it stops the periodic function from making changes
        if (isLeftConnected) {
            leftPidController.reset();
        }
        if (isRightConnected) {
            rightPidController.reset();
        }
    }

    public void halt() {
        // Just set the target position to the current position to get the motor to stop, yes?
        if (isLeftConnected) {
            leftPidController.setTargetPosition(this.getLeftPos());
        }
        if (isRightConnected) {
            rightPidController.setTargetPosition(this.getRightPos());
        }
    }

    public void gotoTop() {
        // null check will be done in setTargetPosition()
        this.setTargetPosition(L_ABSOLUTE_MAX_HEIGHT, R_ABSOLUTE_MAX_HEIGHT);
    }

    public void gotoBottom() {
        // null check will be done in setTargetPosition()
        this.setTargetPosition(L_ABSOLUTE_MIN_HEIGHT, R_ABSOLUTE_MIN_HEIGHT);
    }

    public void gotoHighPole() {
        // null check will be done in setTargetPosition()
        setTargetPositionWithLogging(L_HIGH_JUNCTION, R_HIGH_JUNCTION);
    }

    public void gotoMidPole() {
        // null check will be done in setTargetPosition()
        setTargetPositionWithLogging(L_MEDIUM_JUNCTION, R_MEDIUM_JUNCTION);
    }

    public void gotoLowPole() {
        // null check will be done in setTargetPosition()
        setTargetPositionWithLogging(L_LOW_JUNCTION, R_LOW_JUNCTION);
    }

    public void gotoGroundJunction() {
        // null check will be done in setTargetPosition()
        setTargetPositionWithLogging(L_GROUND_JUNCTION, R_GROUND_JUNCTION);
    }

    public void moveUp() {
        if (isLeftConnected) {
            // maybe getCurrentPosition instead of getTargetPosition
            double rightTargetPosition = leftPidController.getTargetPosition();
            setTargetPositionWithLogging(rightTargetPosition + L_REGULAR_MOVE, rightTargetPosition + R_REGULAR_MOVE);
        }
    }

    public void moveDown() {
        if (isRightConnected) {
            // maybe getCurrentPosition instead of getTargetPosition
            double leftTargetPosition = leftPidController.getTargetPosition();
            setTargetPositionWithLogging(leftTargetPosition - L_REGULAR_MOVE, leftTargetPosition - R_REGULAR_MOVE);
        }
    }
}
