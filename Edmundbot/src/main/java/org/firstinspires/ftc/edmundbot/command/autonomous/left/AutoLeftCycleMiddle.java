package org.firstinspires.ftc.edmundbot.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.edmundbot.Robot;
import org.firstinspires.ftc.edmundbot.command.autonomous.AutoConstants;
import org.firstinspires.ftc.edmundbot.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.edmundbot.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.edmundbot.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.edmundbot.command.lift.LiftHighJunctionCommand;

public class AutoLeftCycleMiddle extends SequentialCommandGroup {

    public AutoLeftCycleMiddle(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Left.START_TO_E_JUNCTION
            )
                .alongWith(
                    new LiftHighJunctionCommand(r.liftSubsystem),
                    new ClawOpenCommand(r.clawSubsystem),
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.Left.E_JUNCTION_TO_STACK
                    )
                        .alongWith(new LiftCollectCommand(r.liftSubsystem)),
                    new ClawCloseCommand(r.clawSubsystem),
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.Left.STACK_TO_E_JUNCTION
                    )
                        .alongWith(new LiftHighJunctionCommand(r.liftSubsystem)),
                    new ClawOpenCommand(r.clawSubsystem),
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.Left.E_JUNCTION_TO_MIDDLE_PARK
                    )
                        .alongWith(new LiftCollectCommand(r.liftSubsystem))
                )
        );
    }
}
