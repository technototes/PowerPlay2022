package org.firstinspires.ftc.twenty403.command.autonomous.left;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;
import org.firstinspires.ftc.twenty403.Robot;

public class AutoLeftParkingSelectionPreLoadCommand extends ChoiceCommand {

    public AutoLeftParkingSelectionPreLoadCommand(Robot r) {
        super(
            new Pair<>(r.visionSystem::left, new AutoLeftPreLoadLeft(r)),
            new Pair<>(r.visionSystem::middle, new AutoLeftPreLoadMiddle(r)),
            new Pair<>(r.visionSystem::right, new AutoLeftPreLoadRight(r))
        );
    }
}
