package org.firstinspires.ftc.twenty403.command.autonomous.right;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoRightCycleMiddle extends SequentialCommandGroup {
    public AutoRightCycleMiddle(
            DrivebaseSubsystem drivebaseSubsystem, ClawSubsystem clawSubsystem, LiftSubsystem liftSubsystem) {
        super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstants.Right.START_TO_W_JUNCTION)
                .alongWith(
                        (new LiftHighJunctionCommand(liftSubsystem)),
                        new ClawOpenCommand(clawSubsystem),
                        new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstants.Right.W_JUNCTION_TO_STACK)
                                .alongWith(new LiftCollectCommand(liftSubsystem)),
                        new ClawCloseCommand(clawSubsystem),
                        new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstants.Right.STACK_TO_W_JUNCTION)
                                .alongWith(new LiftHighJunctionCommand(liftSubsystem)),
                        new ClawOpenCommand(clawSubsystem),
                        new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstants.Right.W_JUNCTION_TO_MIDDLE_PARK)
                                .alongWith(new LiftCollectCommand(liftSubsystem))));
    }
}
