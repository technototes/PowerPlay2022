package org.firstinspires.ftc.edmundbot.command.autonomous.right;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.edmundbot.Robot;

public class AutoRightParkingSelectionCycleCommand extends ChoiceCommand {

    public AutoRightParkingSelectionCycleCommand(Robot r) {
        super(
            new Pair<>(r.visionSystem.visionPipeline::left, new AutoRightCycleLeft(r)),
            new Pair<>(r.visionSystem.visionPipeline::middle, new AutoRightCycleMiddle(r)),
            new Pair<>(r.visionSystem.visionPipeline::right, new AutoRightCycleRight(r))
        );
    }
}
