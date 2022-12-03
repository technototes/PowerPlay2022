package org.firstinspires.ftc.sixteen750.subsystem;

import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

public class ArmSubsystem implements Subsystem {
    private Servo flipperServo;
    private Servo elbowServo;

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
        setFlipperServoPosition(_ELBOW_INTAKE_FLIPPER);
    }

    public void flipperUpward() {
        setFlipperServoPosition(_ELBOW_UPWARD_FLIPPER);
    }

    public void flipperScore() {
        setFlipperServoPosition(_ELBOW_SCORE_FLIPPER);
    }

    public void elbowIntake() {
        setElbowServoPosition(_ELBOW_INTAKE);
    }

    public void elbowUpward() {
        setElbowServoPosition(_ELBOW_UPWARD);
    }

    public void elbowScore() {
        setElbowServoPosition(_ELBOW_SCORE);
    }
}
