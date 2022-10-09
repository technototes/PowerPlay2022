package org.firstinspires.ftc.sixteen750.command.autonomous;

import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.cone.ConeReadyToScoreHigh;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoRedAwayGroup extends SequentialCommandGroup {
    public AutoRedAwayGroup(
            MecanumDrivebaseSubsystem drive, ClawSubsystem claw, ConeSubsystem cone, LiftSubsystem lift) {
        super(
                new TrajectorySequenceCommand(drive, Robot.Trajectories.RED_HIGH_JUNCTION_AWAY)
                        .alongWith(new ConeReadyToScoreHigh(cone)),
                new ClawOpenCommand(claw),
                new AutoRedAwayConeStackCommand(drive, cone, lift, claw),
                new AutoRedAwayConeStackCommand(drive, cone, lift, claw),
                new AutoRedAwayConeStackCommand(drive, cone, lift, claw),
                new AutoRedAwayConeStackCommand(drive, cone, lift, claw),
                // TODO: This should call the vision choice command to decide where to park
                new TrajectorySequenceCommand(drive, Robot.Trajectories.RED_PARK_LOCATION_AWAY));
    }
}
