package org.firstinspires.ftc.twenty403.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.subsystem.drivebase.DrivebaseSubsystem;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.compound.ConeReadyScoreHigh;
import org.firstinspires.ftc.twenty403.command.compound.ConeReadyScoreHighCommand;
import org.firstinspires.ftc.twenty403.command.compound.ConeReadyToIntakeCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class AutoRedHomeConeStackCommand extends SequentialCommandGroup {
    public AutoRedHomeConeStackCommand(DrivebaseSubsystem drive, ConeSubsystem cone, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                new TrajectorySequenceCommand(drive, Robot.Trajectories.CONESTACK_HOME)
                        .alongwith(new ConeReadyToIntakeCommand(cone)),
                new ClawCloseCommand(claw),
                new TrajectorySequenceCommand(drive, Robot.Trajectories.HIGH_JUNCTION_HOME)
                        .alongwith(new ConeReadyScoreHigh(cone)),
                new ClawOpenCommand(claw));
    }
}
