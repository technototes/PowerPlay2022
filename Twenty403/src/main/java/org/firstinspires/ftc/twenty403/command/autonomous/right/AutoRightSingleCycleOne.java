package org.firstinspires.ftc.twenty403.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoRightSingleCycleOne extends SequentialCommandGroup {

    public AutoRightSingleCycleOne(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.W_JUNCTION_TO_STACK_ONE
            )
                .alongWith(
                    new SequentialCommandGroup(
                        new WaitCommand(0.4),
                        r.liftSubsystem.collectCommand
                    )
                ),
            r.clawSubsystem.closeCommand,
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.STACK_TO_W_JUNCTION_ONE
            )
                .alongWith(r.liftSubsystem.highCommand),
                r.clawSubsystem.openCommand
        );
    }
}
