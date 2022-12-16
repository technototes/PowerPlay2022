package org.firstinspires.ftc.swerveteen750.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.swerveteen750.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.MecanumDriveSubsystem;

public class LeftJustParkRight extends SequentialCommandGroup {
    public LeftJustParkRight(MecanumDriveSubsystem driveSubsystem) {
        super(
                new TrajectorySequenceCommand(driveSubsystem, AutoConstantsBlue.Away.START_TO_RIGHT_PARK)
        );
    }
}
