package org.firstinspires.ftc.twenty403.command.autonomous.left;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.technototes.library.command.ChoiceCommand;

public class AutoLeftParkingSelectionJustParkCommand extends ChoiceCommand {
    public AutoLeftParkingSelectionJustParkCommand(
            DrivebaseSubsystem drivebaseSubsystem, VisionSubsystem visionSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new AutoLeftLeft(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new AutoLeftMiddle(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new AutoLeftRight(drivebaseSubsystem)));
    }
}
