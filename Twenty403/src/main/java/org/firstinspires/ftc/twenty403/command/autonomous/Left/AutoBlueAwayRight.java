package org.firstinspires.ftc.twenty403.command.autonomous.Left;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoBlueAwayRight extends SequentialCommandGroup {
    public AutoBlueAwayRight(DrivebaseSubsystem drivebaseSubsystem) {
        super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Away.START_TO_RIGHT_PARK));
    }
}
