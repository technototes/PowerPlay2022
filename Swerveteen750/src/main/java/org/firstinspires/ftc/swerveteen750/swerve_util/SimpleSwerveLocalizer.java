package org.firstinspires.ftc.swerveteen750.swerve_util;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.swerveteen750.swerve_util.swerve_module.SwerveModule;

import java.util.Arrays;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class SimpleSwerveLocalizer implements Supplier<Pose2d> {
    public SwerveModule.SwerveModuleState[] modules;
    public DoubleSupplier headingSupplier;
    public Pose2d poseEstimate;
    public Pose2d pastPoseEstimate;

    public SimpleSwerveLocalizer(DoubleSupplier headingSupplier, SwerveModule... mods){
        this.modules = Arrays.stream(mods).map(SwerveModule::asState).toArray(SwerveModule.SwerveModuleState[]::new);
        this.headingSupplier = headingSupplier;
        this.poseEstimate = new Pose2d();
        this.pastPoseEstimate = new Pose2d();
    }

    public Pose2d getPoseEstimate() {
        return poseEstimate;
    }

    public void update() {
        pastPoseEstimate = poseEstimate;
        Vector2d accumulator = new Vector2d();
        double head = headingSupplier.getAsDouble();
        for(SwerveModule.SwerveModuleState s : modules){
            accumulator = accumulator.plus(s.calculateDelta());
        }
        accumulator = accumulator.div(modules.length).rotated(head);
        poseEstimate = new Pose2d(poseEstimate.vec().plus(accumulator), head);
    }

    @Override
    public Pose2d get() {
        return poseEstimate;
    }
}
