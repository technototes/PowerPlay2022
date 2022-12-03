package org.firstinspires.ftc.sixteen750.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import org.firstinspires.ftc.sixteen750.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.sixteen750.subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.sixteen750.swerve_util.TrajectorySequence;

public class RightJustParkLeft extends SequentialCommandGroup {
    public RightJustParkLeft(MecanumDriveSubsystem driveSubsystem){
        super(
                new TrajectorySequenceCommand(driveSubsystem, AutoConstantsBlue.Home.START_TO_LEFT_PARK)
        );
    }
}
