package org.firstinspires.ftc.twenty403.command.autonomous.Left;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoBlueAwayFullCycleLeft extends SequentialCommandGroup {
    public AutoBlueAwayFullCycleLeft(Robot robot) {
        super(
                new ClawCloseCommand(robot.clawSubsystem),
                new TrajectorySequenceCommand(robot.drivebaseSubsystem, AutoConstantsBlue.Away.START_TO_E_JUNCTION)
                        .alongWith(new SequentialCommandGroup(
                                new WaitCommand(0.2), new LiftHighJunctionCommand(robot.liftSubsystem))),
                new ClawOpenCommand(robot.clawSubsystem),
                new LeftJunctionStackCycle(robot),
                new LeftJunctionStackCycle(robot),
//                new LeftJunctionStackCycle(robot),
                new TrajectorySequenceCommand(robot.drivebaseSubsystem, AutoConstantsBlue.Away.E_JUNCTION_TO_LEFT_PARK)
                        .alongWith(new LiftCollectCommand(robot.liftSubsystem)));
    }
}
