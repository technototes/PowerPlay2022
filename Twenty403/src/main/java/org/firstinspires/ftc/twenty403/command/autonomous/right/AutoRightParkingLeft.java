package org.firstinspires.ftc.twenty403.command.autonomous.right;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoRightParkingLeft extends SequentialCommandGroup {
    public AutoRightParkingLeft(DrivebaseSubsystem drivebaseSubsystem) {
        super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstants.Right.START_TO_LEFT_PARK));
    }
}
