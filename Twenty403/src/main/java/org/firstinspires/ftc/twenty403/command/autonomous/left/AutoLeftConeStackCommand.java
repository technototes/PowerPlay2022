package org.firstinspires.ftc.twenty403.command.autonomous.left;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;

import com.technototes.library.command.SequentialCommandGroup;

public class AutoLeftConeStackCommand extends SequentialCommandGroup {
    public AutoLeftConeStackCommand(Robot r) {
        super(
                /*new TrajectorySequenceCommand(drive, Robot.Trajectories.LEFT_STACK)
                .alongWith(new ConeReadyToIntakeCommand(cone)),*/
                new ClawCloseCommand(r.clawSubsystem),
                /*new TrajectorySequenceCommand(drive, Robot.Trajectories.HIGH_JUNCTION_LEFT)
                .alongWith(new ConeReadyToScoreCommand(cone)),*/
                new ClawOpenCommand(r.clawSubsystem));
    }
}
