package org.firstinspires.ftc.twenty403.command.autonomous.Left;

import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

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
