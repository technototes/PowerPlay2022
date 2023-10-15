package org.firstinspires.ftc.twenty403.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoRightCycleLeft extends SequentialCommandGroup {

    public AutoRightCycleLeft(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.START_TO_W_JUNCTION
            )
                .alongWith(
                    r.liftSubsystem.highCommand,
                    r.clawSubsystem.openCommand,
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.Right.W_JUNCTION_TO_STACK_ONE
                    )
                        .alongWith(r.liftSubsystem.collectCommand),
                    r.clawSubsystem.closeCommand,
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.Right.STACK_TO_W_JUNCTION_ONE
                    )
                        .alongWith(r.liftSubsystem.highCommand),
                    r.clawSubsystem.openCommand,
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.Right.W_JUNCTION_TO_LEFT_PARK
                    )
                        .alongWith(r.liftSubsystem.collectCommand)
                )
        );
    }
}
