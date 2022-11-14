package org.firstinspires.ftc.twenty403.command.autonomous.blue_away;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoBlueAwayLeft extends SequentialCommandGroup {
    public AutoBlueAwayLeft(DrivebaseSubsystem drivebaseSubsystem) {
        super(
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Away.START_TO_LEFT_PARK),
                CommandScheduler.getInstance()::terminateOpMode);
    }
}
