package org.firstinspires.ftc.edmundbot.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.edmundbot.Robot;
import org.firstinspires.ftc.edmundbot.command.autonomous.AutoConstants;
import org.firstinspires.ftc.edmundbot.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.edmundbot.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.edmundbot.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.edmundbot.command.lift.LiftHighJunctionCommand;

public class AutoLeftJunctionStackCycle extends SequentialCommandGroup {

    public AutoLeftJunctionStackCycle(Robot robot) {
        super(
            new TrajectorySequenceCommand(
                robot.drivebaseSubsystem,
                AutoConstants.Left.E_JUNCTION_TO_STACK
            )
                .alongWith(new LiftCollectCommand(robot.liftSubsystem)),
            new ClawCloseCommand(robot.clawSubsystem),
            new TrajectorySequenceCommand(
                robot.drivebaseSubsystem,
                AutoConstants.Left.STACK_TO_E_JUNCTION
            )
                .alongWith(new LiftHighJunctionCommand(robot.liftSubsystem)),
            new ClawOpenCommand(robot.clawSubsystem)
        );
    }
}
