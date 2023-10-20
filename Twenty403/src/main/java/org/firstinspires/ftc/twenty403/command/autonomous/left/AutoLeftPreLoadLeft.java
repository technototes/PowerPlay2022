package org.firstinspires.ftc.twenty403.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.Commands;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoLeftPreLoadLeft extends SequentialCommandGroup {

    public AutoLeftPreLoadLeft(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Left.START_TO_E_JUNCTION
            )
                .alongWith(Commands.Lift.highJunction(r.liftSubsystem)),
            Commands.Claw.open(r.clawSubsystem),
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Left.E_JUNCTION_TO_LEFT_PARK
            )
                .alongWith(Commands.Lift.collect(r.liftSubsystem))
        );
    }
}
