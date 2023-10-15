package org.firstinspires.ftc.twenty403.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import org.firstinspires.ftc.twenty403.Robot;

public class AutoRightConeStackCommand extends SequentialCommandGroup {

    public AutoRightConeStackCommand(Robot r) {
        super(
            /*new TrajectorySequenceCommand(drive, Robot.Trajectories.RIGHT_STACK)
                .alongWith(new ConeReadyToIntakeCommand(cone)),*/
            r.clawSubsystem.closeCommand,
            /*new TrajectorySequenceCommand(drive, Robot.Trajectories.HIGH_JUNCTION_RIGHT)
                .alongWith(new ConeReadyToScoreCommand(cone)),*/
            r.clawSubsystem.openCommand
        );
    }
}
