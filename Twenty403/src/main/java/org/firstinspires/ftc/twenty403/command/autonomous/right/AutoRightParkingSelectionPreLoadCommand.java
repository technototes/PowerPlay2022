package org.firstinspires.ftc.twenty403.command.autonomous.right;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;
import org.firstinspires.ftc.twenty403.Robot;

public class AutoRightParkingSelectionPreLoadCommand extends ChoiceCommand {

    public AutoRightParkingSelectionPreLoadCommand(Robot r) {
        super(
            new Pair<>(r.visionSystem::left, new AutoRightPreLoadLeft(r)),
            new Pair<>(r.visionSystem::middle, new AutoRightPreLoadMiddle(r)),
            new Pair<>(r.visionSystem::right, new AutoRightPreLoadRight(r))
        );
    }
}
