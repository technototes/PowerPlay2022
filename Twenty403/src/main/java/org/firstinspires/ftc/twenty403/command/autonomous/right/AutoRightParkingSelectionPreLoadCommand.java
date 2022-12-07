package org.firstinspires.ftc.twenty403.command.autonomous.right;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.Robot;

import com.technototes.library.command.ChoiceCommand;

public class AutoRightParkingSelectionPreLoadCommand extends ChoiceCommand {
    public AutoRightParkingSelectionPreLoadCommand(Robot r) {
        super(
                new Pair<>(r.visionSystem.visionPipeline::left, new AutoRightPreLoadLeft(r)),
                new Pair<>(r.visionSystem.visionPipeline::middle, new AutoRightPreLoadMiddle(r)),
                new Pair<>(r.visionSystem.visionPipeline::right, new AutoRightPreLoadRight(r)));
    }
}
