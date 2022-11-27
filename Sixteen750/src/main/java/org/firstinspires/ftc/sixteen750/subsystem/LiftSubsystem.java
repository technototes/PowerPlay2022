package org.firstinspires.ftc.sixteen750.subsystem;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

import org.firstinspires.ftc.sixteen750.Robot;

import java.util.function.Supplier;

public class LiftSubsystem implements Subsystem, Supplier<Double>, Loggable {
    public static double TICKS_INCH = 38;

    public static double INTAKE_POSITION_LIFT = 0;
    public static double GROUND_JUNCTION_LIFT = 1.75 * TICKS_INCH;
    public static double LOW_JUNCTION_LIFT = 14.5 * TICKS_INCH;
    public static double MEDIUM_JUNCTION_LIFT = 25 * TICKS_INCH;
    public static double HIGH_JUNCTION_LIFT = 36 * TICKS_INCH;
    public static double MAX_HEIGHT = 38 * TICKS_INCH;
    public static double MIN_HEIGHT = 0;

    // Don't change these: They're used for user-redefining the 'zero' location during gameplay
    public static double ACTUAL_ZERO_LIFT = 0;

    // TODO: THESE VALUES ARE PROBABLY WRONG! THEY NEED TO BE SET TO THE RIGHT VALUES!!!!
    public static double MAX_DISTANCE_FOR_FULLPOWER = 8 * TICKS_INCH;
    public static double DEAD_ZONE = .9 * TICKS_INCH;

    public static double MAX_MOTOR_SPEED = 1;
    public static double MIN_MOTOR_SPEED = -1;

    // This is used to hopefully counteract gravity...
    public static double DOWNWARD_SCALE_FACTOR = 0.65;

    public static double MOVE_LIFT = 1.50 * TICKS_INCH;
    public static double CONE_HEIGHT_DIFFERENCE = .9 * TICKS_INCH;

    public static PIDCoefficients PID =
            new PIDCoefficients(0.0048, 0.0, 0);

    private EncodedMotor<DcMotorEx> _liftMotor;

    private PIDFController liftPidController;
    private int coneNumber = 5;

    // True if we actually have hardware attached
    private boolean isHardware;

    private double voltage;

    private void init(EncodedMotor<DcMotorEx> lm, double volts) {
        voltage = volts;
        _liftMotor = lm;
        PIDCoefficients pid = new PIDCoefficients(PID.kP, PID.kI, PID.kD);
        liftPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
        setNewZero();
    }

    public LiftSubsystem(EncodedMotor<DcMotorEx> oneMotor, double volts) {
        isHardware = true;
        init(oneMotor, volts);
    }

    public LiftSubsystem(EncodedMotor<DcMotorEx> oneMotor) {
        this(oneMotor, 0);
    }

    public LiftSubsystem() {
        isHardware = false;
        init(null, 0);
    }

    private void setLiftPosition(double lval) {
        double lpos = Range.clip(lval, MIN_HEIGHT, MAX_HEIGHT);
        setLiftPosition_OVERRIDE(lpos);
    }

    private void setLiftPosition_OVERRIDE(double lpos) {
        liftPidController.setTargetPosition(lpos);
        lpAndActual = String.format("%d (%d)", (int) lpos, (int) getPos());
    }

    // This is run for every loop (Feature of the TechnoLib Subsystem!)
    // So you can just call "setTop" in a command and it will get there "as soon as it can"
    @Override
    public void periodic() {
        double ltargetSpeed = liftPidController.update(getPos());
        double lclippedSpeed = Range.clip(ltargetSpeed, MIN_MOTOR_SPEED, MAX_MOTOR_SPEED);

        // double liftError = Math.abs(liftPidController.getTargetPosition() - getPosition());
        // if (liftError > DEAD_ZONE) {
        //     return; // Good idea?
        // }
        setMotorPower(lclippedSpeed);
        setLiftPosition_OVERRIDE(liftPidController.getTargetPosition());
    }

    /* Stuff for Logging */

    @Log(name = "lift EncMotor Pos (Actual)")
    public volatile String lpAndActual = "(unknown)";

    @Log(name = "lift Motor Power")
    public volatile String lMotorPower = "n/a";

    @Override
    public Double get() {
        // Not sure about this one: it's just for displaying things, so this is probably fine:
        return getPos();
    }

    public boolean canAutoClose() {
        return Math.abs(getPos() - INTAKE_POSITION_LIFT) < DEAD_ZONE;
    }

    /* Subsystem command functions */

    public void stop() {
        // By resetting the pidController, it stops the periodic function from making changes
        liftPidController.reset();
    }

    public void halt() {
        // Just set the target position to the current position to get the motor to stop, yes?
        liftPidController.setTargetPosition(getPos());
    }

    public double changeCollectHeight() {
        coneNumber--;
        return coneNumber * CONE_HEIGHT_DIFFERENCE;
    }

    public void collect() {
        double collectHeight = changeCollectHeight();
        setLiftPosition(collectHeight);
    }

    public void highPole() {
        setLiftPosition(HIGH_JUNCTION_LIFT);
    }

    public void midPole() {
        setLiftPosition(MEDIUM_JUNCTION_LIFT);
    }

    public void lowPole() {
        setLiftPosition(LOW_JUNCTION_LIFT);
    }

    public void groundJunction() {
        setLiftPosition(GROUND_JUNCTION_LIFT);
    }

    public void intakePos() {
        setLiftPosition(INTAKE_POSITION_LIFT);
    }

    public void moveUp() {
        // maybe getCurrentPosition instead of getTargetPosition
        double lposition = liftPidController.getTargetPosition();

        setLiftPosition(lposition + MOVE_LIFT);
    }

    public void moveDown() {
        // maybe getCurrentPosition instead of getTargetPosition
        double lposition = liftPidController.getTargetPosition();

        setLiftPosition(lposition - MOVE_LIFT);
    }

    public void moveUp_OVERRIDE() {
        // maybe getCurrentPosition instead of getTargetPosition
        double lposition = liftPidController.getTargetPosition();
        setLiftPosition_OVERRIDE(lposition + MOVE_LIFT);
    }

    public void moveDown_OVERRIDE() {
        // maybe getCurrentPosition instead of getTargetPosition
        double lposition = liftPidController.getTargetPosition();
        setLiftPosition_OVERRIDE(lposition - MOVE_LIFT);
    }

    /*
     * The following functions are the only ones that actually touch hardware.
     * Since the motors rotate opposite directions, we can deal with that in these
     * functions, and not have to worry about any other problems.
     * (See the motor power negating in setMotorPower and the encoder negating in getLeftPos)
     */

    private void setMotorPower(double lp) {
        // Add scaling if we're driving *downward*
        if (lp < 0) {
            lp = lp * DOWNWARD_SCALE_FACTOR;
        }
        if (isHardware && Robot.RobotConstant.LIFT_MOVE_MOTORS) {
            // Invert the speed here
            _liftMotor.setSpeed(lp);
        }
        lMotorPower = String.format("Power assigned: %f", lp);
    }

    public void setNewZero() {
        if (!isHardware) {
            return;
        }
        double curLeft = liftPidController.getTargetPosition();
        ACTUAL_ZERO_LIFT = _liftMotor.get();
        liftPidController.setTargetPosition(curLeft + ACTUAL_ZERO_LIFT);
    }

    private double getPos() {
        if (!isHardware) {
            return 0;
        }
        // Invert the sign on this one to make it look like it's rotating the same way...
        return (_liftMotor.get() - ACTUAL_ZERO_LIFT);
    }
}


