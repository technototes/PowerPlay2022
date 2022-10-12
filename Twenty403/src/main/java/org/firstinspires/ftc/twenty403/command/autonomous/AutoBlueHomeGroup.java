package org.firstinspires.ftc.twenty403.command.autonomous;

import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoBlueHomeGroup extends SequentialCommandGroup {
    public AutoBlueHomeGroup(
            MecanumDrivebaseSubsystem drive, ClawSubsystem claw, LiftSubsystem lift) {
        super(
                /*new TrajectorySequenceCommand(drive, Robot.Trajectories.BLUE_HIGH_JUNCTION_HOME)
                .alongWith(new ConeReadyToScoreCommand(cone)),*/
                new ClawOpenCommand(claw),
                new AutoBlueHomeConeStackCommand(drive, claw, lift),
                new AutoBlueHomeConeStackCommand(drive, claw, lift),
                new AutoBlueHomeConeStackCommand(drive, claw, lift),
                new AutoBlueHomeConeStackCommand(drive, claw, lift)
                /*new TrajectorySequenceCommand(
                        drive,
                        Robot.Trajectories.BLUE_PARK_LOCATION_HOME)/*
                /*Placeholder for what we're doing for parking*/
                );
    }
}
