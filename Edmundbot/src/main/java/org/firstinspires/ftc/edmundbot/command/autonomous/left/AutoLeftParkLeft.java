package org.firstinspires.ftc.edmundbot.command.autonomous.left;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.edmundbot.Robot;
import org.firstinspires.ftc.edmundbot.command.autonomous.AutoConstants;

public class AutoLeftParkLeft extends SequentialCommandGroup {

    public AutoLeftParkLeft(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Left.START_TO_LEFT_PARK
            ),
            CommandScheduler.getInstance()::terminateOpMode
        );
    }
}
