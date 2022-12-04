package org.firstinspires.ftc.twenty403.command.autonomous.right;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoRightGroup extends SequentialCommandGroup {
    public AutoRightGroup(MecanumDrivebaseSubsystem drive /*, LiftSubsystem lift, ClawSubsystem claw*/) {
        super(
                // new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.START_TO_E_JUNCTION),
                CommandScheduler.getInstance()::terminateOpMode
                //                .alongWith(new ConeReadyToScoreCommand(cone)),
                // new ClawOpenCommand(claw),
                //                new AutoRightConeStackCommand(drive, lift, claw),
                //                new AutoRightConeStackCommand(drive, lift, claw),
                //                new AutoRightConeStackCommand(drive, lift, claw),
                //                new AutoRightConeStackCommand(drive, lift, claw)
                /*new TrajectorySequenceCommand(
                        drive,
                        Robot.Trajectories.BLUE_PARK_LOCATION_HOME)/*
                /*Placeholder for what we're doing for parking*/
                );
    }
}
