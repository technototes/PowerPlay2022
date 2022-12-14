package org.firstinspires.ftc.sixteen750.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.sixteen750.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.sixteen750.subsystem.drive.MecanumDriveSubsystem;

public class RightJustParkMiddle extends SequentialCommandGroup {
    public RightJustParkMiddle(MecanumDriveSubsystem driveSubsystem){
        super(
                new TrajectorySequenceCommand(driveSubsystem,  AutoConstantsBlue.Away.START_TO_MIDDLE_PARK)
        );
    }
}
