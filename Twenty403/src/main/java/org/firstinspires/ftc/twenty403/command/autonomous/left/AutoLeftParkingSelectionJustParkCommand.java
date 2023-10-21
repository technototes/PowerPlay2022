package org.firstinspires.ftc.twenty403.command.autonomous.left;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;
import org.firstinspires.ftc.twenty403.Robot;

public class AutoLeftParkingSelectionJustParkCommand extends ChoiceCommand {

    public AutoLeftParkingSelectionJustParkCommand(Robot r) {
        super(
            new Pair<>(r.visionSystem::left, new AutoLeftParkLeft(r)),
            new Pair<>(r.visionSystem::middle, new AutoLeftParkMiddle(r)),
            new Pair<>(r.visionSystem::right, new AutoLeftParkRight(r))
        );
    }
}
