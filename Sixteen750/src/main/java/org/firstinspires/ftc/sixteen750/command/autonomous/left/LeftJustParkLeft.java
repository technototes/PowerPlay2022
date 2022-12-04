package org.firstinspires.ftc.sixteen750.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import org.firstinspires.ftc.sixteen750.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.sixteen750.subsystem.MecanumDriveSubsystem;

public class LeftJustParkLeft extends SequentialCommandGroup {
    public LeftJustParkLeft(MecanumDriveSubsystem driveSubsystem) {
        super(
                new TrajectorySequenceCommand(driveSubsystem, AutoConstantsBlue.Away.START_TO_LEFT_PARK)
        );
    }
}
