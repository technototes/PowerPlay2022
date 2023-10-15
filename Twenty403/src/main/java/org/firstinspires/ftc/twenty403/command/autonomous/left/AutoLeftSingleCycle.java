package org.firstinspires.ftc.twenty403.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoLeftSingleCycle extends SequentialCommandGroup {

    public AutoLeftSingleCycle(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Left.E_JUNCTION_TO_STACK
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
                AutoConstants.Left.STACK_TO_E_JUNCTION
            )
                .alongWith(r.liftSubsystem.highCommand),
            r.clawSubsystem.openCommand
        );
    }
}
