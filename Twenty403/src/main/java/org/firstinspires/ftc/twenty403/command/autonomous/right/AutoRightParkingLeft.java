package org.firstinspires.ftc.twenty403.command.autonomous.right;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoRightParkingLeft extends SequentialCommandGroup {
    public AutoRightParkingLeft(Robot r) {
        super(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.Right.START_TO_LEFT_PARK));
    }
}
