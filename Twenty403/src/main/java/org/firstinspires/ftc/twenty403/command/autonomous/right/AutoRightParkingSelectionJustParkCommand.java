package org.firstinspires.ftc.twenty403.command.autonomous.right;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.technototes.library.command.ChoiceCommand;

public class AutoRightParkingSelectionJustParkCommand extends ChoiceCommand {
    public AutoRightParkingSelectionJustParkCommand(
            VisionSubsystem visionSubsystem, DrivebaseSubsystem drivebaseSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new AutoRightParkingLeft(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new AutoRightParkingMiddle(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new AutoRightParkingRight(drivebaseSubsystem)));
    }
}
