package org.firstinspires.ftc.swerveteen750.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.swerveteen750.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.MecanumDriveSubsystem;

public class RightJustParkLeft extends SequentialCommandGroup {
    public RightJustParkLeft(MecanumDriveSubsystem driveSubsystem){
        super(
                new TrajectorySequenceCommand(driveSubsystem, AutoConstantsBlue.Home.START_TO_LEFT_PARK)
        );
    }
}
