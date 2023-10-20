package org.firstinspires.ftc.twenty403.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.Commands;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoRightPreLoadMiddle extends SequentialCommandGroup {

    public AutoRightPreLoadMiddle(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.START_TO_W_JUNCTION
            )
                .alongWith(Commands.Lift.highJunction(r.liftSubsystem)),
            Commands.Claw.open(r.clawSubsystem),
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.W_JUNCTION_TO_MIDDLE_PARK
            )
                .alongWith(
                    new SequentialCommandGroup(
                        new WaitCommand(0.6),
                        Commands.Lift.intake(r.liftSubsystem)
                    )
                )
        );
    }
}
