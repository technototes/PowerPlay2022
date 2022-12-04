package org.firstinspires.ftc.twenty403.command.autonomous.left;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.technototes.library.command.ChoiceCommand;

public class AutoLeftParkingSelectionCycleCommand extends ChoiceCommand {
    public AutoLeftParkingSelectionCycleCommand(
            DrivebaseSubsystem drivebaseSubsystem,
            VisionSubsystem visionSubsystem,
            LiftSubsystem liftSubsystem,
            ClawSubsystem clawSubsystem) {
        super(
                new Pair<>(
                        visionSubsystem.visionPipeline::left,
                        new AutoLeftCycleLeft(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(
                        visionSubsystem.visionPipeline::middle,
                        new AutoLeftCycleMiddle(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(
                        visionSubsystem.visionPipeline::right,
                        new AutoLeftCycleRight(drivebaseSubsystem, clawSubsystem, liftSubsystem)));
    }
}
