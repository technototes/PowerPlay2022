package org.firstinspires.ftc.sixteen750.subsystem;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.path.subsystem.MecanumConstants;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import java.util.function.Supplier;

public class MecanumDriveSubsystem extends MecanumDrivebaseSubsystem implements Supplier<Pose2d> {
    // TODO: create DriveConstants

    public MecanumDriveSubsystem(EncodedMotor<DcMotorEx> fl,
                                 EncodedMotor<DcMotorEx> fr,
                                 EncodedMotor<DcMotorEx> rl,
                                 EncodedMotor<DcMotorEx> rr,
                                 IMU i,
                                 MecanumConstants c
    ) {
        super(fl, fr, rl, rr, i, c);
    }

    @Override
    public Pose2d get() {
        return getPoseEstimate();
    }
}
