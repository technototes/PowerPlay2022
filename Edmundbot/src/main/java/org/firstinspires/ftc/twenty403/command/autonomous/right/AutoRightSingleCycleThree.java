package org.firstinspires.ftc.twenty403.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;

public class AutoRightSingleCycleThree extends SequentialCommandGroup {

    public AutoRightSingleCycleThree(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.W_JUNCTION_TO_STACK_THREE
            )
                .alongWith(new LiftCollectCommand(r.liftSubsystem)),
            new ClawCloseCommand(r.clawSubsystem),
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.STACK_TO_W_JUNCTION_THREE
            )
                .alongWith(new LiftHighJunctionCommand(r.liftSubsystem)),
            new ClawOpenCommand(r.clawSubsystem)
        );
    }
}
