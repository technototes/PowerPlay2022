package org.firstinspires.ftc.twenty403.subsystem;

import android.util.Log;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import com.acmerobotics.dashboard.config.Config;

import com.technototes.library.hardware.sensor.ColorDistanceSensor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.util.Alliance;

@Config
public class ClawSubsystem implements Subsystem {
    // Correct numbers, tested
    public static double OPEN_SERVO_POSITION = .329;
    public static double CLOSE_SERVO_POSITION = .42;

    // # of CM distance before we auto-close the claw
    public static double CONE_IS_CLOSE_ENOUGH = 6.0;

    private Servo clawServo;
    private ColorDistanceSensor sensor;
    private Boolean isHardware;
    private Alliance alliance;
    private LiftSubsystem liftSubsystem;
    private boolean autoClose;

    public ClawSubsystem(LiftSubsystem lift, Servo claw, ColorDistanceSensor s, Alliance a) {
        liftSubsystem = lift;
        clawServo = claw;
        sensor = s;
        alliance = a;
        isHardware = true;
        autoClose = true;
    }

    // Non-functional subsystem constructor
    public ClawSubsystem() {
        liftSubsystem = null;
        clawServo = null;
        sensor = null;
        alliance = Alliance.NONE;
        isHardware = false;
        autoClose = false;
    }

    private static void log(String s) {
        Log.d("Claw", s);
    }

    public void open() {
        log("Open");
        if (isHardware) {
            clawServo.setPosition(OPEN_SERVO_POSITION);
        }
    }

    public void close() {
        log("Close");
        if (isHardware) {
            clawServo.setPosition(CLOSE_SERVO_POSITION);
        }
    }

    public boolean isConeClose() {
        log("isConeClose");
        if (isHardware && sensor.getDistance(DistanceUnit.CM) <= CONE_IS_CLOSE_ENOUGH) {
            return true;
        }
        return false;
    }

    public boolean isAllianceCone() {
        return (sensor.red() > sensor.blue()) == (alliance == Alliance.RED);
    }

    public boolean isClawClosed() {
        double curPos = clawServo.getPosition();
        return Math.abs(curPos - CLOSE_SERVO_POSITION) < Math.abs(curPos - OPEN_SERVO_POSITION);
    }

    public void toggleAutoClose() {
        autoClose = !autoClose;
    }

    public Servo getServo() {
        return clawServo;
    }

    @Override
    public void periodic() {
        if (isHardware && autoClose) {
            if (liftSubsystem.canAutoClose() && !isClawClosed() && isAllianceCone() && isConeClose()) {
                close();
            }
        }
    }
}
