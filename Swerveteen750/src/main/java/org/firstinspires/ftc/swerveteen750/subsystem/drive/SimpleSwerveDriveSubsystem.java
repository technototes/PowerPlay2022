package org.firstinspires.ftc.swerveteen750.subsystem.drive;

import com.technototes.library.subsystem.Subsystem;

import org.firstinspires.ftc.swerveteen750.swerve_util.SimpleSwerveLocalizer;
import org.firstinspires.ftc.swerveteen750.swerve_util.SwerveModule;

import java.util.ArrayList;

public class SimpleSwerveDriveSubsystem implements Subsystem {
    private SwerveModule leftFrontModule;
    private SwerveModule leftRearModule;
    private SwerveModule rightFrontModule;
    private SwerveModule rightRearModule;
    private ArrayList<SwerveModule> modules;
    private SimpleSwerveLocalizer localizer;

    public static boolean spammyDebug = true;
    public static boolean enableLocalizer = false;

    // TODO: take IMU and calculate ExternalHeading
    public SimpleSwerveDriveSubsystem(SwerveModule leftFrontModule,
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
            this.localizer = new SimpleSwerveLocalizer(() -> 0, this.modules.toArray(new SwerveModule[4]));
        }
    }

    @Override
    public void periodic() {
        for (SwerveModule m : modules) {
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
}
