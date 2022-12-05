package org.firstinspires.ftc.twenty403.command.autonomous.red_home;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.technototes.library.command.ChoiceCommand;

public class AutoRedHomeParkingSelectionCycleCommand extends ChoiceCommand {
    public AutoRedHomeParkingSelectionCycleCommand(
            VisionSubsystem visionSubsystem,
            DrivebaseSubsystem drivebaseSubsystem,
            ClawSubsystem clawSubsystem,
            LiftSubsystem liftSubsystem) {
        super(
                new Pair<>(
                        visionSubsystem.visionPipeline::left,
                        new AutoRedHomeCycleLeft(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(
                        visionSubsystem.visionPipeline::middle,
                        new AutoRedHomeCycleMiddle(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(
                        visionSubsystem.visionPipeline::right,
                        new AutoRedHomeCycleRight(drivebaseSubsystem, clawSubsystem, liftSubsystem)));
    }
}
