package org.firstinspires.ftc.twenty403.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.command.SimpleRequiredCommand;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import java.util.function.Supplier;
import org.firstinspires.ftc.twenty403.Robot;

@Config
public class LiftSubsystem implements Subsystem, Supplier<Double>, Loggable {

    public static double TICKS_INCH = 89;

    public static double INTAKE_POSITION_LEFT = 0;
    public static double INTAKE_POSITION_RIGHT = 0;
    public static double GROUND_JUNCTION_LEFT = 155;
    public static double GROUND_JUNCTION_RIGHT = 155;
    public static double LOW_JUNCTION_LEFT = 1432;
    public static double LOW_JUNCTION_RIGHT = 1432;
    public static double MEDIUM_JUNCTION_LEFT = 2230;
    public static double MEDIUM_JUNCTION_RIGHT = 2230;
    public static double HIGH_JUNCTION_LEFT = 3200;
    public static double HIGH_JUNCTION_RIGHT = 3200;
    public static double MAX_HEIGHT = 3400;
    public static double MIN_HEIGHT = 0;

    // Don't change these: They're used for user-redefining the 'zero' location during gameplay
    public static double ACTUAL_ZERO_LEFT = 0;
    public static double ACTUAL_ZERO_RIGHT = 0;

    // TODO: THESE VALUES ARE PROBABLY WRONG! THEY NEED TO BE SET TO THE RIGHT VALUES!!!!
    public static double MAX_DISTANCE_FOR_FULLPOWER = 8 * TICKS_INCH;
    public static double DEAD_ZONE = .9 * TICKS_INCH;

    public static double MAX_MOTOR_SPEED = 1;
    public static double MIN_MOTOR_SPEED = -1;

    // This is used to hopefully counteract gravity...
    public static double DOWNWARD_SCALE_FACTOR = 0.57;

    public static double MOVE_LEFT = 1.50 * TICKS_INCH;
    public static double MOVE_RIGHT = 1.50 * TICKS_INCH;
    public static double CONE_HEIGHT_DIFFERENCE = .9 * TICKS_INCH;

    public static PIDCoefficients PID = new PIDCoefficients(0.0027, 0.0, 0.00015);

    private EncodedMotor<DcMotorEx> _leftMotor;
    private EncodedMotor<DcMotorEx> _rightMotor;

    private PIDFController leftPidController;
    private PIDFController rightPidController;
    private int coneNumber = 5;

    // True if we only have *one* motor connected
    private boolean singleMotor;
    // True if we actually have hardware attached
    private boolean isHardware;

    private double voltage;

    private void init(EncodedMotor<DcMotorEx> lm, EncodedMotor<DcMotorEx> rm, double volts) {
        voltage = volts;
        _leftMotor = lm;
        _rightMotor = rm;
        PIDCoefficients pid = new PIDCoefficients(PID.kP, PID.kI, PID.kD);
        rightPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
        leftPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
        setNewZero();
    }

    // For the left side, positive is *down*
    // For the right side, positive is *up*
    public LiftSubsystem(EncodedMotor<DcMotorEx> lm, EncodedMotor<DcMotorEx> rm, double volts) {
        isHardware = true;
        singleMotor = false;
        init(lm, rm, volts);
    }

    public LiftSubsystem(EncodedMotor<DcMotorEx> lm, EncodedMotor<DcMotorEx> rm) {
        this(lm, rm, 0);
    }

    public LiftSubsystem(EncodedMotor<DcMotorEx> oneMotor, double volts) {
        isHardware = true;
        singleMotor = true;
        init(oneMotor, null, volts);
    }

    public LiftSubsystem(EncodedMotor<DcMotorEx> oneMotor) {
        this(oneMotor, 0);
    }

    public LiftSubsystem() {
        isHardware = false;
        singleMotor = false;
        init(null, null, 0);
    }

    private void setLiftPosition(double lval, double rval) {
        double lpos = Range.clip(lval, MIN_HEIGHT, MAX_HEIGHT);
        double rpos = Range.clip(rval, MIN_HEIGHT, MAX_HEIGHT);
        setLiftPosition_OVERRIDE(lpos, rpos);
    }

