package org.firstinspires.ftc.twenty403.command.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoTestDrivebaseCommand extends SequentialCommandGroup {
    // public static ConfigurablePose TEST_START = new ConfigurablePose(0, 0, 0);
    // public static ConfigurablePose TEST_END = new ConfigurablePose(0, 10, 0);

    public AutoTestDrivebaseCommand(MecanumDrivebaseSubsystem drive) {
        super(
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.START_TO_W_JUNCTION),
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.W_JUNCTION_TO_STACK),
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.STACK_TO_W_JUNCTION),
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.W_JUNCTION_TO_STACK),
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.STACK_TO_W_JUNCTION),
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.W_JUNCTION_TO_STACK),
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.STACK_TO_W_JUNCTION),
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.W_JUNCTION_TO_STACK),
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.STACK_TO_W_JUNCTION),
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.W_JUNCTION_TO_STACK),
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.STACK_TO_W_JUNCTION),
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.W_JUNCTION_TO_LEFT_PARK),
                CommandScheduler.getInstance()::terminateOpMode
                //                .alongWith(new ConeReadyToScoreCommand(cone)),
                // new ClawOpenCommand(claw),
                //                new AutoBlueAwayConeStackCommand(drive, lift, claw),
                //                new AutoBlueAwayConeStackCommand(drive, lift, claw),
                //                new AutoBlueAwayConeStackCommand(drive, lift, claw),
                //                new AutoBlueAwayConeStackCommand(drive, lift, claw)
                /*new TrajectorySequenceCommand(
                        drive,
                        Robot.Trajectories.BLUE_PARK_LOCATION_Away)/*
                /*Placeholder for what we're doing for parking*/
                );
    }
}
