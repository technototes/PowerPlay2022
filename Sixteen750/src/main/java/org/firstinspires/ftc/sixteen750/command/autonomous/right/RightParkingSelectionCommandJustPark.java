package org.firstinspires.ftc.sixteen750.command.autonomous.right;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import org.firstinspires.ftc.sixteen750.subsystem.VisionSubsystem;

public class RightParkingSelectionCommandJustPark extends ChoiceCommand {
    public RightParkingSelectionCommandJustPark(
            VisionSubsystem visionSubsystem, MecanumDrivebaseSubsystem drivebaseSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new RightJustParkLeft(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new RightJustParkMiddle(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new RightJustParkRight(drivebaseSubsystem)));
    }
}
