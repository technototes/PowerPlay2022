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
    public static double TICKS_INCH = 750;
    public static double LGROUND_JUNCTION = 0.5 * TICKS_INCH;
    public static double RGROUND_JUNCTION = 0.5 * TICKS_INCH;
    public static double LLOW_JUNCTION = 12 * TICKS_INCH;
    public static double RLOW_JUNCTION = 12 * TICKS_INCH;
    public static double LMEDIUM_JUNCTION = 24 * TICKS_INCH;
    public static double RMEDIUM_JUNCTION = 24 * TICKS_INCH;
    public static double LHIGH_JUNCTION = 36 * TICKS_INCH;
    public static double RHIGH_JUNCTION = 36 * TICKS_INCH;
    public static double MAX_HEIGHT = 38 * TICKS_INCH;
    public static double ABSOLUTE_MAX_HEIGHT;
    public static double MIN_HEIGHT = 0;
    public static double ABSOLUTE_MIN_HEIGHT;
    public static double MAX_DISTANCE_FOR_FULLPOWER = 8 * TICKS_INCH;
    public static double DEAD_ZONE = .25 * TICKS_INCH;
    public static double MAX_MOTOR_SPEED = 0.8;
    public static double MIN_MOTOR_SPEED = -0.4; // Gravity
    public static double LMOVE = 2.25 * TICKS_INCH;
    public static double RMOVE = 2.25 * TICKS_INCH;
    public static PIDCoefficients PID = new PIDCoefficients(0.008, 0, 0.0005);

    private EncodedMotor<DcMotorEx> leftMotor;
    private PIDFController leftPidController;

    private boolean singleMotor;
    private boolean isHardware;
    private EncodedMotor<DcMotorEx> rightMotor;
    private PIDFController rightPidController;

    public LiftSubsystem(EncodedMotor<DcMotorEx> leftM, EncodedMotor<DcMotorEx> rightM) {
        this.leftMotor = leftM;
        this.leftPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);

        this.rightMotor = rightM;
        this.rightPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
        this.singleMotor = false;
        this.isHardware = true;
    }

    // TODO: figure out which motor to use in single motor mode
    public LiftSubsystem(EncodedMotor<DcMotorEx> leftM) {
        leftMotor = leftM;
        leftPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
        singleMotor = false;
        rightMotor = null;
        rightPidController = null;
        isHardware = true;
    }

    public LiftSubsystem() {
        isHardware = false;
        leftMotor = null;
        rightMotor = null;
        leftPidController = null;
        rightPidController = null;
    }

    private void setPosition(double lPos, double rPos) {
        if (!isHardware) {
            return;
        }
        leftPidController.setTargetPosition(Range.clip(lPos, MIN_HEIGHT, MAX_HEIGHT));
        if (singleMotor == false) {
            rightPidController.setTargetPosition(Range.clip(rPos, MIN_HEIGHT, MAX_HEIGHT));
        }
    }

    public void setTop() {
        if (!isHardware) {
            return;
        }
        leftPidController.setTargetPosition(MAX_HEIGHT);
        if (singleMotor == false) {
            rightPidController.setTargetPosition(MAX_HEIGHT);
        }
    }

    public void setBottom() {
        if (!isHardware) {
            return;
        }
        leftPidController.setTargetPosition(MIN_HEIGHT);
        if (singleMotor == false) {
            rightPidController.setTargetPosition(MIN_HEIGHT);
        }
    }

    public void stop() {
        if (!isHardware) {
            return;
        }
        // By resetting the pidController, it stops the periodic function from making changes
        leftPidController.reset();
        if (singleMotor == false) {
            rightPidController.reset();
        }
    }

    public void halt() {
        if (!isHardware) {
            return;
        }
        // Just set the target position to the current position to get the motor to stop, yes?
        leftPidController.setTargetPosition(leftMotor.get());
        if (!singleMotor) {
            rightPidController.setTargetPosition(rightMotor.get());
        }
    }

    private void setLiftPosition(double lVal, double rVal) {
        if (!isHardware) {
            return;
        }
        setPosition(lVal, rVal);
        lpAndActual = String.format("%d (%d)", (int) lVal, leftMotor.get().intValue());
        if (singleMotor == false) {
            rpAndActual = String.format("%d (%d)", (int) rVal, rightMotor.get().intValue());
        }
    }

    // This is run for every loop (Feature of the TechnoLib Subsystem!)
    // So you can just call "setTop" in a command and it will get there "as soon as it can"
    @Override
    public void periodic() {
        if (!isHardware) {
            return;
        }
        double lTargetSpeed = leftPidController.update(leftMotor.get());
        double lClippedSpeed = Range.clip(lTargetSpeed, MIN_MOTOR_SPEED, MAX_MOTOR_SPEED);
        leftMotor.setSpeed(lClippedSpeed);
        if (!singleMotor) {
            double rTargetSpeed = rightPidController.update(rightMotor.get());
            double rClippedSpeed = Range.clip(rTargetSpeed, MIN_MOTOR_SPEED, MAX_MOTOR_SPEED);
            rightMotor.setSpeed(rClippedSpeed);
            // For logging purposes, I'm also doing this, to ensure that both values are updated
        }
        double rightTarget = 0;
        if (!singleMotor) {
            rightTarget = rightPidController.getTargetPosition();
        }
        setLiftPosition(leftPidController.getTargetPosition(), rightTarget);
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
        if (!isHardware) {
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
        if (!isHardware) {
            return;
        }
        setLiftPosition(LHIGH_JUNCTION, RHIGH_JUNCTION);
    }

    public void midPole() {
        if (!isHardware) {
            return;
        }
        setLiftPosition(LMEDIUM_JUNCTION, RMEDIUM_JUNCTION);
    }

    public void lowPole() {
        if (!isHardware) {
            return;
        }
        setLiftPosition(LLOW_JUNCTION, RLOW_JUNCTION);
    }

    public void groundJunction() {
        if (!isHardware) {
            return;
        }
        setLiftPosition(LGROUND_JUNCTION, RGROUND_JUNCTION);
    }

    public void moveUp() {
        if (!isHardware) {
            return;
        }
        // maybe getCurrentPosition instead of getTargetPosition
        double position = leftPidController.getTargetPosition();
        setLiftPosition(position + LMOVE, position + RMOVE);
    }

    public void moveDown() {
        if (!isHardware) {
            return;
        }
        // maybe getCurrentPosition instead of getTargetPosition
        double position = leftPidController.getTargetPosition();
        setLiftPosition(position - LMOVE, position - RMOVE);
    }

    public double getLeftPos() {
        if (!isHardware) {
            return 0;
        }
        if (leftMotor.getEncoder() != null) {
            // Invert the sign on this one to make it look like it's rotating the same way...
            return -leftMotor.getEncoder().getPosition(); // will cause NullPointerException when running in single motor mode
        //  return -leftMotor.get();
        } else {
            return 16750;
        }
    }

    public double getRightPos() {
        if (!isHardware || singleMotor) {
            return 0;
        }
        if (rightMotor.getEncoder() != null) {
            return rightMotor
                    .getEncoder()
                    .getPosition(); // will cause NullPointerException when running in single motor mode
        //  return rightMotor.get();
        } else {
            return 16750;
        }

    }
}
