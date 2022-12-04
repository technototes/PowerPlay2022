package org.firstinspires.ftc.twenty403.command.autonomous.left;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoLeftLeft extends SequentialCommandGroup {
    public AutoLeftLeft(DrivebaseSubsystem drivebaseSubsystem) {
        super(
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstants.Left.START_TO_LEFT_PARK),
                CommandScheduler.getInstance()::terminateOpMode);
    }
}
