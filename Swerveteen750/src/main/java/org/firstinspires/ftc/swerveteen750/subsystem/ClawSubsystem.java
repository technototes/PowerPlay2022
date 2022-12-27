package org.firstinspires.ftc.swerveteen750.subsystem;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

@Config
public class ClawSubsystem implements Subsystem {
    public static double CLAW_OPEN = 0.4; // Verified
    public static double CLAW_CLOSE = 0.32; // Verified
    public static double CLAW_FLAT = 0.66; // Verified

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

    public void clawFlat() {
        setClawServoPosition(CLAW_FLAT);
    }

    public boolean isConeClose() {
        if (distanceSensor.getDistance(DistanceUnit.CM) <= 4.0) {
            return true;
        }
        return false;
    }
}
