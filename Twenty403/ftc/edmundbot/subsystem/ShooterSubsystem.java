package org.firstinspires.ftc.edmundbot.subsystem;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.subsystem.Subsystem;

public class ShooterSubsystem implements Subsystem {
    private EncodedMotor<DcMotorEx> leftMotor;
    private EncodedMotor<DcMotorEx> rightMotor;

    public ShooterSubsystem(EncodedMotor<DcMotorEx> leftMotor, EncodedMotor<DcMotorEx> rightMotor) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
    }

    public void setSpeed(double power) {
        if (leftMotor != null) {
            leftMotor.setSpeed(power);
        }
        if (rightMotor != null) {
            rightMotor.setSpeed(power);
        }
    }
}
