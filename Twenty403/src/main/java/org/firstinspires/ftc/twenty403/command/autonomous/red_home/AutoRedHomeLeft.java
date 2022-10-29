package org.firstinspires.ftc.twenty403.command.autonomous.red_home;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsRed;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

public class AutoRedHomeLeft extends SequentialCommandGroup {
    public AutoRedHomeLeft(DrivebaseSubsystem drivebaseSubsystem) {
        super(
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsRed.Home.START_TO_LEFT_PARK)
        );
    }
}
