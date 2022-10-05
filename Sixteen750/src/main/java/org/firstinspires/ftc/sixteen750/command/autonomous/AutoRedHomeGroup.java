package org.firstinspires.ftc.sixteen750.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.subsystem.drivebase.DrivebaseSubsystem;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

public class AutoRedHomeGroup extends SequentialCommandGroup {
    public AutoRedHomeGroup(DrivebaseSubsystem drive, ClawSubsystem claw, ConeSubsystem cone, LiftSubsystem lift) {
        super(
                new TrajectorySequenceCommand(drive, HIGHJUNCTION_HOME).alongwith(cone.readyToScoreHigh),
                new ClawOpenCommand(claw),
                new ScoreFromStackRedHome(drive, cone, lift, claw),
                new ScoreFromStackRedHome(drive, cone, lift, claw),
                new ScoreFromStackRedHome(drive, cone, lift, claw),
                new ScoreFromStackRedHome(drive, cone, lift, claw),
                new TrajectorySequenceCommand(PARK_HOME)
        );
    }
}
