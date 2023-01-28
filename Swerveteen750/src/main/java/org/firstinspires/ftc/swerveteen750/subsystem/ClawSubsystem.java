package org.firstinspires.ftc.swerveteen750.subsystem;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.swerveteen750.helpers.ColorHelper;

import com.acmerobotics.dashboard.config.Config;

import com.technototes.library.hardware.sensor.ColorDistanceSensor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.util.Alliance;

@Config
public class ClawSubsystem implements Subsystem {
    public static double CLAW_OPEN = 0.5; //
//    public static double CLAW_CLOSE = 0.375; //
    public static double CLAW_CLOSE = 0.3625; //
    public static double CLAW_FLAT = 0.6; //

    private Servo clawServo;
    private ColorDistanceSensor colorDistanceSensor; // not on the bot currently
    public static double CLAW_TOLERANCE_ZONE = 0;

    public static boolean CLAW_AUTO_CLOSE = true;
    public static int FAKE_RGB_VALUE = 0x008000;
    public static double FAKE_DISTANCE = 3.0;
    private Alliance alliance;
    public LiftSubsystem liftSubsystem;

    public ClawSubsystem(Servo clawServo, ColorDistanceSensor colorDistanceSensor, Alliance alliance) {
        this.clawServo = clawServo;
        this.colorDistanceSensor = colorDistanceSensor;
        this.alliance = alliance;
    }

    public ClawSubsystem(Servo clawServo) {
        this(clawServo, null, Alliance.NONE);
    }

    public double getClawServoPosition() {
        return (this.clawServo != null) ? this.clawServo.getPosition() : 0;
    }

    private void setClawServoPosition(double pos) {
        if (this.clawServo != null) {
            this.clawServo.setPosition(pos);
        }
    }

    public void openClaw() {
        setClawServoPosition(CLAW_OPEN);
    }

    public void closeClaw() {
        setClawServoPosition(CLAW_CLOSE);
    }

    public void flattenClaw() {
        setClawServoPosition(CLAW_FLAT);
    }

//    @Override
//    public void periodic() {
//        if (clawServo != null && CLAW_AUTO_CLOSE) {
//            // TODO: null check for the lift subsystem
//            if (liftSubsystem.canAutoCloseClaw() && !isClawAlreadyClosed() && isAllianceCone() && isConeClose()) {
//                closeClaw();
//            }
//        }
//    }

    public boolean isConeClose() {
        if (colorDistanceSensor != null && colorDistanceSensor.getDistance(DistanceUnit.CM) <= 4.0) {
            return true;
        }
        return false;
    }

    public boolean isAllianceCone() {
        switch (alliance) {
            case NONE:
                // For no alliance, it's always an alliance cone :)
                return true;
            case RED:
                int rgb = readRgb();
                return ColorHelper.red(rgb) > ColorHelper.blue(rgb);
            case BLUE:
                int rgb2 = readRgb();
                return ColorHelper.blue(rgb2) > ColorHelper.red(rgb2);
            default:
                return false;
        }
    }

    public boolean isClawAlreadyClosed() {
        // This is going to say the claw is closed, just because we squeeze the jaws together
        // manually, so we need to check to see if the servo has had it's position explicitly set
        // instead of just checking the servo's position...
        return clawServo != null && (getClawServoPosition() <= CLAW_CLOSE + CLAW_TOLERANCE_ZONE);
    }

    private double readSensor() {
        if (colorDistanceSensor != null) {
            return colorDistanceSensor.getDistance(DistanceUnit.CM);
        } else {
            return FAKE_DISTANCE;
        }
    }

    private int readRgb() {
        if (colorDistanceSensor != null) {
            return colorDistanceSensor.rgb();
        } else {
            return FAKE_RGB_VALUE;
        }
    }
}
