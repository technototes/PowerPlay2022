package org.firstinspires.ftc.twenty403.command.autonomous.Left;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;

public class LeftJunctionStackCycle extends SequentialCommandGroup {
    public LeftJunctionStackCycle(Robot robot) {
        super(
                new TrajectorySequenceCommand(robot.drivebaseSubsystem, AutoConstantsBlue.Away.E_JUNCTION_TO_STACK)
                        .alongWith(new LiftCollectCommand(robot.liftSubsystem)),
                new ClawCloseCommand(robot.clawSubsystem),
                new TrajectorySequenceCommand(robot.drivebaseSubsystem, AutoConstantsBlue.Away.STACK_TO_E_JUNCTION)
                        .alongWith(new LiftHighJunctionCommand(robot.liftSubsystem)),
                new ClawOpenCommand(robot.clawSubsystem)
        );
    }
}
