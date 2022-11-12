package org.firstinspires.ftc.twenty403.command.autonomous.blue_home;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

public class AutoBlueHomeParkingSelectionFullCycleCommand extends ChoiceCommand {
    public AutoBlueHomeParkingSelectionFullCycleCommand(
            VisionSubsystem visionSubsystem,
            DrivebaseSubsystem drivebaseSubsystem,
            ClawSubsystem clawSubsystem,
            LiftSubsystem liftSubsystem) {
        super(
                new Pair<>(
                        visionSubsystem.visionPipeline::left,
                        new AutoBlueHomeFullCycleLeft(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(
                        visionSubsystem.visionPipeline::middle,
                        new AutoBlueHomeFullCycleMiddle(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(
                        visionSubsystem.visionPipeline::right,
                        new AutoBlueHomeFullCycleRight(drivebaseSubsystem, clawSubsystem, liftSubsystem)));
    }
}
