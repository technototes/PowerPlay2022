package org.firstinspires.ftc.sixteen750.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.sixteen750.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.sixteen750.subsystem.drive.MecanumDriveSubsystem;

public class LeftJustParkMiddle extends SequentialCommandGroup {
    public LeftJustParkMiddle(MecanumDriveSubsystem driveSubsystem) {
        super(
                new TrajectorySequenceCommand(driveSubsystem, AutoConstantsBlue.Away.START_TO_MIDDLE_PARK)
        );
    }
}