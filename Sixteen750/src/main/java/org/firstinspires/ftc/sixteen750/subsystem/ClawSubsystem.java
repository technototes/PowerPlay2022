package org.firstinspires.ftc.sixteen750.subsystem;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

public class ClawSubsystem implements Subsystem {
    public static double CLAW_OPEN = 0.6; // Verified
    public static double CLAW_CLOSE = 0.5; // Verified
    public static double ELBOW_CARRY = 0.4;
    public static double ELBOW_RELEASE = 0.0;
    public static double FLIPPER_GROUND_JUNCTION = 2;
    public static double ELBOW_GROUND = 1;
    public static double FLIPPER_LOW_JUNCTION = 10;
    public static double ELBOW_LOW = 2;
    public static double FLIPPER_MID_JUNCTION = 25;
    public static double ELBOW_MID = 3;
    public static double FLIPPER_HIGH_JUNCTION = 35;
    public static double ELBOW_HIGH = 4;

    public static double FLIPPER_UPPER_NORMAL = 0.4; // Verified
    public static double FLIPPER_LOWER_NORMAL = 0.5; // Verified
    public static double FLIPPER_SCORE_MID_JUNCTION = 0.18; // Verified

    public static double ELBOW_MIN_RANGE = 0.3;
    public static double ELBOW_MAX_RANGE = 0.7;
    public static double ELBOW_UPWARD = 0.9; // Verified
    public static double ELBOW_INTAKE = 0.18; // Verified
    public static double ELBOW_SCORE_MID = 0.9; // Verified


    private Servo clawServo;
    private Servo flipperServo;
    private Servo elbowServo;
    private DistanceSensor sensor; // not on the bot currently

    public ClawSubsystem(Servo claw, Servo flipper, Servo elbow, DistanceSensor s) {
        clawServo = claw;
        flipperServo = flipper;
        elbowServo = elbow;
        sensor = s;
    }

    public ClawSubsystem(Servo claw, Servo flipper, Servo elbow) {
        clawServo = claw;
        flipperServo = flipper;
        elbowServo = elbow;
    }

    public ClawSubsystem() {
        clawServo = null;
        flipperServo = null;
        elbowServo = null;
        sensor = null;
    }

    public void clawOpen() {
        clawServo.setPosition(CLAW_OPEN);
    }

    public void clawClose() {
        clawServo.setPosition(CLAW_CLOSE);
    }

    public void carry() {
        clawClose();
        elbowServo.setPosition(ELBOW_CARRY);
    }

    public void release() {
        elbowServo.setPosition(ELBOW_RELEASE);
        clawOpen();
    }

    public void scoreGroundJunction() {
        flipperServo.setPosition(FLIPPER_GROUND_JUNCTION);
        elbowServo.setPosition(ELBOW_GROUND);
    }

    public void flipperLowJunction() {
        flipperServo.setPosition(FLIPPER_LOW_JUNCTION);
    }

    public void elbowLowJunction() {
        elbowServo.setPosition(ELBOW_LOW);
    }

    public void flipperMediumJunction() {
        flipperServo.setPosition(FLIPPER_MID_JUNCTION);
    }

    public void elbowMediumJunction() {
        elbowServo.setPosition(ELBOW_MID);
    }

    public void flipperHighJunction() {
        flipperServo.setPosition(FLIPPER_HIGH_JUNCTION);
    }

    public void elbowHighJunction() {
        elbowServo.setPosition(ELBOW_HIGH);
    }

    public void flipperNormal(){
        flipperServo.setPosition(FLIPPER_UPPER_NORMAL);
    }

    public void elbowUpward(){
        elbowServo.setPosition(ELBOW_UPWARD);
    }

    public boolean isConeClose() {
        if (sensor.getDistance(DistanceUnit.CM) <= 4.0) {
            return true;
        }
        return false;
    }

    public double getClawPosition() {
        return clawServo.getPosition();
    }

    public double getFlipperPosition() {
        return flipperServo.getPosition();
    }

    public double getElbowPosition() {
        return elbowServo.getPosition();
    }

    public void elbowIntake(){
        elbowServo.setPosition(ELBOW_INTAKE);
    }

    public void flipperPreIntake(){
        flipperServo.setPosition(FLIPPER_UPPER_NORMAL);
    }

    public void flipperIntake(){
        flipperServo.setPosition(FLIPPER_LOWER_NORMAL);
    }

    public void elbowScoreMidJunction(){
        elbowServo.setPosition(ELBOW_SCORE_MID);
    }

    public void flipperScoreMidJunction(){
        flipperServo.setPosition(FLIPPER_SCORE_MID_JUNCTION);
    }
}
