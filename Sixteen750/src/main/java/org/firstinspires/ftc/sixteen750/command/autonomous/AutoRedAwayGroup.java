package org.firstinspires.ftc.sixteen750.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.subsystem.drivebase.DrivebaseSubsystem;
import com.technototes.path.trajectorysequence.TrajectorySequence;

import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

public class AutoRedAwayGroup extends SequentialCommandGroup {
    public AutoRedAwayGroup(DrivebaseSubsystem drive, ClawSubsystem claw, ConeSubsystem cone, LiftSubsystem lift) {
        super(
                new TrajectorySequence(drive, HIGHJUNCTION_AWAY).alongwith(cone.readyToScoreHigh),
                new ClawOpenCommand(claw),
                new ScoreFromStackRedAway(drive, cone, lift, claw),
                new ScoreFromStackRedAway(drive, cone, lift, claw),
                new ScoreFromStackRedAway(drive, cone, lift, claw),
                new ScoreFromStackRedAway(drive, cone, lift, claw),
                new TrajectorySequence(drive, PARK_AWAY)
        );
    }
}
