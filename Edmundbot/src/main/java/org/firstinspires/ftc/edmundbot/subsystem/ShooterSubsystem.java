package org.firstinspires.ftc.edmundbot.subsystem;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.subsystem.Subsystem;

public class ShooterSubsystem implements Subsystem {
    private final EncodedMotor<DcMotorEx> leftMotor;
    private final EncodedMotor<DcMotorEx> rightMotor;
    private final EncodedMotor<DcMotorEx> intakeMotor;

    public ShooterSubsystem(EncodedMotor<DcMotorEx> leftMotor, EncodedMotor<DcMotorEx> rightMotor, EncodedMotor<DcMotorEx> intakeMotor) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.intakeMotor = intakeMotor;
    }

    public void setSpeed(double power) {
        setLeftMotorSpeed(power);
        setRightMotorSpeed(power);
    }

    private void setLeftMotorSpeed(double power) {
        if (leftMotor != null) {
            leftMotor.setSpeed(power);
        }
    }

    private void setRightMotorSpeed(double power) {
        if (rightMotor != null) {
            rightMotor.setSpeed(power);
        }
    }

    public void setIntakeSpeed(double power) {
        if (intakeMotor != null) {
            intakeMotor.setSpeed(power);
        }
    }

    public void startIntake() {
        setIntakeSpeed(1);
    }

    public void stopIntake() {
        setIntakeSpeed(0);
    }
}
