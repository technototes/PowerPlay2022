package org.firstinspires.ftc.twenty403.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.subsystem.drivebase.DrivebaseSubsystem;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.compound.ConeReadyScoreHigh;
import org.firstinspires.ftc.twenty403.command.compound.ConeReadyScoreHighCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class AutoRedHomeGroup extends SequentialCommandGroup {
    public AutoRedHomeGroup(DrivebaseSubsystem drive, ClawSubsystem claw, ConeSubsystem cone, LiftSubsystem lift) {
        super(
                new TrajectorySequenceCommand(drive, Robot.Trajectories.HIGH_JUNCTION_HOME)
                        .alongwith(new ConeReadyScoreHigh(cone)),
                new ClawOpenCommand(claw),
                new AutoRedHomeConeStackCommand(drive, cone, lift, claw),
                new AutoRedHomeConeStackCommand(drive, cone, lift, claw),
                new AutoRedHomeConeStackCommand(drive, cone, lift, claw),
                new AutoRedHomeConeStackCommand(drive, cone, lift, claw),
                new TrajectorySequenceCommand(Robot.Trajectories.PARK_HOME));
    }
}
