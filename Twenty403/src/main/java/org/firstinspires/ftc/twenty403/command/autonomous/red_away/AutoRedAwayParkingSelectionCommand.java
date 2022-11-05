package org.firstinspires.ftc.twenty403.command.autonomous.red_away;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.technototes.library.command.ChoiceCommand;

public class AutoRedAwayParkingSelectionCommand extends ChoiceCommand {
    public AutoRedAwayParkingSelectionCommand(DrivebaseSubsystem drivebaseSubsystem, VisionSubsystem visionSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new ParkRedAwayLeft(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new ParkRedAwayRight(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new ParkRedAwayRight(drivebaseSubsystem)));
    }
}
