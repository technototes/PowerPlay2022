package org.firstinspires.ftc.twenty403.subsystem;

import android.util.Log;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import com.technototes.library.hardware.sensor.ColorDistanceSensor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.util.Alliance;

@Config
public class ClawSubsystem implements Subsystem {
    // Correct numbers, tested
    public static double OPEN_SERVO_POSITION = .29;
    public static double CLOSE_SERVO_POSITION = .42;

    private Servo clawServo;
    private ColorDistanceSensor sensor;
    private Boolean isHardware;
    private Alliance alliance;

    public ClawSubsystem(Servo claw, ColorDistanceSensor s, Alliance a) {
        clawServo = claw;
        sensor = s;
        alliance = a;
        isHardware = true;
    }

    // Non-functional subsystem constructor
    public ClawSubsystem() {
        clawServo = null;
        sensor = null;
        isHardware = false;
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
        if (isHardware && sensor.getDistance(DistanceUnit.CM) <= 4.0) {
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

    public Servo getServo() {
        return clawServo;
    }

    @Override
    public void periodic() {
        if (!isClawClosed() && isAllianceCone() && isConeClose()) {
            close();
        }
    }
}
