package org.firstinspires.ftc.twenty403.command.autonomous.left;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.Robot;

import com.technototes.library.command.ChoiceCommand;

public class AutoLeftParkingSelectionJustParkCommand extends ChoiceCommand {
    public AutoLeftParkingSelectionJustParkCommand(Robot r) {
        super(
                new Pair<>(r.visionSystem.visionPipeline::left, new AutoLeftParkLeft(r)),
                new Pair<>(r.visionSystem.visionPipeline::middle, new AutoLeftParkMiddle(r)),
                new Pair<>(r.visionSystem.visionPipeline::right, new AutoLeftParkRight(r)));
    }
}
