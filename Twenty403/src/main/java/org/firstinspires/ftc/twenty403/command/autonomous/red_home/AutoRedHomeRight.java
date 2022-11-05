package org.firstinspires.ftc.twenty403.command.autonomous.red_home;

/*
original code:
public class AutoBlueHomeLeft extends SequentialCommandGroup {
    public AutoBlueHomeLeft(DrivebaseSubsystem drivebaseSubsystem) {
        super(
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.START_TO_LEFT_PARK
        );
    }
}
*/

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsRed;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoRedHomeRight extends SequentialCommandGroup {
    public AutoRedHomeRight(DrivebaseSubsystem drivebaseSubsystem) {
        //super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsRed.Home.START_TO_RIGHT_PARK));
    }
}
