package org.firstinspires.ftc.twenty403.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;

public class AutoLeftCycleLeft extends SequentialCommandGroup {

    public AutoLeftCycleLeft(Robot r) {
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
                        AutoConstants.Left.E_JUNCTION_TO_LEFT_PARK
                    )
                        .alongWith(new LiftCollectCommand(r.liftSubsystem))
                )
        );
    }
}
