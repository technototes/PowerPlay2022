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
    public static double LEFT_ABSOLUTE_MAX_HEIGHT = 1651;
    public static double RIGHT_ABSOLUTE_MAX_HEIGHT = 1651; // COPIED FROM LEFT
    public static double MIN_HEIGHT = 0;
    public static double LEFT_ABSOLUTE_MIN_HEIGHT = 15;
    public static double RIGHT_ABSOLUTE_MIN_HEIGHT = 15; // COPIED FROM LEFT
    public static double MAX_DISTANCE_FOR_FULLPOWER = 8 * TICKS_INCH; // WRONG
    public static double DEAD_ZONE = .25 * TICKS_INCH; // WRONG
    public static double MAX_MOTOR_SPEED = 0.8;
    public static double MIN_MOTOR_SPEED = -0.4; // Gravity
    public static double L_INCREMENTAL_MOVE = 10;
    public static double R_INCREMENTAL_MOVE = 10;
    public static PIDCoefficients PID = new PIDCoefficients(0.008, 0, 0.0005);

    private EncodedMotor<DcMotorEx> leftMotor;
    private PIDFController leftPidController;
    private final boolean isLeftConnected;

    private EncodedMotor<DcMotorEx> rightMotor;
    private PIDFController rightPidController;
    private final boolean isRightConnected;

    public LiftSubsystem(EncodedMotor<DcMotorEx> leftM, EncodedMotor<DcMotorEx> rightM) {
        if (leftM != null) {
            this.leftMotor = leftM;
            this.leftPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
            this.isLeftConnected = true;
        }
        else {
            this.isLeftConnected = false;
        }

        if (rightM != null) {
            this.rightMotor = rightM;
            this.rightPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
            this.isRightConnected = true;
        } else {
            this.isRightConnected = false;
        }
    }

    private void setTargetPosition(double lPos, double rPos) {
        if (isLeftConnected) {
            leftPidController.setTargetPosition(Range.clip(lPos, LEFT_ABSOLUTE_MIN_HEIGHT, LEFT_ABSOLUTE_MAX_HEIGHT));
        }
        if (isRightConnected) {
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

    private void setTargetPositionWithLogging(double lVal, double rVal) {
        setTargetPosition(lVal, rVal);
        if (isLeftConnected){
            lpAndActual = String.format("%d (%d)", (int) lVal, leftMotor.get().intValue());
        }
        if (isRightConnected) {
            rpAndActual = String.format("%d (%d)", (int) rVal, rightMotor.get().intValue());
        }
    }

    // This is run for every loop (Feature of the TechnoLib Subsystem!)
    // So you can just call "setTop" in a command and it will get there "as soon as it can"
    @Override
    public void periodic() {
        if (isLeftConnected) {
            double leftTargetSpeed = leftPidController.update(getLeftPos());
            double leftClippedSpeed = Range.clip(leftTargetSpeed, MIN_MOTOR_SPEED, MAX_MOTOR_SPEED);
            leftMotor.setSpeed(leftClippedSpeed);
        }
        if (isRightConnected) {
            double rTargetSpeed = rightPidController.update(getRightPos());
            double rClippedSpeed = Range.clip(rTargetSpeed, MIN_MOTOR_SPEED, MAX_MOTOR_SPEED);
            rightMotor.setSpeed(rClippedSpeed);
            // For logging purposes, I'm also doing this, to ensure that both values are updated
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
        // Not sure about this one
        if (isLeftConnected) {
            return leftPidController.getTargetPosition();
        }
        return 0.0;
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
        if (isLeftConnected) {
            // maybe getCurrentPosition instead of getTargetPosition
            double rightTargetPosition = leftPidController.getTargetPosition();
            setTargetPositionWithLogging(rightTargetPosition + L_INCREMENTAL_MOVE, rightTargetPosition + R_INCREMENTAL_MOVE);
        }
    }

    public void moveDown() {
        if (isRightConnected) {
            // maybe getCurrentPosition instead of getTargetPosition
            double leftTargetPosition = leftPidController.getTargetPosition();
            setTargetPositionWithLogging(leftTargetPosition - L_INCREMENTAL_MOVE, leftTargetPosition - R_INCREMENTAL_MOVE);
        }
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
            return -rightMotor
                    .getEncoder()
                    .getPosition();
        }
        return 0.0;
    }
}