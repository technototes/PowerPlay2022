package org.firstinspires.ftc.sixteen750.subsystem;

import android.util.Log;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

public class ClawSubsystem implements Subsystem {
    public static double OPEN_SERVO_POSITION = .8;
    public static double CLOSE_SERVO_POSITION = .5;
    public static double CARRY_SERVO_POSITION = .4;
    public static double RELEASE_SERVO_POSITION = .0;
    private Servo clawServo;
    private Servo flipperServo;
    private DistanceSensor sensor;
    private Boolean isHardware;

    public ClawSubsystem(Servo claw, Servo flipper, DistanceSensor s) {
        clawServo = claw;
        flipperServo = flipper;
        sensor = s;
        isHardware = true;
    }

    //Non-functional subsystem constructor
    public ClawSubsystem() {
        clawServo = null;
        flipperServo = null;
        sensor = null;
        isHardware = false;
    }

    public static void log(String s) {
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

    public void carry() {
        log("Carry");
        close();
        if (isHardware) {
            flipperServo.setPosition(CARRY_SERVO_POSITION);
        }
    }

    public void release() {
        if (isHardware) {
            flipperServo.setPosition(RELEASE_SERVO_POSITION);
        }
        open();
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
