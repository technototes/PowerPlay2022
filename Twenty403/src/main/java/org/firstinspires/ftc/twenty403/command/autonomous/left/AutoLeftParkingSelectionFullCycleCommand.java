package org.firstinspires.ftc.twenty403.command.autonomous.left;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;
import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoLeftParkingSelectionFullCycleCommand extends ChoiceCommand {

    public AutoLeftParkingSelectionFullCycleCommand(Robot robot, DoubleSupplier currOpModeRunTime) {
        super(
            new Pair<>(
                robot.visionSystem::left,
                new AutoLeftFullCycle(
                    robot,
                    AutoConstants.Left.E_JUNCTION_TO_LEFT_PARK,
                    currOpModeRunTime
                )
            ),
            new Pair<>(
                robot.visionSystem::middle,
                new AutoLeftFullCycle(
                    robot,
                    AutoConstants.Left.E_JUNCTION_TO_MIDDLE_PARK,
                    currOpModeRunTime
                )
            ),
            new Pair<>(
                robot.visionSystem::right,
                new AutoLeftFullCycle(
                    robot,
                    AutoConstants.Left.E_JUNCTION_TO_RIGHT_PARK,
                    currOpModeRunTime
                )
            )
        );
    }
}
