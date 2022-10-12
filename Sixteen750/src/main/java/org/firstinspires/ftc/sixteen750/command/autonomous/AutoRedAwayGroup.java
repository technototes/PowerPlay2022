package org.firstinspires.ftc.sixteen750.command.autonomous;

import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ConeReadyToScoreHigh;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoRedAwayGroup extends SequentialCommandGroup {
    public AutoRedAwayGroup(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                new TrajectorySequenceCommand(drive, Robot.Trajectories.RED_HIGH_JUNCTION_AWAY)
                        .alongWith(new ConeReadyToScoreHigh(lift, claw)),
                new ClawOpenCommand(claw),
                new AutoRedAwayConeStackCommand(drive, lift, claw),
                new AutoRedAwayConeStackCommand(drive, lift, claw),
                new AutoRedAwayConeStackCommand(drive, lift, claw),
                new AutoRedAwayConeStackCommand(drive, lift, claw),
                // TODO: This should call the vision choice command to decide where to park
                new TrajectorySequenceCommand(drive, Robot.Trajectories.RED_PARK_LOCATION_AWAY));
    }
}