    private void setLiftPosition_OVERRIDE(double lpos, double rpos) {
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
        setLiftPosition_OVERRIDE(
            leftPidController.getTargetPosition(),
            rightPidController.getTargetPosition()
        );
    }

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
        return getLeftPos();
    }

    public boolean canAutoClose() {
        return Math.abs(getLeftPos() - INTAKE_POSITION_LEFT) < DEAD_ZONE;
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

    public double changeCollectHeight() {
        coneNumber--;
        return coneNumber * CONE_HEIGHT_DIFFERENCE;
    }

    public void collect() {
        double collectHeight = changeCollectHeight();
        setLiftPosition(collectHeight, collectHeight);
    }

    public void highPole() {
        setLiftPosition(HIGH_JUNCTION_LEFT, HIGH_JUNCTION_RIGHT);
    }

    public void midPole() {
        setLiftPosition(MEDIUM_JUNCTION_LEFT, MEDIUM_JUNCTION_RIGHT);
    }

    public void lowPole() {
        setLiftPosition(LOW_JUNCTION_LEFT, LOW_JUNCTION_RIGHT);
    }

    public void groundJunction() {
        setLiftPosition(GROUND_JUNCTION_LEFT, GROUND_JUNCTION_RIGHT);
    }

    public void intakePos() {
        setLiftPosition(INTAKE_POSITION_LEFT, INTAKE_POSITION_RIGHT);
    }

    public void moveUp() {
        // maybe getCurrentPosition instead of getTargetPosition
        double lposition = leftPidController.getTargetPosition();
        double rposition = rightPidController.getTargetPosition();
        setLiftPosition(lposition + MOVE_LEFT, rposition + MOVE_RIGHT);
    }

    public void moveDown() {
        // maybe getCurrentPosition instead of getTargetPosition
        double lposition = leftPidController.getTargetPosition();
        double rposition = rightPidController.getTargetPosition();
        setLiftPosition(lposition - MOVE_LEFT, rposition - MOVE_RIGHT);
    }

    public void moveUp_OVERRIDE() {
        // maybe getCurrentPosition instead of getTargetPosition
        double lposition = leftPidController.getTargetPosition();
        double rposition = rightPidController.getTargetPosition();
        setLiftPosition_OVERRIDE(lposition + MOVE_LEFT, rposition + MOVE_RIGHT);
    }

    public void moveDown_OVERRIDE() {
        // maybe getCurrentPosition instead of getTargetPosition
        double lposition = leftPidController.getTargetPosition();
        double rposition = rightPidController.getTargetPosition();
        setLiftPosition_OVERRIDE(lposition - MOVE_LEFT, rposition - MOVE_RIGHT);
    }

    /*
     * The following functions are the only ones that actually touch hardware.
     * Since the motors rotate opposite directions, we can deal with that in these
     * functions, and not have to worry about any other problems.
     * (See the motor power negating in setMotorPower and the encoder negating in getLeftPos)
     */

    private void setMotorPower(double lp, double rp) {
        // Add scaling if we're driving *downward*
        if (lp < 0) {
            lp = lp * DOWNWARD_SCALE_FACTOR;
        }
        if (rp < 0) {
            rp = rp * DOWNWARD_SCALE_FACTOR;
        }
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

    public void setNewZero() {
        if (!isHardware) {
            return;
        }
        ACTUAL_ZERO_LEFT = _leftMotor.get();
        leftPidController.setTargetPosition(0);
        leftPidController.reset();
        if (singleMotor) {
            return;
        }
        ACTUAL_ZERO_RIGHT = _rightMotor.get();
        rightPidController.setTargetPosition(0);
        rightPidController.reset();
    }

    private double getLeftPos() {
        if (!isHardware) {
            return 0;
        }
        // Invert the sign on this one to make it look like it's rotating the same way...
        return -(_leftMotor.get() - ACTUAL_ZERO_LEFT);
    }

    private double getRightPos() {
        if (!isHardware || singleMotor) {
            return 0;
        }
        return _rightMotor.get() - ACTUAL_ZERO_RIGHT;
    }
}
