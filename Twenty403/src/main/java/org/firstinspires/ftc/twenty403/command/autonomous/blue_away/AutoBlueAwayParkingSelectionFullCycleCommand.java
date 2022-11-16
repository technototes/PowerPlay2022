package org.firstinspires.ftc.twenty403.command.autonomous.blue_away;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.twenty403.command.autonomous.blue_away.AutoBlueAwayFullCycleLeft;
import org.firstinspires.ftc.twenty403.command.autonomous.blue_away.AutoBlueAwayFullCycleMiddle;
import org.firstinspires.ftc.twenty403.command.autonomous.blue_away.AutoBlueAwayFullCycleRight;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

public class AutoBlueAwayParkingSelectionFullCycleCommand extends ChoiceCommand {
    public AutoBlueAwayParkingSelectionFullCycleCommand(
            VisionSubsystem visionSubsystem,
            DrivebaseSubsystem drivebaseSubsystem,
            ClawSubsystem clawSubsystem,
            LiftSubsystem liftSubsystem) {
        super(
                new Pair<>(
                        visionSubsystem.visionPipeline::left,
                        new AutoBlueAwayFullCycleLeft(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(
                        visionSubsystem.visionPipeline::middle,
                        new AutoBlueAwayFullCycleMiddle(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(
                        visionSubsystem.visionPipeline::right,
                        new AutoBlueAwayFullCycleRight(drivebaseSubsystem, clawSubsystem, liftSubsystem)));
    }
}
