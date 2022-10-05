package org.firstinspires.ftc.sixteen750.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.trajectorysequence.TrajectorySequence;

import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

public class AutoRedHomeGroup extends SequentialCommandGroup {
    public AutoRedHomeGroup(MecanumDrive drive, ClawSubsystem claw, ConeSubsystem cone, LiftSubsystem lift) {
        super(
                new TrajectorySequence(drive, HIGHJUNCTION_HOME).alongwith(cone.readyToScoreHigh),
                new ClawOpenCommand(claw),
                new ScoreFromStackRedHome(drive, cone, lift, claw),
                new ScoreFromStackRedHome(drive, cone, lift, claw),
                new ScoreFromStackRedHome(drive, cone, lift, claw),
                new ScoreFromStackRedHome(drive, cone, lift, claw),
                new TrajectorySequence(PARK_HOME)
        );
    }
}
}
