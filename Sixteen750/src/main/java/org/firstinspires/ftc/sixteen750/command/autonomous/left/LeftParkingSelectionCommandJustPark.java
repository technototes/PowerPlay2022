package org.firstinspires.ftc.sixteen750.command.autonomous.left;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.sixteen750.subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.VisionSubsystem;

public class LeftParkingSelectionCommandJustPark extends ChoiceCommand {
    public LeftParkingSelectionCommandJustPark(
            VisionSubsystem visionSubsystem, MecanumDriveSubsystem driveSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new LeftJustParkLeft(driveSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new LeftJustParkMiddle(driveSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new LeftJustParkRight(driveSubsystem)));
    }
}
