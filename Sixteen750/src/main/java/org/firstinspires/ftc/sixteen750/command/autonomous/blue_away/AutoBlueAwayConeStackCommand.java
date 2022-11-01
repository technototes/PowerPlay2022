package org.firstinspires.ftc.sixteen750.command.autonomous.blue_away;

import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoBlueAwayConeStackCommand extends SequentialCommandGroup {
    public AutoBlueAwayConeStackCommand(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                /*new TrajectorySequenceCommand(drive, Robot.Trajectories.BLUE_AWAY_STACK)
                .alongWith(new ConeReadyToIntakeCommand(cone)),*/
                new ClawCloseCommand(claw),
                /*new TrajectorySequenceCommand(drive, Robot.Trajectories.BLUE_HIGH_JUNCTION_AWAY)
                .alongWith(new ConeReadyToScoreCommand(cone)),*/
                new ClawOpenCommand(claw));
    }
}
