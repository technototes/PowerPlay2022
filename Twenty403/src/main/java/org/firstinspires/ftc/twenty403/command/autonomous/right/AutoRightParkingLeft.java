package org.firstinspires.ftc.twenty403.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;

public class AutoRightParkingLeft extends SequentialCommandGroup {

    public AutoRightParkingLeft(Robot r) {
        super(
                new ClawCloseCommand(r.clawSubsystem),
                new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.Right.START_TO_LEFT_PARK
                )
        );
    }
}
