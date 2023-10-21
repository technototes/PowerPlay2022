package org.firstinspires.ftc.twenty403.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.Commands;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoRightCycleRight extends SequentialCommandGroup {

    public AutoRightCycleRight(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.START_TO_W_JUNCTION
            )
                .alongWith(Commands.Lift.highJunction(r.liftSubsystem)),
            Commands.Claw.open(r.clawSubsystem),
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.W_JUNCTION_TO_STACK_ONE
            )
                .alongWith(Commands.Lift.collect(r.liftSubsystem)),
            Commands.Claw.close(r.clawSubsystem),
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.STACK_TO_W_JUNCTION_ONE
            )
                .alongWith(Commands.Lift.highJunction(r.liftSubsystem)),
            Commands.Claw.open(r.clawSubsystem),
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.W_JUNCTION_TO_RIGHT_PARK
            )
                .alongWith(Commands.Lift.collect(r.liftSubsystem))
        );
    }
}
