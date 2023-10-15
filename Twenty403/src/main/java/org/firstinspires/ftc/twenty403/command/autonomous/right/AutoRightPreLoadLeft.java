package org.firstinspires.ftc.twenty403.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoRightPreLoadLeft extends SequentialCommandGroup {

    public AutoRightPreLoadLeft(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.START_TO_W_JUNCTION
            )
                .alongWith(r.liftSubsystem.highCommand),
            r.clawSubsystem.openCommand,
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.W_JUNCTION_TO_LEFT_PARK
            )
                .alongWith(
                    new SequentialCommandGroup(
                        new WaitCommand(0.6),
                        r.liftSubsystem.intakeCommand
                    )
                )
        );
    }
}
