package org.firstinspires.ftc.twenty403.command.autonomous.left;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.technototes.library.command.ChoiceCommand;

public class AutoLeftParkingSelectionFullCycleCommand extends ChoiceCommand {
    public AutoLeftParkingSelectionFullCycleCommand(VisionSubsystem visionSubsystem, Robot robot) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new AutoLeftFullCycleLeft(robot)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new AutoLeftFullCycleMiddle(robot)),
                new Pair<>(visionSubsystem.visionPipeline::right, new AutoLeftFullCycleRight(robot)));
    }
}
