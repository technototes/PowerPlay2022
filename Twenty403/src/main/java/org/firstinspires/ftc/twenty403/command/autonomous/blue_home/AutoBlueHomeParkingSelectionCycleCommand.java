package org.firstinspires.ftc.twenty403.command.autonomous.blue_home;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.twenty403.command.autonomous.blue_away.AutoBlueAwayCycleLeft;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

public class AutoBlueHomeParkingSelectionCycleCommand extends ChoiceCommand {
    public AutoBlueHomeParkingSelectionCycleCommand(VisionSubsystem visionSubsystem, DrivebaseSubsystem drivebaseSubsystem, ClawSubsystem clawSubsystem, LiftSubsystem liftSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new AutoBlueHomeCycleLeft(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new AutoBlueHomeCycleMiddle(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new AutoBlueHomeCycleRight(drivebaseSubsystem, clawSubsystem, liftSubsystem))
        );
    }
}
