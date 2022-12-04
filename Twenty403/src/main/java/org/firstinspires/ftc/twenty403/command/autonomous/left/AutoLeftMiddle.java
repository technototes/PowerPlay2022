package org.firstinspires.ftc.twenty403.command.autonomous.left;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

/*
original code:
public class AutoRightLeft extends SequentialCommandGroup {
    public AutoRightLeft(DrivebaseSubsystem drivebaseSubsystem) {
        super(
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.START_TO_LEFT_PARK)
        );
    }
}
*/
public class AutoLeftMiddle extends SequentialCommandGroup {
    public AutoLeftMiddle(DrivebaseSubsystem drivebaseSubsystem) {
        super(
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstants.Left.START_TO_MIDDLE_PARK),
                CommandScheduler.getInstance()::terminateOpMode);
    }
}
