package org.firstinspires.ftc.swerveteen750.subsystem.drive;

import androidx.annotation.NonNull;

import com.technototes.library.subsystem.Subsystem;

import org.firstinspires.ftc.swerveteen750.swerve_util.SimpleSwerveLocalizer;
import org.firstinspires.ftc.swerveteen750.swerve_util.swerve_module.AnotherSwerveModule;

import java.util.ArrayList;

// Intended to use in Autonomous
public class SimpleSwerveDriveSubsystem implements Subsystem {
    private AnotherSwerveModule leftFrontModule;
    private AnotherSwerveModule leftRearModule;
    private AnotherSwerveModule rightFrontModule;
    private AnotherSwerveModule rightRearModule;
    private ArrayList<AnotherSwerveModule> modules;
    private SimpleSwerveLocalizer localizer;

    public static boolean spammyDebug = true;
    public static boolean enableLocalizer = false;

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
        }
        else {
            System.err.println("Left Front Module is null");
        }
        if (leftRearModule != null) {
            this.modules.add(leftRearModule);
        }
        else {
            System.err.println("Left Rear Module is null");
        }
        if (rightFrontModule != null) {
            this.modules.add(rightFrontModule);
        }
        else {
            System.err.println("Right Front Module is null");
        }
        if (rightRearModule != null) {
            this.modules.add(rightRearModule);
        }
        else {
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

        if (spammyDebug){
            if (enableLocalizer) {
                System.out.println(localizer.get());
            }
        }
    }

    // The order of parameter being adjusted as the original one
    public void setModuleOrientations(double leftFront, double leftRear, double rightRear, double rightFront) {
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

    public void setModulePowers(double leftFront, double leftRear, double rightRear, double rightFront) {
        leftFrontModule.setServoPower(leftFront);
        leftRearModule.setServoPower(leftRear);
        rightFrontModule.setServoPower(rightFront);
        rightRearModule.setServoPower(rightRear);
    }

    public void setModulePowers(@NonNull double[] powers) {
        if (powers.length != 4) {
            System.err.println("Invalid array length for setModulePowers()");
            return;
        }
        setModulePowers(powers[0], powers[1], powers[2], powers[3]);
    }
}
