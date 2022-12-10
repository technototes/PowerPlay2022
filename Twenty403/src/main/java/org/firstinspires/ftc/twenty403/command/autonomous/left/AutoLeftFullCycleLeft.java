package org.firstinspires.ftc.twenty403.command.autonomous.left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;

public class AutoLeftFullCycleLeft extends SequentialCommandGroup {

    public AutoLeftFullCycleLeft(Robot robot) {
        super(
            new ClawCloseCommand(robot.clawSubsystem),
            new TrajectorySequenceCommand(
                robot.drivebaseSubsystem,
                AutoConstants.Left.START_TO_E_JUNCTION
            )
                .alongWith(
                    new SequentialCommandGroup(
                        new WaitCommand(0.2),
                        new LiftHighJunctionCommand(robot.liftSubsystem)
                    )
                ),
            new ClawOpenCommand(robot.clawSubsystem),
            new AutoLeftJunctionStackCycle(robot),
            new AutoLeftJunctionStackCycle(robot),
            //                new LeftJunctionStackCycle(robot),
            new TrajectorySequenceCommand(
                robot.drivebaseSubsystem,
                AutoConstants.Left.E_JUNCTION_TO_LEFT_PARK
            )
                .alongWith(new LiftCollectCommand(robot.liftSubsystem))
        );
    }
}
