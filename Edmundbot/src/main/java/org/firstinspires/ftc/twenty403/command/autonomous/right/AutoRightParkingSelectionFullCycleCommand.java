package org.firstinspires.ftc.twenty403.command.autonomous.right;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;
import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoRightParkingSelectionFullCycleCommand extends ChoiceCommand {

    public AutoRightParkingSelectionFullCycleCommand(
        Robot robot,
        DoubleSupplier currOpModeRunTime
    ) {
        super(
            new Pair<>(
                robot.visionSystem.visionPipeline::left,
                new AutoRightFullCycle(
                    robot,
                    AutoConstants.Right.W_JUNCTION_TO_LEFT_PARK,
                    currOpModeRunTime
                )
            ),
            new Pair<>(
                robot.visionSystem.visionPipeline::middle,
                new AutoRightFullCycle(
                    robot,
                    AutoConstants.Right.W_JUNCTION_TO_MIDDLE_PARK,
                    currOpModeRunTime
                )
            ),
            new Pair<>(
                robot.visionSystem.visionPipeline::right,
                new AutoRightFullCycle(
                    robot,
                    AutoConstants.Right.W_JUNCTION_TO_RIGHT_PARK,
                    currOpModeRunTime
                )
            )
        );
    }
}
