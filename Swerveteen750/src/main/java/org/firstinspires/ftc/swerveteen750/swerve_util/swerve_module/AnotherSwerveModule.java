package org.firstinspires.ftc.swerveteen750.swerve_util.swerve_module;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.util.Angle;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.SwerveDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.swerve_util.AbsoluteAnalogEncoder;
import org.firstinspires.ftc.swerveteen750.swerve_util.CRServoProfiler;
import org.firstinspires.ftc.swerveteen750.swerve_util.Encoder;


public class AnotherSwerveModule {
//    public static PIDCoefficients ROTATION_PID = new PIDCoefficients(0.6, 0, 0.03);

    public static double K_STATIC = 0.0, K_MOTOR = 0;

    public static CRServoProfiler.Constraints SERVO_CONSTRAINTS = new CRServoProfiler.Constraints(1, 1000, 2);

    public static double MAX_SERVO_SPEED = 1, MAX_MOTOR_SPEED = 1;

    private double kStatic;

    //EXPERIMENTAL FEATURES
    public static boolean WAIT_FOR_TARGET = false;

    public static double ALLOWED_COS_ERROR = Math.toRadians(2);

    public static double ALLOWED_BB_ERROR = .02;

    public static boolean MOTOR_FLIPPING = true;

    public static double FLIP_BIAS = Math.toRadians(0);

    public double motorEncoderZero = 0;


    private DcMotorEx motor;
    private CRServo servo;
    private AbsoluteAnalogEncoder encoder;
    public PIDFController rotationController;
    private CRServoProfiler rotationProfiler;

    private boolean wheelFlipped = false;
    private double currentTargetPosition = 0;
    private double lastRotationError = 0;
    private double lastServoPower = 0;

