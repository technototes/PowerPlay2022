package org.firstinspires.ftc.twenty403.command.autonomous.left;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoLeftFullCycleMiddle extends SequentialCommandGroup {
    public AutoLeftFullCycleMiddle(Robot robot) {
        super(
                //                new ClawCloseCommand(robot.clawSubsystem),
                new TrajectorySequenceCommand(robot.drivebaseSubsystem, AutoConstants.Left.START_TO_E_JUNCTION)
                        .alongWith(new SequentialCommandGroup(
                                new WaitCommand(0.2), new LiftHighJunctionCommand(robot.liftSubsystem))),
                new ClawOpenCommand(robot.clawSubsystem),
                new LeftJunctionStackCycle(robot),
                new LeftJunctionStackCycle(robot),
                // new LeftJunctionStackCycle(robot),
                new TrajectorySequenceCommand(robot.drivebaseSubsystem, AutoConstants.Left.E_JUNCTION_TO_MIDDLE_PARK)
                        .alongWith(new LiftCollectCommand(robot.liftSubsystem)));
    }
}
