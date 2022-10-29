package org.firstinspires.ftc.twenty403.command.autonomous.blue_away;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

public class AutoBlueAwayParkingSelectionCommand extends ChoiceCommand {
        public AutoBlueAwayParkingSelectionCommand(DrivebaseSubsystem drivebaseSubsystem, VisionSubsystem visionSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new AutoBlueAwayLeft(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new AutoBlueAwayRight(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new AutoBlueAwayRight(drivebaseSubsystem))
        );
    }
}

