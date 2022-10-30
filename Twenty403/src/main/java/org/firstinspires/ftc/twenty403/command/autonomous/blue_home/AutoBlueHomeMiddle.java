package org.firstinspires.ftc.twenty403.command.autonomous.blue_home;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoBlueHomeMiddle extends SequentialCommandGroup {
    public AutoBlueHomeMiddle(DrivebaseSubsystem drivebaseSubsystem) {
        super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.START_TO_MIDDLE_PARK));
    }
}
