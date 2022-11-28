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
    // Make everything right related private so don't take up space in FTC-Dashboard
    public static double TICKS_PER_INCH = 136;
    public static double L_INTAKE_FLOOR;
    private static double R_INTAKE_FLOOR;
    public static double L_GROUND_JUNCTION = 1.75 * TICKS_PER_INCH;
    private static double R_GROUND_JUNCTION;
    public static double L_LOW_JUNCTION = 14.5 * TICKS_PER_INCH;
    private static double R_LOW_JUNCTION;
    public static double L_MEDIUM_JUNCTION = 25 * TICKS_PER_INCH;
    private static double R_MEDIUM_JUNCTION;
    public static double L_HIGH_JUNCTION = 36 * TICKS_PER_INCH;
    private static double R_HIGH_JUNCTION;
    public static double L_ABSOLUTE_MIN_HEIGHT = 0;
    public static double L_ABSOLUTE_MAX_HEIGHT = 38 * TICKS_PER_INCH;
    private static double R_ABSOLUTE_MIN_HEIGHT;
    private static double R_ABSOLUTE_MAX_HEIGHT;
    public static double L_MAX_MOTOR_SPEED = 0.8; // Unverified
    public static double L_MIN_MOTOR_SPEED = -0.4; // Unverified, Gravity
    private static double R_MAX_MOTOR_SPEED; // Unverified
    private static double R_MIN_MOTOR_SPEED; // Unverified, Gravity
    public static double L_REGULAR_MOVE = 1.5 * TICKS_PER_INCH;
    private static double R_REGULAR_MOVE;
    public static double L_TINY_MOVE = 0.5 * TICKS_PER_INCH; // When close to the upper limit
    private static double R_TINY_MOVE;

    // Don't change these: They're used for user-redefining the 'zero' location during gameplay
    public static double L_ACTUAL_ZERO = 0;
    private static double R_ACTUAL_ZERO;

    public static PIDCoefficients L_PID = new PIDCoefficients(0.0048, 0, 0);
    private static PIDCoefficients R_PID = new PIDCoefficients(0.0048, 0, 0);

    public static double TOLERANCE_ZONE = 0.9 * TICKS_PER_INCH;

    // This is used to hopefully counteract gravity...
    public static double DOWNWARD_SCALE_FACTOR = 0.65;

    public static double CONE_HEIGHT_DIFFERENCE = 0.9 * TICKS_PER_INCH;
    private int currentConeNumber = 5;

    private EncodedMotor<DcMotorEx> leftMotor;
    private PIDFController leftPidController;
    private final boolean isLeftConnected;

    private EncodedMotor<DcMotorEx> rightMotor;
    private PIDFController rightPidController;
    private final boolean isRightConnected;

    public LiftSubsystem(EncodedMotor<DcMotorEx> leftM, EncodedMotor<DcMotorEx> rightM, Supplier<Double> voltageGetter) {
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

    public LiftSubsystem(EncodedMotor<DcMotorEx> leftM, EncodedMotor<DcMotorEx> rightM) {
        this(leftM, rightM, () -> 12.0);
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

    private void setMotorSpeed(EncodedMotor<DcMotorEx> motor, double power) {
        // Compensate gravity effect
        if (power < 0) {
            power = power * DOWNWARD_SCALE_FACTOR;
        }
        motor.setSpeed(power);
    }

    // This is run for every loop (Feature of the TechnoLib Subsystem!)
    // So you can just call "setTop" in a command and it will get there "as soon as it can"
    @Override
    public void periodic() {
        if (isLeftConnected) {
            double leftTargetSpeed = leftPidController.update(getLeftPos());
            double leftClippedSpeed = Range.clip(leftTargetSpeed, L_MIN_MOTOR_SPEED, L_MAX_MOTOR_SPEED);
            this.setMotorSpeed(leftMotor, leftClippedSpeed);
        }
        if (isRightConnected) {
            double rTargetSpeed = rightPidController.update(getRightPos());
            double rClippedSpeed = Range.clip(rTargetSpeed, L_MIN_MOTOR_SPEED, L_MAX_MOTOR_SPEED);
            this.setMotorSpeed(rightMotor, rClippedSpeed);
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

    public double getLeftTargetPos(){
        if (isLeftConnected) {
            return leftPidController.getTargetPosition();
        }
        return 0.0;
    }

    public double getRightTargetPos(){
        if (isRightConnected) {
            return rightPidController.getTargetPosition();
        }
        return 0.0;
    }

    @Override
    public Double get() {
        // Not sure about this one
        if (isLeftConnected) {
            return leftPidController.getTargetPosition();
        } else if (isRightConnected) {
            return rightPidController.getTargetPosition();
        } else {
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

    private void setNewZero(){
        if (isLeftConnected) {
            L_ACTUAL_ZERO = leftMotor.get();
        }
        if (isRightConnected) {
            R_ACTUAL_ZERO = rightMotor.get();
        }
    }

    public double getNewConeStackIntakeHeight() {
        return currentConeNumber-- * CONE_HEIGHT_DIFFERENCE;
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

    public void gotoFloorIntake() {
        // null check will be done in setTargetPosition()
        setTargetPositionWithLogging(L_INTAKE_FLOOR, R_INTAKE_FLOOR);
    }

    public void gotoConeStackIntake() {
        // null check will be done in setTargetPosition()
        setTargetPositionWithLogging(getNewConeStackIntakeHeight(), getNewConeStackIntakeHeight());
    }

    public void moveUp() {
        setTargetPositionWithLogging(getLeftTargetPos() + L_REGULAR_MOVE, getLeftTargetPos() + R_REGULAR_MOVE);
    }

    public void moveDown() {
        setTargetPositionWithLogging(getLeftTargetPos() - L_REGULAR_MOVE, getLeftTargetPos() - R_REGULAR_MOVE);
    }
}