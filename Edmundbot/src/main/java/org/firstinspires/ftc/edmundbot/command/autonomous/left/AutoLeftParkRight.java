package org.firstinspires.ftc.edmundbot.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.edmundbot.Robot;
import org.firstinspires.ftc.edmundbot.command.autonomous.AutoConstants;

public class AutoLeftParkRight extends SequentialCommandGroup {

    public AutoLeftParkRight(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Left.START_TO_RIGHT_PARK
            )
        );
    }
}
