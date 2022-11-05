package org.firstinspires.ftc.twenty403.command.autonomous.red_home;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsRed;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class ParkRedHomeMiddle extends SequentialCommandGroup {
    public ParkRedHomeMiddle(DrivebaseSubsystem drivebaseSubsystem) {
         super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsRed.Home.START_TO_MIDDLE_PARK));
    }
}
