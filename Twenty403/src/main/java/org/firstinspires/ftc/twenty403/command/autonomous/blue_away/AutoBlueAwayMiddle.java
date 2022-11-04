package org.firstinspires.ftc.twenty403.command.autonomous.blue_away;

import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.SequentialCommandGroup;

/*
original code:
public class AutoBlueHomeLeft extends SequentialCommandGroup {
    public AutoBlueHomeLeft(DrivebaseSubsystem drivebaseSubsystem) {
        super(
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.START_TO_LEFT_PARK)
        );
    }
}
*/
public class AutoBlueAwayMiddle extends SequentialCommandGroup {
    public AutoBlueAwayMiddle(DrivebaseSubsystem drivebaseSubsystem) {
        // super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Away.START_TO_MIDDLE_PARK));
    }
}
