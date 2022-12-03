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
public class LiftSubsystem implements Subsystem, Supplier<Double>, Loggable {
    // Assuming the 0 position for both lift motor might be different?
    // The LiftSubsystem should be able to any of the motor combination
    public static double TICKS_PER_INCH = 136;
    public static double L_INTAKE_FLOOR = 0.1 * TICKS_PER_INCH; // wrong
    public static double L_GROUND_JUNCTION = 1.75 * TICKS_PER_INCH;
    public static double L_LOW_JUNCTION = 14.5 * TICKS_PER_INCH;
    public static double L_MEDIUM_JUNCTION = 25 * TICKS_PER_INCH;
    public static double L_HIGH_JUNCTION = 36 * TICKS_PER_INCH;
    public static double L_ABSOLUTE_MIN_HEIGHT = 0;
    public static double L_ABSOLUTE_MAX_HEIGHT = 38 * TICKS_PER_INCH;
    public static double L_MAX_MOTOR_SPEED = 0.8; // Unverified
    public static double L_MIN_MOTOR_SPEED = -0.4; // Unverified, Gravity
    public static double L_REGULAR_MOVE = 1.5 * TICKS_PER_INCH;
    public static double L_TINY_MOVE = 0.5 * TICKS_PER_INCH; // When close to the upper limit

    // Don't change these: They're used for user-redefining the 'zero' location during gameplay
    public static double L_ACTUAL_ZERO = 0;

    public static PIDCoefficients L_PID = new PIDCoefficients(0.0048, 0, 0);

    public static double TOLERANCE_ZONE = 0.9 * TICKS_PER_INCH;

    // This is used to hopefully counteract gravity...
    public static double DOWNWARD_SCALE_FACTOR = 0.65;

    public static double CONE_HEIGHT_DIFFERENCE = 0.9 * TICKS_PER_INCH;
    private int currentConeNumber = 5;

    private EncodedMotor<DcMotorEx> leftMotor;
    private PIDFController leftPidController;
    private final boolean isLeftConnected;

    private double currentVoltage = 0;
    private Supplier<Double> voltageGetter;

    public LiftSubsystem(EncodedMotor<DcMotorEx> leftMotor, Supplier<Double> voltageGetter) {
        this.voltageGetter = voltageGetter;
        this.currentVoltage = (this.voltageGetter == null) ? 0 : this.voltageGetter.get();
        if (leftMotor != null) {
            this.leftMotor = leftMotor;
            this.leftPidController = new PIDFController(L_PID, 0, 0, 0, (x, y) -> 0.1);
            this.isLeftConnected = true;
            System.out.println("Left Lift Motor Connected");
        } else {
            this.isLeftConnected = false;
            System.out.println("Left motor is not connected!");
        }
    }

    public LiftSubsystem(EncodedMotor<DcMotorEx> leftMotor) {
        this(leftMotor, () -> 12.0);
    }

    private void setTargetPosition(double leftTargetPos) {
        if (isLeftConnected) {
            leftPidController.setTargetPosition(Range.clip(leftTargetPos, L_ABSOLUTE_MIN_HEIGHT, L_ABSOLUTE_MAX_HEIGHT));
        }
    }

    private void setTargetPostion_OVERRIDE(double lpos) {
        leftPidController.setTargetPosition(lpos);
    }

    private void setTargetPositionWithLogging(double leftTargetPos) {
        setTargetPosition(leftTargetPos);
        if (isLeftConnected) {
            lpAndActual = String.format("%d (%d)", (int) leftTargetPos, leftMotor.get().intValue());
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
    }

    public double leftDelta() {
        return leftPidController.getTargetPosition() - leftMotor.getDevice().getCurrentPosition();
    }

    public boolean isLeftAtTarget() {
        return Math.abs(leftDelta()) < TOLERANCE_ZONE;
    }

    public double getLeftPos() {
        if (isLeftConnected) {
            return leftMotor.getEncoder().getPosition();
        }
        return 0.0;
    }

    public double getLeftTargetPos() {
        if (isLeftConnected) {
            return leftPidController.getTargetPosition();
        }
        return 0.0;
    }

    @Override
    public Double get() {
        // Not sure about this one
        if (isLeftConnected) {
            return leftPidController.getTargetPosition();
        } else {
            return 0.0;
        }
    }

    // TODO: enable as needed
    // @Log(name = "lEncMotor Pos (Actual)")
    public volatile String lpAndActual = "(unknown)";

    public void stop() {
        // By resetting the pidController, it stops the periodic function from making changes
        if (isLeftConnected) {
            leftPidController.reset();
        }
    }

    public void halt() {
        // Just set the target position to the current position to get the motor to stop, yes?
        if (isLeftConnected) {
            leftPidController.setTargetPosition(this.getLeftPos());
        }
    }

    private void setNewZero() {
        if (isLeftConnected) {
            L_ACTUAL_ZERO = leftMotor.get();
        }
    }

    public double getNewConeStackIntakeHeight() {
        return currentConeNumber-- * CONE_HEIGHT_DIFFERENCE;
    }

    public void gotoTop() {
        this.setTargetPosition(L_ABSOLUTE_MAX_HEIGHT);
    }

    public void gotoBottom() {
        this.setTargetPosition(L_ABSOLUTE_MIN_HEIGHT);
    }

    public void gotoHighPole() {
        setTargetPositionWithLogging(L_HIGH_JUNCTION);
    }

    public void gotoMidPole() {
        setTargetPositionWithLogging(L_MEDIUM_JUNCTION);
    }

    public void gotoLowPole() {
        setTargetPositionWithLogging(L_LOW_JUNCTION);
    }

    public void gotoGroundJunction() {
        setTargetPositionWithLogging(L_GROUND_JUNCTION);
    }

    public void gotoFloorIntake() {
        setTargetPositionWithLogging(L_INTAKE_FLOOR);
    }

    public void gotoConeStackIntake() {
        setTargetPositionWithLogging(getNewConeStackIntakeHeight());
    }

    public void moveUp() {
        setTargetPositionWithLogging(getLeftTargetPos() + L_REGULAR_MOVE);
    }

    public void moveUpOVERRIDE() {
        setTargetPostion_OVERRIDE(getLeftTargetPos() + L_REGULAR_MOVE);
    }

    public void moveDownOVERRIDE() {
        setTargetPostion_OVERRIDE(getLeftTargetPos() - L_REGULAR_MOVE);
    }

    public void moveDown() {
        setTargetPositionWithLogging(getLeftTargetPos() - L_REGULAR_MOVE);
    }

    public void setVoltage(double voltage) {
        this.currentVoltage = voltage;
    }

    public void updateVoltage() {
        this.setVoltage(this.voltageGetter.get());
    }
}