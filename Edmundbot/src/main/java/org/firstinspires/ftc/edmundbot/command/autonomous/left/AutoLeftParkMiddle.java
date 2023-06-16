package org.firstinspires.ftc.edmundbot.command.autonomous.left;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.edmundbot.Robot;
import org.firstinspires.ftc.edmundbot.command.autonomous.AutoConstants;

/*
original code:
public class AutoRightLeft extends SequentialCommandGroup {
    public AutoRightLeft(DrivebaseSubsystem drivebaseSubsystem) {
        super(
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstants.Left.START_TO_LEFT_PARK)
        );
    }
}
*/
public class AutoLeftParkMiddle extends SequentialCommandGroup {

    public AutoLeftParkMiddle(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Left.START_TO_MIDDLE_PARK
            ),
            CommandScheduler.getInstance()::terminateOpMode
        );
    }
}
