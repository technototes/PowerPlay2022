package org.firstinspires.ftc.sixteen750.command.autonomous.left;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import org.firstinspires.ftc.sixteen750.command.autonomous.right.RightJustParkLeft;
import org.firstinspires.ftc.sixteen750.command.autonomous.right.RightJustParkMiddle;
import org.firstinspires.ftc.sixteen750.command.autonomous.right.RightJustParkRight;
import org.firstinspires.ftc.sixteen750.subsystem.VisionSubsystem;

public class LeftParkingSelectionCommandJustPark extends ChoiceCommand {
    public LeftParkingSelectionCommandJustPark(
            VisionSubsystem visionSubsystem, MecanumDrivebaseSubsystem drivebaseSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new LeftJustParkLeft(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new LeftJustParkMiddle(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new LeftJustParkRight(drivebaseSubsystem)));
    }
}
