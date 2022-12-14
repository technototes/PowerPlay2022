package org.firstinspires.ftc.sixteen750.subsystem;

import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

public class ArmSubsystem implements Subsystem {
    private Servo flipperServo;
    private Servo elbowServo;

    public static double ELBOW_INTAKE = 0.875;
    public static double ELBOW_UPWARD = 0.6;
    public static double ELBOW_SCORE = 0.2;

    public static double FLIPPER_WHEN_ELBOW_INTAKE = 0.55;
    public static double FLIPPER_WHEN_ELBOW_UPWARD = 0.6;
    public static double FLIPPER_WHEN_ELBOW_SCORE = 1;

    public static double TOLERANCE = 0.05;

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

    public void flipperIntake() {
        setFlipperServoPosition(FLIPPER_WHEN_ELBOW_INTAKE);
    }

    public void flipperUpward() {
        setFlipperServoPosition(FLIPPER_WHEN_ELBOW_UPWARD);
    }

    public void flipperScore() {
        setFlipperServoPosition(FLIPPER_WHEN_ELBOW_SCORE);
    }

    public void elbowIntake() {
        setElbowServoPosition(ELBOW_INTAKE);
    }

    public void elbowUpward() {
        setElbowServoPosition(ELBOW_UPWARD);
    }

    public void elbowScore() {
        setElbowServoPosition(ELBOW_SCORE);
    }

    public boolean isArmAtIntakePosition() {
        return Math.abs(getElbowPosition() - ELBOW_INTAKE) < TOLERANCE && Math.abs(getFlipperPosition() - FLIPPER_WHEN_ELBOW_INTAKE) < TOLERANCE;
    }

    public boolean isArmAtUpwardPosition() {
        return Math.abs(getElbowPosition() - ELBOW_UPWARD) < TOLERANCE && Math.abs(getFlipperPosition() - FLIPPER_WHEN_ELBOW_UPWARD) < TOLERANCE;
    }

    public boolean isArmAtScorePosition() {
        return Math.abs(getElbowPosition() - ELBOW_SCORE) < TOLERANCE && Math.abs(getFlipperPosition() - FLIPPER_WHEN_ELBOW_SCORE) < TOLERANCE;
    }
}
