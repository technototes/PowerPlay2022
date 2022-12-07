package org.firstinspires.ftc.twenty403.command.autonomous.right;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.Robot;

import com.technototes.library.command.ChoiceCommand;

public class AutoRightParkingSelectionCycleCommand extends ChoiceCommand {
    public AutoRightParkingSelectionCycleCommand(Robot r) {
        super(
                new Pair<>(r.visionSystem.visionPipeline::left, new AutoRightCycleLeft(r)),
                new Pair<>(r.visionSystem.visionPipeline::middle, new AutoRightCycleMiddle(r)),
                new Pair<>(r.visionSystem.visionPipeline::right, new AutoRightCycleRight(r)));
    }
}
