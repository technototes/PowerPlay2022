package org.firstinspires.ftc.twenty403.command.autonomous.left;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.Robot;

import com.technototes.library.command.ChoiceCommand;

public class AutoLeftParkingSelectionFullCycleCommand extends ChoiceCommand {
    public AutoLeftParkingSelectionFullCycleCommand(Robot r) {
        super(
                new Pair<>(r.visionSystem.visionPipeline::left, new AutoLeftFullCycleLeft(r)),
                new Pair<>(r.visionSystem.visionPipeline::middle, new AutoLeftFullCycleMiddle(r)),
                new Pair<>(r.visionSystem.visionPipeline::right, new AutoLeftFullCycleRight(r)));
    }
}
