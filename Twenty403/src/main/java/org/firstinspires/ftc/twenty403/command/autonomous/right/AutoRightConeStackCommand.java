package org.firstinspires.ftc.twenty403.command.autonomous.right;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;

import com.technototes.library.command.SequentialCommandGroup;

public class AutoRightConeStackCommand extends SequentialCommandGroup {
    public AutoRightConeStackCommand(Robot r) {
        super(
                /*new TrajectorySequenceCommand(drive, Robot.Trajectories.RIGHT_STACK)
                .alongWith(new ConeReadyToIntakeCommand(cone)),*/
                new ClawCloseCommand(r.clawSubsystem),
                /*new TrajectorySequenceCommand(drive, Robot.Trajectories.HIGH_JUNCTION_RIGHT)
                .alongWith(new ConeReadyToScoreCommand(cone)),*/
                new ClawOpenCommand(r.clawSubsystem));
    }
}
