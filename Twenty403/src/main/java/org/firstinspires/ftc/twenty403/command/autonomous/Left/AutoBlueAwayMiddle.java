package org.firstinspires.ftc.twenty403.command.autonomous.Left;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

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
        super(
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Away.START_TO_MIDDLE_PARK),
                CommandScheduler.getInstance()::terminateOpMode);
    }
}
