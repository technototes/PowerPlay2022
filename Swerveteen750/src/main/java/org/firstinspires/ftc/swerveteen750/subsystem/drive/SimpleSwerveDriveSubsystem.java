package org.firstinspires.ftc.swerveteen750.subsystem.drive;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.subsystem.Subsystem;

import org.firstinspires.ftc.swerveteen750.swerve_util.SimpleSwerveLocalizer;
import org.firstinspires.ftc.swerveteen750.swerve_util.swerve_module.AnotherSwerveModule;

import java.util.ArrayList;

// Intended to use in Autonomous
@Config
public class SimpleSwerveDriveSubsystem implements Subsystem {
    private final AnotherSwerveModule leftFrontModule;
    private final AnotherSwerveModule leftRearModule;
    private final AnotherSwerveModule rightFrontModule;
    private final AnotherSwerveModule rightRearModule;
    private final ArrayList<AnotherSwerveModule> modules;
    private SimpleSwerveLocalizer localizer;

    public static boolean spammyDebug = true;
    public static boolean enableLocalizer = false;

    public static double LF_MOTOR_SCALAR = 1.00;
    public static double LR_MOTOR_SCALAR = 0.85;
    public static double RF_MOTOR_SCALAR = 0.86;
    public static double RR_MOTOR_SCALAR = 1.00;

    // TODO: take IMU and calculate ExternalHeading
    public SimpleSwerveDriveSubsystem(AnotherSwerveModule leftFrontModule,
                                      AnotherSwerveModule leftRearModule,
                                      AnotherSwerveModule rightFrontModule,
                                      AnotherSwerveModule rightRearModule
    ) {
        this.leftFrontModule = leftFrontModule;
        this.leftRearModule = leftRearModule;
        this.rightFrontModule = rightFrontModule;
        this.rightRearModule = rightRearModule;

        this.modules = new ArrayList<>();
        if (leftFrontModule != null) {
            this.modules.add(leftFrontModule);
            leftFrontModule.setTargetRotation(0);
        } else {
            System.err.println("Left Front Module is null");
        }
        if (leftRearModule != null) {
            this.modules.add(leftRearModule);
            leftRearModule.setTargetRotation(0);
        } else {
            System.err.println("Left Rear Module is null");
        }
        if (rightFrontModule != null) {
            this.modules.add(rightFrontModule);
            rightFrontModule.setTargetRotation(0);
        } else {
            System.err.println("Right Front Module is null");
        }
        if (rightRearModule != null) {
            this.modules.add(rightRearModule);
            rightRearModule.setTargetRotation(0);
        } else {
            System.err.println("Right Rear Module is null");
        }

        if (enableLocalizer) {
            this.localizer = new SimpleSwerveLocalizer(() -> 0, this.modules.toArray(new AnotherSwerveModule[4]));
        }
    }

    @Override
    public void periodic() {
        for (AnotherSwerveModule m : modules) {
            m.update();
        }

        if (enableLocalizer) {
            localizer.update();
        }

        if (spammyDebug) {
            if (enableLocalizer) {
                System.out.println(localizer.get());
            }
        }
    }

    // The order of parameter being adjusted as the original one
    public void setModuleOrientations(double leftFront, double leftRear, double rightFront, double rightRear) {
        leftFrontModule.setTargetRotation(leftFront);
        leftRearModule.setTargetRotation(leftRear);
        rightFrontModule.setTargetRotation(rightFront);
        rightRearModule.setTargetRotation(rightRear);
    }

    public void setModuleOrientations(@NonNull double[] orientations) {
        if (orientations.length != 4) {
            System.err.println("Invalid array length for setModuleOrientations()");
            return;
        }
        setModuleOrientations(orientations[0], orientations[1], orientations[2], orientations[3]);
    }

    public void setModulePowers(double leftFront, double leftRear, double rightFront, double rightRear) {
        leftFrontModule.setMotorPower(leftFront * LF_MOTOR_SCALAR);
        leftRearModule.setMotorPower(leftRear * LR_MOTOR_SCALAR);
        rightFrontModule.setMotorPower(rightFront * RF_MOTOR_SCALAR);
        rightRearModule.setMotorPower(rightRear * RR_MOTOR_SCALAR);
    }

    public void setModulePowers(@NonNull double[] powers) {
        if (powers.length != 4) {
            System.err.println("Invalid array length for setModulePowers()");
            return;
        }
        setModulePowers(powers[0], powers[1], powers[2], powers[3]);
    }

    public void rotatingClockwise() {
        setModuleOrientations(Math.toRadians(315), Math.toRadians(15), Math.toRadians(315), Math.toRadians(15));
    }

    double leftFrontMotorEncoderZero = 0;
    double leftRearMotorEncoderZero = 0;
    double rightFrontMotorEncoderZero = 0;
    double rightRearMotorEncoderZero = 0;

    public void setMotorEncoderZero() {
        leftFrontMotorEncoderZero = leftFrontModule.getUnadjustedWheelInchPosition();
        leftRearMotorEncoderZero = leftRearModule.getUnadjustedWheelInchPosition();
        rightFrontMotorEncoderZero = rightFrontModule.getUnadjustedWheelInchPosition();
        rightRearMotorEncoderZero = rightRearModule.getUnadjustedWheelInchPosition();
    }

    public double[] getAdjustedMotorEncoderValues() {
        return new double[]{
                leftFrontModule.getUnadjustedWheelInchPosition() - leftFrontMotorEncoderZero,
                leftRearModule.getUnadjustedWheelInchPosition() - leftRearMotorEncoderZero,
                rightFrontModule.getUnadjustedWheelInchPosition() - rightFrontMotorEncoderZero,
                rightRearModule.getUnadjustedWheelInchPosition() - rightRearMotorEncoderZero
        };
    }
}
