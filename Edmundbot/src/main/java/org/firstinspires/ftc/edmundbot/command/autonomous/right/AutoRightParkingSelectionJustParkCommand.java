package org.firstinspires.ftc.edmundbot.command.autonomous.right;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.edmundbot.Robot;

public class AutoRightParkingSelectionJustParkCommand extends ChoiceCommand {

    public AutoRightParkingSelectionJustParkCommand(Robot r) {
        super(
            new Pair<>(r.visionSystem.visionPipeline::left, new AutoRightParkingLeft(r)),
            new Pair<>(r.visionSystem.visionPipeline::middle, new AutoRightParkingMiddle(r)),
            new Pair<>(r.visionSystem.visionPipeline::right, new AutoRightParkingRight(r))
        );
    }
}
