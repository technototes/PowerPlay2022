package org.firstinspires.ftc.sixteen750.command.autonomous.blue_away;

import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoBlueAwayGroup extends SequentialCommandGroup {
    public AutoBlueAwayGroup(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                /*new TrajectorySequenceCommand(drive, Robot.Trajectories.BLUE_HIGH_JUNCTION_AWAY)
                .alongWith(new ConeReadyToScoreCommand(cone)),*/
                new ClawOpenCommand(claw),
                new AutoBlueAwayConeStackCommand(drive, lift, claw),
                new AutoBlueAwayConeStackCommand(drive, lift, claw),
                new AutoBlueAwayConeStackCommand(drive, lift, claw),
                new AutoBlueAwayConeStackCommand(drive, lift, claw)
                /*new TrajectorySequenceCommand(
                drive,
                Robot.Trajectories.BLUE_PARK_LOCATION_AWAY) */
                /*Placeholder for what we're doing for parking*/ );
    }
}
