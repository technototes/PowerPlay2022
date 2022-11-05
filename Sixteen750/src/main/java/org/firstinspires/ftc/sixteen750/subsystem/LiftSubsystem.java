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
    // TODO: THESE VALUES ARE ALL WRONG! THEY NEED TO BE SET TO THE RIGHT VALUES!!!!
    public static double TICKS_INCH = 750; // WRONG,
    public static double L_GROUND_JUNCTION = 0.5 * TICKS_INCH; // WRONG
    public static double R_GROUND_JUNCTION = 0.5 * TICKS_INCH; // WRONG
    public static double L_LOW_JUNCTION = 12 * TICKS_INCH; // WRONG
    public static double R_LOW_JUNCTION = 12 * TICKS_INCH; // WRONG
    public static double L_MEDIUM_JUNCTION = 24 * TICKS_INCH; // WRONG
    public static double R_MEDIUM_JUNCTION = 24 * TICKS_INCH; // WRONG
    public static double L_HIGH_JUNCTION = 36 * TICKS_INCH; // WRONG
    public static double R_HIGH_JUNCTION = 36 * TICKS_INCH; // WRONG
    public static double MAX_HEIGHT = 38 * TICKS_INCH; // WRONG
    public static double LEFT_ABSOLUTE_MAX_HEIGHT = 544;
    public static double RIGHT_ABSOLUTE_MAX_HEIGHT = 566;
    public static double MIN_HEIGHT = 0;
    public static double LEFT_ABSOLUTE_MIN_HEIGHT = 0;
    public static double RIGHT_ABSOLUTE_MIN_HEIGHT = 0;
    public static double MAX_DISTANCE_FOR_FULLPOWER = 8 * TICKS_INCH; // WRONG
    public static double DEAD_ZONE = .25 * TICKS_INCH; // WRONG
    public static double MAX_MOTOR_SPEED = 0.8;
    public static double MIN_MOTOR_SPEED = -0.4; // Gravity
    public static double L_INCREMENTAL_MOVE = 10;
    public static double R_INCREMENTAL_MOVE = 10;
    public static PIDCoefficients PID = new PIDCoefficients(0.008, 0, 0.0005);

    private EncodedMotor<DcMotorEx> leftMotor;
    private PIDFController leftPidController;

    private boolean singleMotor;
    private boolean isHardwareConnected;
    private EncodedMotor<DcMotorEx> rightMotor;
    private PIDFController rightPidController;

    public LiftSubsystem(EncodedMotor<DcMotorEx> leftM, EncodedMotor<DcMotorEx> rightM) {
        this.leftMotor = leftM;
        this.leftPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);

        this.rightMotor = rightM;
        this.rightPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
        this.singleMotor = false;
        this.isHardwareConnected = true;
    }

    // TODO: figure out which motor to use in single motor mode
    public LiftSubsystem(EncodedMotor<DcMotorEx> leftM) {
        isHardwareConnected = true;
        leftMotor = leftM;
        leftPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
        singleMotor = true; // !@#$%^&
        rightMotor = null;
        rightPidController = null;
    }

    public LiftSubsystem() {
        isHardwareConnected = false;
        leftMotor = null;
        rightMotor = null;
        leftPidController = null;
        rightPidController = null;
    }

    private void setTargetPosition(double lPos, double rPos) {
        if (!isHardwareConnected) {
            return; // this will be the only check so do not remove
        }
        if (singleMotor && leftPidController != null) {
            leftPidController.setTargetPosition(Range.clip(lPos, LEFT_ABSOLUTE_MIN_HEIGHT, LEFT_ABSOLUTE_MAX_HEIGHT));
        }
        else if (leftPidController != null && rightPidController != null) {
            leftPidController.setTargetPosition(Range.clip(lPos, LEFT_ABSOLUTE_MIN_HEIGHT, LEFT_ABSOLUTE_MAX_HEIGHT));
            rightPidController.setTargetPosition(Range.clip(rPos, RIGHT_ABSOLUTE_MIN_HEIGHT, RIGHT_ABSOLUTE_MAX_HEIGHT));
        }
    }

    public void setTop() {
        // null check will be done in setTargetPosition()
        this.setTargetPosition(LEFT_ABSOLUTE_MAX_HEIGHT, RIGHT_ABSOLUTE_MAX_HEIGHT);
    }

    public void setBottom() {
        // null check will be done in setTargetPosition()
        this.setTargetPosition(LEFT_ABSOLUTE_MIN_HEIGHT, RIGHT_ABSOLUTE_MIN_HEIGHT);
    }

    public void stop() {
        if (!isHardwareConnected) {
            return;
        }
        // By resetting the pidController, it stops the periodic function from making changes
        if (singleMotor && leftPidController != null) {
            leftPidController.reset();
        }
        else if (leftPidController != null && rightPidController != null) {
            leftPidController.reset();
            rightPidController.reset();
        }
    }

    public void halt() {
        if (!isHardwareConnected) {
            return;
        }
        // Just set the target position to the current position to get the motor to stop, yes?
        if (singleMotor && leftPidController != null) {
            leftPidController.setTargetPosition(leftMotor.get());
        }
        else if (leftPidController != null && rightPidController != null) {
            leftPidController.setTargetPosition(leftMotor.get());
            rightPidController.setTargetPosition(rightMotor.get());
        }
    }

    private void setTargetPositionWithLogging(double lVal, double rVal) {
        if (!isHardwareConnected) {
            return;
        }
        setTargetPosition(lVal, rVal);
        if (singleMotor && leftMotor != null){
            lpAndActual = String.format("%d (%d)", (int) lVal, leftMotor.get().intValue());
        }
        else if (leftMotor != null && rightMotor != null) {
            lpAndActual = String.format("%d (%d)", (int) lVal, leftMotor.get().intValue());
            rpAndActual = String.format("%d (%d)", (int) rVal, rightMotor.get().intValue());
        }
    }

    // This is run for every loop (Feature of the TechnoLib Subsystem!)
    // So you can just call "setTop" in a command and it will get there "as soon as it can"
    @Override
    public void periodic() {
        if (!isHardwareConnected) {
            return;
        }
        if (singleMotor && leftPidController != null) {
            double leftTargetSpeed = leftPidController.update(getLeftPos());
            double leftClippedSpeed = Range.clip(leftTargetSpeed, MIN_MOTOR_SPEED, MAX_MOTOR_SPEED);
            leftMotor.setSpeed(leftClippedSpeed);
        }
        else if (leftPidController != null && rightPidController != null) {
            double rTargetSpeed = rightPidController.update(getRightPos());
            double rClippedSpeed = Range.clip(rTargetSpeed, MIN_MOTOR_SPEED, MAX_MOTOR_SPEED);
            rightMotor.setSpeed(rClippedSpeed);
            // For logging purposes, I'm also doing this, to ensure that both values are updated
        }
        else {
            throw new RuntimeException("LiftSubsystem periodic() called with no motors");
        }
//        double rightTarget = 0;
//        if (!singleMotor) {
//            rightTarget = rightPidController.getTargetPosition();
//        }
//        setTargetPositionWithLogging(leftPidController.getTargetPosition(), rightTarget);
    }

    //    public double delta() {
    //        return leftPidController.getTargetPosition() - leftMotor.getDevice().getCurrentPosition();
    //        return rightPidController.getTargetPosition() - rightMotor.getDevice().getCurrentPosition();
    //    }

    //    public boolean isAtTarget() {
    //        return Math.abs(delta()) < DEAD_ZONE;
    //    }

    // TODO: enable as needed
    //    @Log(name = "lEncMotor Pos (Actual)")
    public volatile String lpAndActual = "(unknown)";

    // TODO: enable as needed
    //    @Log(name = "rEncMotor Pos (Actual)")
    public volatile String rpAndActual = "(unknown)";

    @Override
    public Double get() {
        if (!isHardwareConnected) {
            return 0.0;
        }
        // Not sure about this one
        return leftPidController.getTargetPosition();
        //        return rightPidController.getTargetPosition();
    }

    //    private boolean closeEnough(double currentPostion, double targetPostion) {
    //        double error = currentPostion - targetPostion;
    //        if (error <= DEAD_ZONE && error >= -DEAD_ZONE) {
    //            return true;
    //        } else {
    //            return false;
    //        }
    //    }

    public void highPole() {
        // null check will be done in setTargetPosition()
        setTargetPositionWithLogging(L_HIGH_JUNCTION, R_HIGH_JUNCTION);
    }

    public void midPole() {
        // null check will be done in setTargetPosition()
        setTargetPositionWithLogging(L_MEDIUM_JUNCTION, R_MEDIUM_JUNCTION);
    }

    public void lowPole() {
        // null check will be done in setTargetPosition()
        setTargetPositionWithLogging(L_LOW_JUNCTION, R_LOW_JUNCTION);
    }

    public void groundJunction() {
        // null check will be done in setTargetPosition()
        setTargetPositionWithLogging(L_GROUND_JUNCTION, R_GROUND_JUNCTION);
    }

    public void moveUp() {
        if (isHardwareConnected && leftPidController != null) {
            // maybe getCurrentPosition instead of getTargetPosition
            double rightTargetPosition = leftPidController.getTargetPosition();
            setTargetPositionWithLogging(rightTargetPosition + L_INCREMENTAL_MOVE, rightTargetPosition + R_INCREMENTAL_MOVE);
        }
    }

    public void moveDown() {
        if (isHardwareConnected && leftPidController != null) {
            // maybe getCurrentPosition instead of getTargetPosition
            double leftTargetPosition = leftPidController.getTargetPosition();
            setTargetPositionWithLogging(leftTargetPosition - L_INCREMENTAL_MOVE, leftTargetPosition - R_INCREMENTAL_MOVE);
        }
    }

    public double getLeftPos() {
        if (!isHardwareConnected || leftMotor == null) {
            return 0;
        }
        return leftMotor.getEncoder().getPosition(); // will cause NullPointerException when running in single motor modereturn 16750;
    }

    public double getRightPos() {
        if (!isHardwareConnected || singleMotor || rightMotor == null) {
            return 0;
        }
        // Invert the sign on this one to make it look like it's rotating the same way...
        return -rightMotor
                .getEncoder()
                .getPosition(); // will cause NullPointerException when running in single motor mode
    }
}