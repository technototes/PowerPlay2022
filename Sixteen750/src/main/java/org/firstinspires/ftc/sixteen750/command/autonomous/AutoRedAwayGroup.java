package org.firstinspires.ftc.sixteen750.command.autonomous;

import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.subsystem.drivebase.DrivebaseSubsystem;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoRedAwayGroup extends SequentialCommandGroup {
    public AutoRedAwayGroup(DrivebaseSubsystem drive, ClawSubsystem claw, ConeSubsystem cone, LiftSubsystem lift) {
        super(
                new TrajectorySequenceCommand(drive, HIGHJUNCTION_AWAY).alongwith(cone.readyToScoreHigh),
                new ClawOpenCommand(claw),
                new ScoreFromStackRedAway(drive, cone, lift, claw),
                new ScoreFromStackRedAway(drive, cone, lift, claw),
                new ScoreFromStackRedAway(drive, cone, lift, claw),
                new ScoreFromStackRedAway(drive, cone, lift, claw),
                new TrajectorySequenceCommand(drive, PARK_AWAY)
        );
    }
}
