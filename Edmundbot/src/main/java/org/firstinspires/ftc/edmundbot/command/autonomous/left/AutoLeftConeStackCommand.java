package org.firstinspires.ftc.edmundbot.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.edmundbot.Robot;
import org.firstinspires.ftc.edmundbot.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.edmundbot.command.claw.ClawOpenCommand;

public class AutoLeftConeStackCommand extends SequentialCommandGroup {

    public AutoLeftConeStackCommand(Robot r) {
        super(
            /*new TrajectorySequenceCommand(drive, Robot.Trajectories.LEFT_STACK)
                .alongWith(new ConeReadyToIntakeCommand(cone)),*/
            new ClawCloseCommand(r.clawSubsystem),
            /*new TrajectorySequenceCommand(drive, Robot.Trajectories.HIGH_JUNCTION_LEFT)
                .alongWith(new ConeReadyToScoreCommand(cone)),*/
            new ClawOpenCommand(r.clawSubsystem)
        );
    }
}
