package org.firstinspires.ftc.twenty403.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.compound.ConeReadyToIntakeCommand;
import org.firstinspires.ftc.twenty403.command.compound.ConeReadyToScoreCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class BlueHomeAutoConeStack extends SequentialCommandGroup {
    public BlueHomeAutoConeStack(
            MecanumDrivebaseSubsystem drive, ClawSubsystem claw, LiftSubsystem lift, ConeSubsystem cone) {
        super(
                new TrajectorySequenceCommand(drive, Robot.Trajectories.BLUE_HOME_STACK)
                        .alongWith(new ConeReadyToIntakeCommand(cone)),
                new ClawCloseCommand()claw),
                new TrajectorySequenceCommand(drive, Robot.Trajectories.BLUE_HIGH_JUNCTION_HOME)
                        .alongWith(new ConeReadyToScoreCommand(cone)),
                new ClawOpenCommand(claw));
    }
}