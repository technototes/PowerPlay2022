package org.firstinspires.ftc.twenty403.command.autonomous.left;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;

public class AutoLeftTest extends SequentialCommandGroup {

    public AutoLeftTest(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.START_TO_W_JUNCTION
            )
                .alongWith(new LiftHighJunctionCommand(r.liftSubsystem)),
            new ClawOpenCommand(r.clawSubsystem),
            CommandScheduler::terminateOpMode
        );
    }
}