    public AnotherSwerveModule(DcMotorEx m, CRServo s, AbsoluteAnalogEncoder e, PIDCoefficients rotationPID, PIDFCoefficients motorVelocityPID, double rotationStatic) {
        motor = m;
        MotorConfigurationType motorConfigurationType = motor.getMotorType().clone();
        motorConfigurationType.setAchieveableMaxRPMFraction(MAX_MOTOR_SPEED);
        motor.setMotorType(motorConfigurationType);

        servo = s;
        ((CRServoImplEx) servo).setPwmRange(new PwmControl.PwmRange(1000, 2000));
        servo.setDirection(DcMotorSimple.Direction.REVERSE);

        encoder = e;
        e.setInverted(Encoder.Direction.REVERSE);
        rotationController = new PIDFController(rotationPID, 0, 0, K_STATIC, (p, v) -> lastMotorPower * K_MOTOR);
        rotationProfiler = new CRServoProfiler(servo, SERVO_CONSTRAINTS);
//        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        System.out.println("Rotation PID: " + rotationPID);


        if (motorVelocityPID != null) {
            System.out.println("Motor PID: " + motorVelocityPID);
            motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, motorVelocityPID);
        } else {
            System.out.println("Current/Default Motor PID: " + motor.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER));
        }
        kStatic = rotationStatic;
    }


    public AnotherSwerveModule(HardwareMap hardwareMap, String motorName, String servoName, String encoderName, PIDCoefficients rotationPID, PIDFCoefficients motorVelocityPID, double rotationalStatic) {
        this(
                hardwareMap.get(DcMotorEx.class, motorName),
                hardwareMap.get(CRServo.class, servoName),
                new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, encoderName)),
                rotationPID,
                motorVelocityPID,
                rotationalStatic
        );
    }


    public void update() {
        double current = getModuleRotation();
        double error = AngleUnit.normalizeRadians(current - currentTargetPosition);
        lastRotationError = error;
        double power = Range.clip(rotationController.update(error), -MAX_SERVO_SPEED, MAX_SERVO_SPEED);
        if (Double.isNaN(power)) power = 0;
        power = power + (Math.abs(power) > ALLOWED_BB_ERROR ? (kStatic * Math.signum(power)): 0);
        servo.setPower(power);
        lastServoPower = power;
        //System.out.println("Target: " + Math.toDegrees(target) + " Current: " + Math.toDegrees(current) + " Power: " + power + ", " + rotationController.getTargetPosition() +", " + rotationController.getTargetVelocity() + ", " + rotationController.getTargetAcceleration());
    }

    public double getTargetRotation() {
        return currentTargetPosition;
    }

    public double getModuleRotation() {
        return encoder.getCurrentPosition();
    }

    public double getLastRotationError() {
        return lastRotationError;
    }

    public double getLastServoPower() {
        return lastServoPower;

    }
    public double getUnadjustedWheelInchPosition() {
        return SwerveDriveSubsystem.SwerveDriveConstant.encoderTicksToInches(motor.getCurrentPosition());
    }

    public double getAdjustedWheelInchPosition() {
        return SwerveDriveSubsystem.SwerveDriveConstant.encoderTicksToInches(motor.getCurrentPosition() - motorEncoderZero);
    }

    public void setMotorEncoderZero() {
        this.motorEncoderZero = motor.getCurrentPosition();
    }

    public int flipModifier() {
        return MOTOR_FLIPPING && wheelFlipped ? 1 : -1;
    }

    public double getWheelVelocity() {
        return SwerveDriveSubsystem.SwerveDriveConstant.encoderTicksToInches(motor.getVelocity());
    }

    public void setMode(DcMotor.RunMode runMode) {
        motor.setMode(runMode);
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        motor.setZeroPowerBehavior(zeroPowerBehavior);
    }

    public void setPIDFCoefficients(DcMotor.RunMode runMode, PIDFCoefficients coefficients) {
        motor.setPIDFCoefficients(runMode, coefficients);
    }

    public

    double lastMotorPower = 0;

    public void setMotorPower(double power) {
        //target check
        if (WAIT_FOR_TARGET && !isWithinAllowedError()) {
            power *= Math.cos(Range.clip(rotationController.getLastError(), -Math.PI / 2, Math.PI / 2));
        }
        lastMotorPower = power;
        //flip check
        if (MOTOR_FLIPPING) power *= flipModifier();

        motor.setPower(power);
    }

    /// this can be direct substitution for setMotorPower as it will do the conversion
    public void setMotorVelocity(double velocity) {
        lastMotorPower = velocity;

        velocity *= 2300.0;
        if (MOTOR_FLIPPING) velocity *= flipModifier();

        motor.setVelocity(velocity);
    }

    public boolean isWithinAllowedError() {
        double error = Math.abs(rotationController.getLastError());
        return error < ALLOWED_COS_ERROR || error > 2 * Math.PI - ALLOWED_COS_ERROR;
    }

    public void setServoPower(double power) {
        servo.setPower(power);
    }

    public static double MIN_MOTOR_TO_TURN = 0.05;
    public boolean enableMotor = true;

    public void setTargetRotation(double target) {
//        if (enableMotor && Math.abs(lastMotorPower) < MIN_MOTOR_TO_TURN) {
//            //add stuff like X-ing preAlign
//            return;
//        }
        double current = getModuleRotation();
        //normalize for wraparound
        if (current - target > Math.PI) current -= (2 * Math.PI);
        else if (target - current > Math.PI) current += (2 * Math.PI);

        if (MOTOR_FLIPPING) {
            //flip target
            wheelFlipped = Math.abs(current - target) > (Math.PI / 2 - flipModifier() * FLIP_BIAS);
            if (wheelFlipped) target = Angle.norm(target + Math.PI);
        }
        currentTargetPosition = target;
    }

    public SwerveModuleState asState() {
        return new SwerveModuleState(this);
    }

    public double getServoPower() {
        return servo.getPower();
    }

    public static class SwerveModuleState {
        public AnotherSwerveModule module;
        public double wheelPos, podRot;

        public SwerveModuleState(AnotherSwerveModule s) {
            module = s;
            wheelPos = 0;
            podRot = 0;
        }

        public SwerveModuleState update() {
            return setState(-module.getUnadjustedWheelInchPosition(), module.getModuleRotation());
        }

        public SwerveModuleState setState(double wheel, double pod) {
            wheelPos = wheel;
            podRot = pod;
            return this;
        }

        //TODO add averaging for podrots based off of past values
        public Vector2d calculateDelta() {
            double oldWheel = wheelPos;
            update();
            return Vector2d.polar(wheelPos - oldWheel, podRot);
        }
    }

    public double getEncoderVoltage() {
        return encoder.getVoltage();
    }
}
