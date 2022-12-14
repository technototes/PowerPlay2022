package org.firstinspires.ftc.twenty403.command.autonomous.left;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;
import org.firstinspires.ftc.twenty403.Robot;

public class AutoLeftParkingSelectionCycleCommand extends ChoiceCommand {

    public AutoLeftParkingSelectionCycleCommand(Robot r) {
        super(
            new Pair<>(r.visionSystem.visionPipeline::left, new AutoLeftCycleLeft(r)),
            new Pair<>(r.visionSystem.visionPipeline::middle, new AutoLeftCycleMiddle(r)),
            new Pair<>(r.visionSystem.visionPipeline::right, new AutoLeftCycleRight(r))
        );
    }
}
