package org.firstinspires.ftc.twenty403.command.autonomous.red_home;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.technototes.library.command.ChoiceCommand;

public class AutoRedHomeParkingSelectionCommand extends ChoiceCommand {
    public AutoRedHomeParkingSelectionCommand(DrivebaseSubsystem drivebaseSubsystem, VisionSubsystem visionSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new ParkRedHomeLeft(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new ParkRedHomeRight(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new ParkRedHomeRight(drivebaseSubsystem)));
    }
}
