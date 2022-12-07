package org.firstinspires.ftc.twenty403.command.autonomous.right;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.Robot;

import com.technototes.library.command.ChoiceCommand;

public class AutoRightParkingSelectionJustParkCommand extends ChoiceCommand {
    public AutoRightParkingSelectionJustParkCommand(Robot r) {
        super(
                new Pair<>(r.visionSystem.visionPipeline::left, new AutoRightParkingLeft(r)),
                new Pair<>(r.visionSystem.visionPipeline::middle, new AutoRightParkingMiddle(r)),
                new Pair<>(r.visionSystem.visionPipeline::right, new AutoRightParkingRight(r)));
    }
}
