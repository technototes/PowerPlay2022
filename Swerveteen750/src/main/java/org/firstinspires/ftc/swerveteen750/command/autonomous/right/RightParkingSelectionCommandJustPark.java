package org.firstinspires.ftc.swerveteen750.command.autonomous.right;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.MecanumDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.VisionSubsystem;

public class RightParkingSelectionCommandJustPark extends ChoiceCommand {
    public RightParkingSelectionCommandJustPark(
            VisionSubsystem visionSubsystem, MecanumDriveSubsystem driveSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new RightJustParkLeft(driveSubsystem)),
                //new Pair<>(visionSubsystem.visionPipeline::middle, new RightJustParkMiddle(driveSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new RightJustParkRight(driveSubsystem)));
    }
}
