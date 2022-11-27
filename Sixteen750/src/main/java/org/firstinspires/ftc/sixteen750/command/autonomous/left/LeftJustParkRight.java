package org.firstinspires.ftc.sixteen750.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import org.firstinspires.ftc.sixteen750.command.autonomous.AutoConstantsBlue;

public class LeftJustParkRight extends SequentialCommandGroup {
    public LeftJustParkRight(MecanumDrivebaseSubsystem drivebaseSubsystem){
        super(
                new TrajectorySequenceCommand(drivebaseSubsystem,  AutoConstantsBlue.Home.START_TO_RIGHT_PARK)
        );
    }
}
