package org.firstinspires.ftc.sixteen750.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;
import com.technototes.path.trajectorysequence.TrajectorySequence;

import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.cone.ConeReadyToIntakeCommand;
import org.firstinspires.ftc.sixteen750.command.cone.ConeReadyToScoreCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

public class ScoreFromStackRedAway extends SequentialCommandGroup {
    public ScoreFromStackRedAway(MecanumDrivebaseSubsystem drive, ConeSubsystem cone, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                new TrajectorySequence(drive, CONESTACK_HOME).alongwith(new ConeReadyToIntakeCommand(cone)),
                new ClawCloseCommand(claw),
                new TrajectorySequence(drive, HIGHJUNCTION_HOME).alongwith(new ConeReadyToScoreCommand()),
                new ClawOpenCommand(claw)

        );
    }

}
