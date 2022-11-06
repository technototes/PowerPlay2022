package org.firstinspires.ftc.twenty403.command.autonomous.blue_away;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.technototes.library.command.ChoiceCommand;

public class AutoBlueAwayParkingSelectionCycleCommand extends ChoiceCommand {
    public AutoBlueAwayParkingSelectionCycleCommand(
            DrivebaseSubsystem drivebaseSubsystem,
            VisionSubsystem visionSubsystem,
            LiftSubsystem liftSubsystem,
            ClawSubsystem clawSubsystem) {
        super(
                new Pair<>(
                        visionSubsystem.visionPipeline::left,
                        new AutoBlueAwayCycleLeft(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(
                        visionSubsystem.visionPipeline::middle,
                        new AutoBlueAwayCycleMiddle(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(
                        visionSubsystem.visionPipeline::right,
                        new AutoBlueAwayCycleRight(drivebaseSubsystem, clawSubsystem, liftSubsystem)));
    }
}
