package org.firstinspires.ftc.edmundbot.command.autonomous.right;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.edmundbot.Robot;

public class AutoRightParkingSelectionPreLoadCommand extends ChoiceCommand {

    public AutoRightParkingSelectionPreLoadCommand(Robot r) {
        super(
            new Pair<>(r.visionSystem.visionPipeline::left, new AutoRightPreLoadLeft(r)),
            new Pair<>(r.visionSystem.visionPipeline::middle, new AutoRightPreLoadMiddle(r)),
            new Pair<>(r.visionSystem.visionPipeline::right, new AutoRightPreLoadRight(r))
        );
    }
}
