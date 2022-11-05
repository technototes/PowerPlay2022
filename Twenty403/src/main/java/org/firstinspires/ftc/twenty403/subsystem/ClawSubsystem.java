package org.firstinspires.ftc.twenty403.subsystem;

import android.util.Log;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

@Config
public class ClawSubsystem implements Subsystem {
    // Correct numbers, tested
    public static double OPEN_SERVO_POSITION = .4;
    public static double CLOSE_SERVO_POSITION = .55;

    private Servo clawServo;
    private DistanceSensor sensor;
    private Boolean isHardware;

    public ClawSubsystem(Servo claw, DistanceSensor s) {
        clawServo = claw;
        sensor = s;
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

    public Servo getServo() {
        return clawServo;
    }
}
