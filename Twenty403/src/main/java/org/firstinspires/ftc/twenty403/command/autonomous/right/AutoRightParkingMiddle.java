package org.firstinspires.ftc.twenty403.command.autonomous.right;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoRightParkingMiddle extends SequentialCommandGroup {
    public AutoRightParkingMiddle(DrivebaseSubsystem drivebaseSubsystem) {
        super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstants.Right.START_TO_MIDDLE_PARK));
    }
}
