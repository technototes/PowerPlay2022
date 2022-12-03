package org.firstinspires.ftc.twenty403.command.autonomous.Left;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.technototes.library.command.ChoiceCommand;

public class AutoBlueAwayParkingSelectionFullCycleCommand extends ChoiceCommand {
    public AutoBlueAwayParkingSelectionFullCycleCommand(
            VisionSubsystem visionSubsystem,
            Robot robot) {
        super(
                new Pair<>(
                        visionSubsystem.visionPipeline::left,
                        new AutoBlueAwayFullCycleLeft(robot)),
                new Pair<>(
                        visionSubsystem.visionPipeline::middle,
                        new AutoBlueAwayFullCycleMiddle(robot)),
                new Pair<>(
                        visionSubsystem.visionPipeline::right,
                        new AutoBlueAwayFullCycleRight(robot)));
    }
}
