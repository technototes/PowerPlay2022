package org.firstinspires.ftc.twenty403.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.Commands;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoLeftSingleCycleTwo extends SequentialCommandGroup {

    public AutoLeftSingleCycleTwo(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Left.E_JUNCTION_TO_STACK_TWO
            )
                .alongWith(
                    new SequentialCommandGroup(
                        new WaitCommand(0.4),
                        Commands.Lift.collect(r.liftSubsystem)
                    )
                ),
            Commands.Claw.close(r.clawSubsystem),
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Left.STACK_TO_E_JUNCTION_TWO
            )
                .alongWith(Commands.Lift.highJunction(r.liftSubsystem)),
            Commands.Claw.open(r.clawSubsystem)
        );
    }
}
