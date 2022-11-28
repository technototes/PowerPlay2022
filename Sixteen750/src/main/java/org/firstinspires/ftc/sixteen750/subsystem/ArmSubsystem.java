package org.firstinspires.ftc.sixteen750.subsystem;

import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

public class ArmSubsystem implements Subsystem {
    private Servo flipperServo;
    private Servo elbowServo;

    public static double ELBOW_CARRY = 0.4;
    public static double ELBOW_RELEASE = 0.0;
    public static double FLIPPER_GROUND_JUNCTION;
    public static double ELBOW_GROUND;
    public static double FLIPPER_LOW_JUNCTION;
    public static double ELBOW_LOW;
    public static double FLIPPER_MID_JUNCTION;
    public static double ELBOW_MID;
    public static double FLIPPER_HIGH_JUNCTION;
    public static double ELBOW_HIGH;

    public static double FLIPPER_UPPER_NORMAL = 0.4; // Verified
    public static double FLIPPER_LOWER_NORMAL = 0.5; // Verified
    public static double FLIPPER_SCORE_MID_JUNCTION = 0.26; // Verified

    public static double ELBOW_RANGE_MIN = 0.2;
    public static double ELBOW_RANGE_MAX = 0.9;
    public static double ELBOW_UPWARD = 0.9; // Verified
    public static double ELBOW_INTAKE = 0.2; // Verified
    public static double ELBOW_SCORE_MID = 0.9; // Verified

    // new value
    public static double _ELBOW_INTAKE = 0.875;
    public static double _ELBOW_UPWARD = 0.6;
    public static double _ELBOW_SCORE = 0.1;

    public static double _ELBOW_INTAKE_FLIPPER = 0.55;
    public static double _ELBOW_UPWARD_FLIPPER = 0.6;
    public static double _ELBOW_SCORE_FLIPPER = 1;



    public ArmSubsystem(Servo flipperServo, Servo elbowServo){
        this.flipperServo = flipperServo;
        this.elbowServo = elbowServo;
    }

    public double getFlipperPosition() {
        return (this.flipperServo != null) ? this.flipperServo.getPosition() : 0;
    }

    public double getElbowPosition() {
        return (this.elbowServo != null) ? this.elbowServo.getPosition() : 0;
    }

    private void setFlipperServoPosition(double pos) {
        if (this.flipperServo != null) {
            this.flipperServo.setPosition(pos);
        }
    }

    private void setElbowServoPosition(double pos) {
        if (this.elbowServo != null) {
            this.elbowServo.setPosition(pos);
        }
    }

    public void flipperNormal() {
        flipperServo.setPosition(FLIPPER_UPPER_NORMAL);
    }

    public void elbowUpward() {
        setElbowServoPosition(ELBOW_UPWARD);
    }

    public void elbowIntakeGround() {
        setElbowServoPosition(ELBOW_INTAKE);
    }

    public void flipperPreIntake() {
        setFlipperServoPosition(FLIPPER_UPPER_NORMAL);
    }

    public void flipperIntake() {
        setFlipperServoPosition(FLIPPER_LOWER_NORMAL);
    }

    public void elbowScoreMidJunction() {
        setElbowServoPosition(ELBOW_SCORE_MID);
    }

    public void flipperScoreMidJunction() {
        setFlipperServoPosition(FLIPPER_SCORE_MID_JUNCTION);
    }

    public void flipperServoIncrementalDown() {
        setFlipperServoPosition(Range.clip(getFlipperPosition() - 0.02, 0, 1));
    }

    public void flipperServoIncrementalUp() {
        setFlipperServoPosition(Range.clip(getFlipperPosition() + 0.02, 0, 1));
    }

    public void elbowServoIncrementalDown() {
        setElbowServoPosition(Range.clip(getElbowPosition() - 0.03, 0, 1));
    }

    public void elbowServoIncrementalUp() {
        setElbowServoPosition(Range.clip(getElbowPosition() + 0.03, 0, 1));
    }
}
