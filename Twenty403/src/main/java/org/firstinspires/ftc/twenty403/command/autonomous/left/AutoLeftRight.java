package org.firstinspires.ftc.twenty403.command.autonomous.left;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoLeftRight extends SequentialCommandGroup {
    public AutoLeftRight(DrivebaseSubsystem drivebaseSubsystem) {
        super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstants.Left.START_TO_RIGHT_PARK));
    }
}
