package org.firstinspires.ftc.twenty403.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoBlueHomeGroup extends SequentialCommandGroup {
    public AutoBlueHomeGroup(MecanumDrivebaseSubsystem drive /*, LiftSubsystem lift, ClawSubsystem claw*/) {
        super(
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.START_TO_E_JUNCTION)
                //                .alongWith(new ConeReadyToScoreCommand(cone)),
                // new ClawOpenCommand(claw),
                //                new AutoBlueHomeConeStackCommand(drive, lift, claw),
                //                new AutoBlueHomeConeStackCommand(drive, lift, claw),
                //                new AutoBlueHomeConeStackCommand(drive, lift, claw),
                //                new AutoBlueHomeConeStackCommand(drive, lift, claw)
                /*new TrajectorySequenceCommand(
                        drive,
                        Robot.Trajectories.BLUE_PARK_LOCATION_HOME)/*
                /*Placeholder for what we're doing for parking*/
                );
    }
}
