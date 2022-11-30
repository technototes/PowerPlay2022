package org.firstinspires.ftc.twenty403.subsystem;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.twenty403.helpers.ColorHelper;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.hardware.sensor.ColorDistanceSensor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class ClawSubsystem implements Subsystem, Loggable {
    // Correct numbers, tested 11/27/22
    public static double OPEN_SERVO_POSITION = .3045;
    public static double CLOSE_SERVO_POSITION = .42;

    // # of CM distance before we auto-close the claw
    public static double CONE_IS_CLOSE_ENOUGH = 6.0;

    // used if the claw subsystem isn't connectedn (mid-blue)
    public static int FAKE_RGB_VALUE = 0x008000;
    // This should trigger a close
    public static double FAKE_DISTANCE = 3.0;

    // This is both written to, and read from when using the subsystem without hardware
    @LogConfig.Run(duringInit = true, duringRun = true)
    @Log.Number
    public double CLAW_SERVO_POS = 0.35;

    private Servo _clawServo;
    private ColorDistanceSensor _sensor;
    private boolean isHardware;
    private Alliance alliance;
    private LiftSubsystem liftSubsystem;

    // This indicates that we've actually explicitly closed the claw at some point
    // which means the servo motor is engaged...
    private boolean servoSet;
    // Should we automatically close the claw if we detect a cone?
    private boolean autoClose;

    public ClawSubsystem(LiftSubsystem lift, Servo claw, ColorDistanceSensor s, Alliance a) {
        liftSubsystem = lift;
        _clawServo = claw;
        _sensor = s;
        alliance = a;
        isHardware = true;
        autoClose = true;
        servoSet = false;
    }

    // Non-functional subsystem constructor
    public ClawSubsystem() {
        liftSubsystem = null;
        _clawServo = null;
        _sensor = null;
        alliance = Alliance.NONE;
        isHardware = false;
        autoClose = false;
    }

    public void open() {
        setServo(OPEN_SERVO_POSITION);
    }

    public void close() {
        setServo(CLOSE_SERVO_POSITION);
    }

    public boolean isConeClose() {
        return readSensor() <= CONE_IS_CLOSE_ENOUGH;
    }

    public boolean isAllianceCone() {
        switch (alliance) {
            case NONE:
                // For no alliance, it's always an alliance cone :)
                return true;
            case RED:
                int rgb = readRgb();
                return ColorHelper.red(rgb) > ColorHelper.blue(rgb);
            case BLUE:
                int rgb2 = readRgb();
                return ColorHelper.blue(rgb2) > ColorHelper.red(rgb2);
            default:
                return false;
        }
    }

    public boolean isClawClosed() {
        double curPos = readServo();
        // This is going to say the claw is closed, just because we squeeze the jaws together
        // manually, so we need to check to see if the servo has had it's position explicitly set
        // instead of just checking the servo's position...
        return servoSet && Math.abs(curPos - CLOSE_SERVO_POSITION) < Math.abs(curPos - OPEN_SERVO_POSITION);
    }

    public void toggleAutoClose() {
        autoClose = !autoClose;
    }

    @Override
    public void periodic() {
        if (autoClose) {
            if (liftSubsystem.canAutoClose() && !isClawClosed() && isAllianceCone() && isConeClose()) {
                CLOSE_SERVO_POSITION = .55;
                close();
            }
        }
    }

    // All hardware access should be down here:
    private void setServo(double pos) {
        // Remember that we've actually set the servo's position
        servoSet = true;
        if (isHardware) {
            _clawServo.setPosition(pos);
        }
        CLAW_SERVO_POS = pos;
    }

    private double readServo() {
        if (isHardware) {
            double pos = _clawServo.getPosition();
            CLAW_SERVO_POS = pos;
            return pos;
        } else {
            return CLAW_SERVO_POS;
        }
    }

    private double readSensor() {
        if (isHardware) {
            return _sensor.getDistance(DistanceUnit.CM);
        } else {
            return FAKE_DISTANCE;
        }
    }

    private int readRgb() {
        if (isHardware) {
            return _sensor.rgb();
        } else {
            return FAKE_RGB_VALUE;
        }
    }
}
