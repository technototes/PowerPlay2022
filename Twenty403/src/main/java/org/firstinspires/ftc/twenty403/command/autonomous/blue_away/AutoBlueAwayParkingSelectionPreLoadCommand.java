package org.firstinspires.ftc.twenty403.command.autonomous.blue_away;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

public class AutoBlueAwayParkingSelectionPreLoadCommand extends ChoiceCommand {
    public AutoBlueAwayParkingSelectionPreLoadCommand(DrivebaseSubsystem drivebaseSubsystem, VisionSubsystem visionSubsystem, LiftSubsystem liftSubsystem, ClawSubsystem clawSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new AutoBlueAwayPreLoadLeft(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new AutoBlueAwayPreLoadMiddle(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new AutoBlueAwayPreLoadRight(drivebaseSubsystem, clawSubsystem, liftSubsystem))
        );
    }
}
