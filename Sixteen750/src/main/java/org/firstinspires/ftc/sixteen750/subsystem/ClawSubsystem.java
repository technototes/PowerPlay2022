package org.firstinspires.ftc.sixteen750.subsystem;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

public class ClawSubsystem implements Subsystem {
    public static double CLAW_OPEN = 0.6; // Verified
    public static double CLAW_CLOSE = 0.50; // Verified

    private Servo clawServo;
    private DistanceSensor distanceSensor; // not on the bot currently

    public ClawSubsystem(Servo clawServo, DistanceSensor distanceSensor) {
        this.clawServo = clawServo;
        this.distanceSensor = distanceSensor;
    }

    public double getClawPosition() {
        return (this.clawServo != null) ? this.clawServo.getPosition() : 0;
    }

    private void setClawServoPosition(double pos) {
        if (this.clawServo != null) {
            this.clawServo.setPosition(pos);
        }
    }

    public void clawOpen() {
        setClawServoPosition(CLAW_OPEN);
    }

    public void clawClose() {
        setClawServoPosition(CLAW_CLOSE);
    }

    @Deprecated
    public void carry() {
        clawClose();
    }

    @Deprecated
    public void release() {
        clawOpen();
    }

    public boolean isConeClose() {
        if (distanceSensor.getDistance(DistanceUnit.CM) <= 4.0) {
            return true;
        }
        return false;
    }
}
