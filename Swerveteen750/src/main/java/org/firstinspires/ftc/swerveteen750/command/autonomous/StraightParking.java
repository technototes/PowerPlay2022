package org.firstinspires.ftc.swerveteen750.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.MecanumDriveSubsystem;

public class StraightParking extends SequentialCommandGroup {
    public StraightParking(MecanumDriveSubsystem driveSubsystem) {
        super(
                new TrajectorySequenceCommand(driveSubsystem, AutoConstantsBlue.Away.ANOTHER_START_TO_MIDDLE_PARK)
        );
    }
}
