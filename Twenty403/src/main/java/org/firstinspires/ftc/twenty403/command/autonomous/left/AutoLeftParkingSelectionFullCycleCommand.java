package org.firstinspires.ftc.twenty403.command.autonomous.left;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

import java.util.function.DoubleSupplier;

public class AutoLeftParkingSelectionFullCycleCommand extends ChoiceCommand {

    public AutoLeftParkingSelectionFullCycleCommand(
            Robot robot,
            DoubleSupplier currOpModeRunTime
    ) {
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
