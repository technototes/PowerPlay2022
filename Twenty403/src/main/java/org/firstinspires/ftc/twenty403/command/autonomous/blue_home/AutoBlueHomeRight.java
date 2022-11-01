package org.firstinspires.ftc.twenty403.command.autonomous.blue_home;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoBlueHomeRight extends SequentialCommandGroup {
    public AutoBlueHomeRight(DrivebaseSubsystem drivebaseSubsystem) {
        super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.START_TO_RIGHT_PARK));
    }
}
