package org.firstinspires.ftc.twenty403.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoRightSingleCycleThree extends SequentialCommandGroup {

    public AutoRightSingleCycleThree(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.W_JUNCTION_TO_STACK_THREE
            )
                .alongWith(r.liftSubsystem.collectCommand),
            r.clawSubsystem.closeCommand,
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.STACK_TO_W_JUNCTION_THREE
            )
                .alongWith(r.liftSubsystem.highCommand),
                r.clawSubsystem.openCommand
        );
    }
}
