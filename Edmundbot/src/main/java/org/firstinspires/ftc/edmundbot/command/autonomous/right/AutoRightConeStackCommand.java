package org.firstinspires.ftc.edmundbot.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.edmundbot.Robot;
import org.firstinspires.ftc.edmundbot.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.edmundbot.command.claw.ClawOpenCommand;

public class AutoRightConeStackCommand extends SequentialCommandGroup {

    public AutoRightConeStackCommand(Robot r) {
        super(
            /*new TrajectorySequenceCommand(drive, Robot.Trajectories.RIGHT_STACK)
                .alongWith(new ConeReadyToIntakeCommand(cone)),*/
            new ClawCloseCommand(r.clawSubsystem),
            /*new TrajectorySequenceCommand(drive, Robot.Trajectories.HIGH_JUNCTION_RIGHT)
                .alongWith(new ConeReadyToScoreCommand(cone)),*/
            new ClawOpenCommand(r.clawSubsystem)
        );
    }
}
