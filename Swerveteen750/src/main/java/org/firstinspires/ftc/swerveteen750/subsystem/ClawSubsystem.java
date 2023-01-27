package org.firstinspires.ftc.swerveteen750.subsystem;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.swerveteen750.helpers.ColorHelper;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import com.technototes.library.hardware.sensor.ColorDistanceSensor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.util.Alliance;

@Config
public class ClawSubsystem implements Subsystem {
    public static double CLAW_OPEN = 0.5; //
    public static double CLAW_CLOSE = 0.375; //
    public static double CLAW_FLAT = 0.6; //

    private Servo clawServo;
    private ColorDistanceSensor colorDistanceSensor; // not on the bot currently
    public double CLAW_SERVO_POS = 0.35;
    public static double autoCloseDeadzone = 0;
    private boolean servoSet;

    public static int FAKE_RGB_VALUE = 0x008000;
    public static double FAKE_DISTANCE = 3.0;
    private boolean isHardware;
    private Alliance alliance;
    private boolean autoClose;
    private LiftSubsystem liftSubsystem;

    public ClawSubsystem(Servo clawServo, ColorDistanceSensor colorDistanceSensor) {
        this.clawServo = clawServo;
        this.colorDistanceSensor = colorDistanceSensor;
        servoSet = true;
    }

    public ClawSubsystem(Servo clawServo) {
        this.clawServo = clawServo;
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
        if (colorDistanceSensor.getDistance(DistanceUnit.CM) <= 4.0) {
            return true;
        }
        return false;
    }

    @Override
    public void periodic() {
        if (isHardware && autoClose) {
            if (
                    liftSubsystem.canAutoClose() && !isClawClose() && isAllianceCone() && isConeClose()
            ) {
                clawClose();
                ///this.wait(.2);
                //CommandScheduler.getInstance().schedule(new ClawAutoCloseWithLift(this,liftSubsystem));
                //CommandScheduler.getInstance().scheduleOnce(new ClawAutoCloseWithLift(this, liftSubsystem));
            }
        }
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

    public boolean isClawClose() {
        double curPos = readServo();
        // This is going to say the claw is closed, just because we squeeze the jaws together
        // manually, so we need to check to see if the servo has had it's position explicitly set
        // instead of just checking the servo's position...
        return (
                servoSet &&
                        //Math.abs(curPos - CLOSE_SERVO_POSITION) < Math.abs(curPos - OPEN_SERVO_POSITION)
                        (
                                curPos <= CLAW_CLOSE + autoCloseDeadzone
                                /*&& curPos >= CLOSE_SERVO_POSITION - autoCloseDeadzone*/
                        )
        );
    }

    private double readServo() {
        if (isHardware) {
            double pos = clawServo.getPosition();
            CLAW_SERVO_POS = pos;
            return pos;
        } else {
            return CLAW_SERVO_POS;
        }
    }


    private double readSensor() {
        if (isHardware) {
            return colorDistanceSensor.getDistance(DistanceUnit.CM);
        } else {
            return FAKE_DISTANCE;
        }
    }

    private int readRgb() {
        if (isHardware) {
            return colorDistanceSensor.rgb();
        } else {
            return FAKE_RGB_VALUE;
        }
    }
}
