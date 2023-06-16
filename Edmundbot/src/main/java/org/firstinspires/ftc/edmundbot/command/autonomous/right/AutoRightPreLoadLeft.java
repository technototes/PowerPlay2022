package org.firstinspires.ftc.edmundbot.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.edmundbot.Robot;
import org.firstinspires.ftc.edmundbot.command.autonomous.AutoConstants;
import org.firstinspires.ftc.edmundbot.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.edmundbot.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.edmundbot.command.lift.LiftIntakeCommand;

public class AutoRightPreLoadLeft extends SequentialCommandGroup {

    public AutoRightPreLoadLeft(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.START_TO_W_JUNCTION
            )
                .alongWith(new LiftHighJunctionCommand(r.liftSubsystem)),
            new ClawOpenCommand(r.clawSubsystem),
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.W_JUNCTION_TO_LEFT_PARK
            )
                .alongWith(
                    new SequentialCommandGroup(
                        new WaitCommand(0.6),
                        new LiftIntakeCommand(r.liftSubsystem)
                    )
                )
        );
    }
}
