package org.firstinspires.ftc.twenty403.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.Commands;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoLeftJunctionStackCycle extends SequentialCommandGroup {

    public AutoLeftJunctionStackCycle(Robot robot) {
        super(
            new TrajectorySequenceCommand(
                robot.drivebaseSubsystem,
                AutoConstants.Left.E_JUNCTION_TO_STACK
            )
                .alongWith(Commands.Lift.collect(robot.liftSubsystem)),
            Commands.Claw.close(robot.clawSubsystem),
            new TrajectorySequenceCommand(
                robot.drivebaseSubsystem,
                AutoConstants.Left.STACK_TO_E_JUNCTION
            )
                .alongWith(Commands.Lift.highJunction(robot.liftSubsystem)),
            Commands.Claw.open(robot.clawSubsystem)
        );
    }
}
