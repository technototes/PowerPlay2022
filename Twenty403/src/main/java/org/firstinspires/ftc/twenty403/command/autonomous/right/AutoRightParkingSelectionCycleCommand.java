package org.firstinspires.ftc.twenty403.command.autonomous.right;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.technototes.library.command.ChoiceCommand;

public class AutoRightParkingSelectionCycleCommand extends ChoiceCommand {
    public AutoRightParkingSelectionCycleCommand(
            VisionSubsystem visionSubsystem,
            DrivebaseSubsystem drivebaseSubsystem,
            ClawSubsystem clawSubsystem,
            LiftSubsystem liftSubsystem) {
        super(
                new Pair<>(
                        visionSubsystem.visionPipeline::left,
                        new AutoRightCycleLeft(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(
                        visionSubsystem.visionPipeline::middle,
                        new AutoRightCycleMiddle(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(
                        visionSubsystem.visionPipeline::right,
                        new AutoRightCycleRight(drivebaseSubsystem, clawSubsystem, liftSubsystem)));
    }
}
