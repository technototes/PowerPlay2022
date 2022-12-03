package org.firstinspires.ftc.sixteen750.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import org.firstinspires.ftc.sixteen750.command.autonomous.AutoConstantsBlue;

public class RightJustParkMiddle extends SequentialCommandGroup {
    public RightJustParkMiddle(MecanumDrivebaseSubsystem drivebaseSubsystem){
        super(
                new TrajectorySequenceCommand(drivebaseSubsystem,  AutoConstantsBlue.Away.START_TO_MIDDLE_PARK)
        );
    }
}
