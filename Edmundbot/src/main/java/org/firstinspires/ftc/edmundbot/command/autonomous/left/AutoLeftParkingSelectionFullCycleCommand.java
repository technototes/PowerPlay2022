package org.firstinspires.ftc.edmundbot.command.autonomous.left;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;
import java.util.function.DoubleSupplier;

import org.firstinspires.ftc.edmundbot.Robot;
import org.firstinspires.ftc.edmundbot.command.autonomous.AutoConstants;

public class AutoLeftParkingSelectionFullCycleCommand extends ChoiceCommand {

    public AutoLeftParkingSelectionFullCycleCommand(Robot robot, DoubleSupplier currOpModeRunTime) {
        super(
            new Pair<>(
                robot.visionSystem.visionPipeline::left,
                new AutoLeftFullCycle(
                    robot,
                    AutoConstants.Left.E_JUNCTION_TO_LEFT_PARK,
                    currOpModeRunTime
                )
            ),
            new Pair<>(
                robot.visionSystem.visionPipeline::middle,
                new AutoLeftFullCycle(
                    robot,
                    AutoConstants.Left.E_JUNCTION_TO_MIDDLE_PARK,
                    currOpModeRunTime
                )
            ),
            new Pair<>(
                robot.visionSystem.visionPipeline::right,
                new AutoLeftFullCycle(
                    robot,
                    AutoConstants.Left.E_JUNCTION_TO_RIGHT_PARK,
                    currOpModeRunTime
                )
            )
        );
    }
}
