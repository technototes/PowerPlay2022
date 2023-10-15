package org.firstinspires.ftc.twenty403.command.autonomous.right;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;
import org.firstinspires.ftc.twenty403.Robot;

public class AutoRightParkingSelectionJustParkCommand extends ChoiceCommand {

    public AutoRightParkingSelectionJustParkCommand(Robot r) {
        super(
            new Pair<>(r.visionSystem::left, new AutoRightParkingLeft(r)),
            new Pair<>(r.visionSystem::middle, new AutoRightParkingMiddle(r)),
            new Pair<>(r.visionSystem::right, new AutoRightParkingRight(r))
        );
    }
}
