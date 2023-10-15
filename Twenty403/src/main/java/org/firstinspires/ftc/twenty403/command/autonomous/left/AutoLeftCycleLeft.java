package org.firstinspires.ftc.twenty403.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoLeftCycleLeft extends SequentialCommandGroup {

    public AutoLeftCycleLeft(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Left.START_TO_E_JUNCTION
            )
                .alongWith(
                    r.liftSubsystem.highCommand,
                    r.clawSubsystem.openCommand,
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.Left.E_JUNCTION_TO_STACK
                    )
                        .alongWith(r.liftSubsystem.collectCommand),
                    r.clawSubsystem.closeCommand,
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.Left.STACK_TO_E_JUNCTION
                    )
                        .alongWith(r.liftSubsystem.highCommand),
                    r.clawSubsystem.openCommand,
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.Left.E_JUNCTION_TO_LEFT_PARK
                    )
                        .alongWith(r.liftSubsystem.collectCommand)
                )
        );
    }
}
