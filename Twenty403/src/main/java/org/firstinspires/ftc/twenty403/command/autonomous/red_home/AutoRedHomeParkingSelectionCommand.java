package org.firstinspires.ftc.twenty403.command.autonomous.red_home;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

public class AutoRedHomeParkingSelectionCommand extends ChoiceCommand {
    public AutoRedHomeParkingSelectionCommand(DrivebaseSubsystem drivebaseSubsystem, VisionSubsystem visionSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new AutoRedHomeLeft(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new AutoRedHomeRight(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new AutoRedHomeRight(drivebaseSubsystem))
        );
    }
}