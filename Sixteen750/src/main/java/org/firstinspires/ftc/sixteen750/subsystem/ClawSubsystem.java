package org.firstinspires.ftc.sixteen750.subsystem;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

public class ClawSubsystem implements Subsystem {
    public static double OPEN_CLAW_SERVO_POS = .8;
    public static double CLOSE_CLAW_SERVO_POS = .5;
    public static double ELBOW_CARRY_SERVO_POS = .4;
    public static double ELBOW_RELEASE_SERVO_POS = .0;
    public static double GROUND_JUNCTION = 2;
    public static double GROUND_ELBOW = 1;
    public static double LOW_JUNCTION = 10;
    public static double LOW_ELBOW = 2;
    public static double MID_JUNCTION = 25;
    public static double MID_ELBOW = 3;
    public static double HIGH_JUNCTION = 35;
    public static double HIGH_ELBOW = 4;
    private Servo clawServo;
    private Servo flipperServo;
    private Servo elbowServo;
    private DistanceSensor sensor; //not on the bot currently

    public ClawSubsystem(Servo claw, Servo flipper, Servo elbow, DistanceSensor s) {
        clawServo = claw;
        flipperServo = flipper;
        elbowServo = elbow;
        sensor = s;
    }

    public void open() {
        clawServo.setPosition(OPEN_CLAW_SERVO_POS);
    }

    public void close() {
        clawServo.setPosition(CLOSE_CLAW_SERVO_POS);
    }

    public void carry() {
        close();
        elbowServo.setPosition(ELBOW_CARRY_SERVO_POS);
    }
    public void release() {
        elbowServo.setPosition(ELBOW_RELEASE_SERVO_POS);
        open();
    }


    public void scoreGroundJunction() {
        flipperServo.setPosition(GROUND_JUNCTION);
        elbowServo.setPosition(GROUND_ELBOW);
    }


    public void flipperLowJunction() {
        flipperServo.setPosition(LOW_JUNCTION);
    }
    public void elbowLowJunction() {
        elbowServo.setPosition(LOW_ELBOW);
    }


    public void flipperMediumJunction() {
        flipperServo.setPosition(MID_JUNCTION);
    }
    public void elbowMidiumJunction() {
        elbowServo.setPosition(MID_ELBOW);
    }


    public void flipperHighJunction() {
        flipperServo.setPosition(HIGH_JUNCTION);
    }
    public void elbowHighJunction() {
        elbowServo.setPosition(HIGH_ELBOW);
    }

    public boolean isConeClose() {
        if (sensor.getDistance(DistanceUnit.CM) <= 4.0) {
            return true;
        }
        return false;
    }

    public Servo getServo() {
        return clawServo;
    }
}
