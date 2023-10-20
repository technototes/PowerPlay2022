package org.firstinspires.ftc.twenty403.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.Commands;

public class AutoRightConeStackCommand extends SequentialCommandGroup {

    public AutoRightConeStackCommand(Robot r) {
        super(
            /*new TrajectorySequenceCommand(drive, Robot.Trajectories.RIGHT_STACK)
                .alongWith(new ConeReadyToIntakeCommand(cone)),*/
            Commands.Claw.close(r.clawSubsystem),
            /*new TrajectorySequenceCommand(drive, Robot.Trajectories.HIGH_JUNCTION_RIGHT)
                .alongWith(new ConeReadyToScoreCommand(cone)),*/
            Commands.Claw.open(r.clawSubsystem)
        );
    }
}
