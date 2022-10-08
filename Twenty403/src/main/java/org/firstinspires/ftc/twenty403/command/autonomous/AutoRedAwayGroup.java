package org.firstinspires.ftc.twenty403.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.subsystem.drivebase.DrivebaseSubsystem;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.compound.ConeReadyScoreHigh;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class AutoRedAwayGroup extends SequentialCommandGroup {
    public AutoRedAwayGroup(DrivebaseSubsystem drive, ClawSubsystem claw, ConeSubsystem cone, LiftSubsystem lift) {
        super(
                new TrajectorySequenceCommand(drive, Robot.Trajectories.RED_HIGH_JUNCTION_AWAY)
                        .alongwith(new ConeReadyScoreHigh(cone)),
                new ClawOpenCommand(claw),
                new AutoRedAwayConeStackCommand(drive, cone, lift, claw),
                new AutoRedAwayConeStackCommand(drive, cone, lift, claw),
                new AutoRedAwayConeStackCommand(drive, cone, lift, claw),
                new AutoRedAwayConeStackCommand(drive, cone, lift, claw),
                new TrajectorySequenceCommand(drive, Robot.Trajectories.RED_PARK_LOCATION_AWAY));
    }
}