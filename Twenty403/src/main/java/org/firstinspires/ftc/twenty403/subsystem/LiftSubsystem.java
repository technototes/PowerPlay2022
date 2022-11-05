package org.firstinspires.ftc.twenty403.subsystem;

import java.util.function.Supplier;

import org.firstinspires.ftc.twenty403.Robot;

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
    public static double TICKS_INCH = 265;

    public static double LIntake_Position = 0;
    public static double RIntake_Position = 0;
    public static double LGROUND_JUNCTION = 265;
    public static double RGROUND_JUNCTION = 265;
    public static double LLOW_JUNCTION = 2980; // 2750 old value
    public static double RLOW_JUNCTION = 2980;
    public static double LMEDIUM_JUNCTION = 4770; // 4670
    public static double RMEDIUM_JUNCTION = 4770;
    public static double LHIGH_JUNCTION = 6560; // 6460
    public static double RHIGH_JUNCTION = 6560;
    public static double MAX_HEIGHT = 6600;
    public static double MIN_HEIGHT = 0;
    // TODO: THESE VALUES ARE PROBABLY WRONG! THEY NEED TO BE SET TO THE RIGHT VALUES!!!!
    public static double MAX_DISTANCE_FOR_FULLPOWER = 8 * TICKS_INCH;
    public static double DEAD_ZONE = .25 * TICKS_INCH;

    // Values work 11/4/22
    public static double MAX_MOTOR_SPEED = 1;
    public static double MIN_MOTOR_SPEED = -0.5; // Gravity

    public static double LMOVE = 1.00 * TICKS_INCH;
    public static double RMOVE = 1.00 * TICKS_INCH;
    public static double CONE_HEIGHT_DIFFERENCE = 1.2 *TICKS_INCH;
    // We may need to adjust this. Make *very* small changes! Values work 11/4/22
    public static PIDCoefficients PID = new PIDCoefficients(0.0008, 0, 0.00005);

    private EncodedMotor<DcMotorEx> _leftMotor;
    private EncodedMotor<DcMotorEx> _rightMotor;

    private PIDFController leftPidController;
    private PIDFController rightPidController;
    private int coneNumber = 5;

    // True if we only have *one* motor connected
    private boolean singleMotor;
    // True if we actually have hardware attached
    private boolean isHardware;

    // For the left side, positive is *down*
    // For the right side, positive is *up*
    public LiftSubsystem(EncodedMotor<DcMotorEx> lm, EncodedMotor<DcMotorEx> rm) {
        isHardware = true;
        singleMotor = false;

        _leftMotor = lm;
        _rightMotor = rm;

        PIDCoefficients pid = new PIDCoefficients(PID.kP, PID.kI, PID.kD);

        rightPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
        leftPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
    }

    // Before:
    // 2613, -2654
    // 1 inch lower
    // 2875, -2923
    // 262, 269
    public LiftSubsystem(EncodedMotor<DcMotorEx> oneMotor) {
        isHardware = true;
        singleMotor = true;

        _leftMotor = oneMotor;
        _rightMotor = null;

        leftPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
        rightPidController = null;
    }

    public LiftSubsystem() {
        isHardware = false;
        singleMotor = false;

        _leftMotor = null;
        _rightMotor = null;

        leftPidController = null;
        rightPidController = null;
    }

    //    public double delta() {
    //        return leftPidController.getTargetPosition() - leftMotor.getDevice().getCurrentPosition();
    //        return rightPidController.getTargetPosition() - rightMotor.getDevice().getCurrentPosition();
    //    }

    //    public boolean isAtTarget() {
    //        return Math.abs(delta()) < DEAD_ZONE;
    //    }

    private void setLiftPosition(double lval, double rval) {
        double lpos = Range.clip(lval, MIN_HEIGHT, MAX_HEIGHT);
        double rpos = Range.clip(rval, MIN_HEIGHT, MAX_HEIGHT);
        leftPidController.setTargetPosition(lpos);
        rightPidController.setTargetPosition(rpos);
        lpAndActual = String.format("%d (%d)", (int) lpos, (int) getLeftPos());
        rpAndActual = String.format("%d (%d)", (int) rpos, (int) getRightPos());
    }

    // This is run for every loop (Feature of the TechnoLib Subsystem!)
    // So you can just call "setTop" in a command and it will get there "as soon as it can"
    @Override
    public void periodic() {
        double ltargetSpeed = leftPidController.update(getLeftPos());
        double lclippedSpeed = Range.clip(ltargetSpeed, MIN_MOTOR_SPEED, MAX_MOTOR_SPEED);
        double rtargetSpeed = rightPidController.update(getRightPos());
        double rclippedSpeed = Range.clip(rtargetSpeed, MIN_MOTOR_SPEED, MAX_MOTOR_SPEED);

        //        double leftError = Math.abs(leftPidController.getTargetPosition() - getLeftPos());
        //        double rightError = Math.abs(rightPidController.getTargetPosition() - getRightPos());
        //        if (leftError > DEAD_ZONE || rightError > DEAD_ZONE) {
        //        }
        setMotorPower(lclippedSpeed, rclippedSpeed);
        setLiftPosition(leftPidController.getTargetPosition(), rightPidController.getTargetPosition());
    }

    //    public boolean isAtTarget() {
    //        return Math.abs(delta()) < DEAD_ZONE;
    //    }

    /* Stuff for Logging */

    @Log(name = "lEncMotor Pos (Actual)")
    public volatile String lpAndActual = "(unknown)";

    @Log(name = "rEncMotor Pos (Actual)")
    public volatile String rpAndActual = "(unknown)";

    @Log(name = "lMotor Power")
    public volatile String lMotorPower = "n/a";

    @Log(name = "rMotor Power")
    public volatile String rMotorPower = "n/a";

    @Override
    public Double get() {
        // Not sure about this one: it's just for displaying things, so this is probably fine:
        return leftPidController.getTargetPosition();
        // return rightPidController.getTargetPosition();
    }

    /* Subsystem command functions */

    public void stop() {
        // By resetting the pidController, it stops the periodic function from making changes
        leftPidController.reset();
        rightPidController.reset();
    }

    public void halt() {
        // Just set the target position to the current position to get the motor to stop, yes?
        leftPidController.setTargetPosition(getLeftPos());
        rightPidController.setTargetPosition(getRightPos());
    }

    public double changeCollectHeight(){
        coneNumber--;
        return coneNumber * CONE_HEIGHT_DIFFERENCE;
    }

    public void collect() {
        double collectHeight = changeCollectHeight();
        setLiftPosition(collectHeight, collectHeight);
    }

    public void highPole() {
        setLiftPosition(LHIGH_JUNCTION, RHIGH_JUNCTION);
    }

    public void midPole() {
        setLiftPosition(LMEDIUM_JUNCTION, RMEDIUM_JUNCTION);
    }

    public void lowPole() {
        setLiftPosition(LLOW_JUNCTION, RLOW_JUNCTION);
    }

    public void groundJunction() {
        setLiftPosition(LGROUND_JUNCTION, RGROUND_JUNCTION);
    }

    public void intakePos() {
        setLiftPosition(LIntake_Position, RIntake_Position);
    }

    public void moveUp() {
        // maybe getCurrentPosition instead of getTargetPosition
        double lposition = leftPidController.getTargetPosition();
        double rposition = rightPidController.getTargetPosition();
        setLiftPosition(lposition + LMOVE, rposition + RMOVE);
    }

    public void moveDown() {
        // maybe getCurrentPosition instead of getTargetPosition
        double lposition = leftPidController.getTargetPosition();
        double rposition = rightPidController.getTargetPosition();
        setLiftPosition(lposition - LMOVE, rposition - RMOVE);
    }

    /*
     * The following functions are the only ones that actually touch hardware.
     * Since the motors rotate opposite directions, we can deal with that in these
     * functions, and not have to worry about any other problems.
     * (See the motor power negating in setMotorPower and the encoder negating in getLeftPos)
     */

    private void setMotorPower(double lp, double rp) {
        if (isHardware && Robot.RobotConstant.LIFT_MOVE_MOTORS) {
            // Invert the speed here
            _leftMotor.setSpeed(-lp);
            if (!singleMotor) {
                _rightMotor.setSpeed(rp);
            }
        }
        lMotorPower = String.format("Power assigned: %f", lp);
        rMotorPower = String.format("Power assigned: %f", rp);
    }

    private double getLeftPos() {
        if (!isHardware) {
            return 0;
        }
        // Invert the sign on this one to make it look like it's rotating the same way...
        return -_leftMotor.get();
    }

    private double getRightPos() {
        if (!isHardware || singleMotor) {
            return 0;
        }
        return _rightMotor.get();
    }

}
