package org.firstinspires.ftc.twenty403.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoRightParkingRight extends SequentialCommandGroup {

    public AutoRightParkingRight(Robot r) {
        super(
            r.clawSubsystem.closeCommand,
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.START_TO_RIGHT_PARK
            )
        );
    }
}
