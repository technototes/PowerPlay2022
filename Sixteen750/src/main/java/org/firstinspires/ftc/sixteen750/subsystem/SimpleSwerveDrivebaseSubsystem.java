package org.firstinspires.ftc.sixteen750.subsystem;

import com.technototes.library.subsystem.Subsystem;

import org.firstinspires.ftc.sixteen750.swerve_util.SwerveModule;

import java.util.ArrayList;

public class SimpleSwerveDrivebaseSubsystem implements Subsystem {
    private SwerveModule leftFrontModule;
    private SwerveModule leftRearModule;
    private SwerveModule rightFrontModule;
    private SwerveModule rightRearModule;
    private ArrayList<SwerveModule> modules;


    public SimpleSwerveDrivebaseSubsystem(SwerveModule leftFrontModule,
                                          SwerveModule leftRearModule,
                                          SwerveModule rightFrontModule,
                                          SwerveModule rightRearModule
    ) {
        this.leftFrontModule = leftFrontModule;
        this.leftRearModule = leftRearModule;
        this.rightFrontModule = rightFrontModule;
        this.rightRearModule = rightRearModule;

        this.modules = new ArrayList<>();
        if (leftFrontModule != null) {
            this.modules.add(leftFrontModule);
        }
        if (leftRearModule != null) {
            this.modules.add(leftRearModule);
        }
        if (rightFrontModule != null) {
            this.modules.add(rightFrontModule);
        }
        if (rightRearModule != null) {
            this.modules.add(rightRearModule);
        }
    }

    @Override
    public void periodic() {
        for (SwerveModule m : modules) {
            m.update();
        }
    }
}
