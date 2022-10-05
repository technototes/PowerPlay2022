package org.firstinspires.ftc.sixteen750.command.autonomous;

import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.cone.ConeReadyToIntakeCommand;
import org.firstinspires.ftc.sixteen750.command.cone.ConeReadyToScoreHigh;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.subsystem.drivebase.DrivebaseSubsystem;
import com.technototes.path.command.TrajectorySequenceCommand;

public class ScoreFromStackRedHome extends SequentialCommandGroup {
    public ScoreFromStackRedHome(DrivebaseSubsystem drive, ConeSubsystem cone, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                new TrajectorySequenceCommand(drive, Robot.Trajectories.CONESTACK_HOME)
                        .alongwith(new ConeReadyToIntakeCommand(cone)),
                new ClawCloseCommand(claw),
                new TrajectorySequenceCommand(drive, Robot.Trajectories.HIGH_JUNCTION_HOME)
                        .alongwith(new ConeReadyToScoreHigh(cone)),
                new ClawOpenCommand(claw));
    }
}
