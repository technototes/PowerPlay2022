package org.firstinspires.ftc.twenty403.command.autonomous;

import com.acmerobotics.dashboard.config.Config;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;


public class AutoTestDrivebaseCommand extends SequentialCommandGroup {
    // public static ConfigurablePose TEST_START = new ConfigurablePose(0, 0, 0);
    // public static ConfigurablePose TEST_END = new ConfigurablePose(0, 10, 0);

    public AutoTestDrivebaseCommand(MecanumDrivebaseSubsystem drive) {
        super(
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.START_TO_W_JUNCTION),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.W_JUNCTION_TO_STACK),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.STACK_TO_W_JUNCTION),
                // new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.W_JUNCTION_TO_STACK),
                // new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.STACK_TO_W_JUNCTION),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.W_JUNCTION_TO_MIDDLE_PARK),
                CommandScheduler.getInstance()::terminateOpMode
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
