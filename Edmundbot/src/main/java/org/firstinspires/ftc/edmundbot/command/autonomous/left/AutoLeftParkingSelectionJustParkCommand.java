package org.firstinspires.ftc.edmundbot.command.autonomous.left;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.edmundbot.Robot;

public class AutoLeftParkingSelectionJustParkCommand extends ChoiceCommand {

    public AutoLeftParkingSelectionJustParkCommand(Robot r) {
        super(
            new Pair<>(r.visionSystem.visionPipeline::left, new AutoLeftParkLeft(r)),
            new Pair<>(r.visionSystem.visionPipeline::middle, new AutoLeftParkMiddle(r)),
            new Pair<>(r.visionSystem.visionPipeline::right, new AutoLeftParkRight(r))
        );
    }
}
