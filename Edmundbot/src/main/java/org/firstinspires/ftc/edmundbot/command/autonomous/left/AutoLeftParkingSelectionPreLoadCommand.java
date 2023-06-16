package org.firstinspires.ftc.edmundbot.command.autonomous.left;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.edmundbot.Robot;

public class AutoLeftParkingSelectionPreLoadCommand extends ChoiceCommand {

    public AutoLeftParkingSelectionPreLoadCommand(Robot r) {
        super(
            new Pair<>(r.visionSystem.visionPipeline::left, new AutoLeftPreLoadLeft(r)),
            new Pair<>(r.visionSystem.visionPipeline::middle, new AutoLeftPreLoadMiddle(r)),
            new Pair<>(r.visionSystem.visionPipeline::right, new AutoLeftPreLoadRight(r))
        );
    }
}
