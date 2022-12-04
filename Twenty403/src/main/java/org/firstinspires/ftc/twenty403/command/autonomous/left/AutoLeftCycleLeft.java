package org.firstinspires.ftc.twenty403.command.autonomous.left;

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

public class AutoLeftCycleLeft extends SequentialCommandGroup {
    public AutoLeftCycleLeft(
            DrivebaseSubsystem drivebaseSubsystem, ClawSubsystem clawSubsystem, LiftSubsystem liftSubsystem) {
        super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstants.Left.START_TO_E_JUNCTION)
                .alongWith(
                        (new LiftHighJunctionCommand(liftSubsystem)),
                        new ClawOpenCommand(clawSubsystem),
                        new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstants.Left.E_JUNCTION_TO_STACK)
                                .alongWith(new LiftCollectCommand(liftSubsystem)),
                        new ClawCloseCommand(clawSubsystem),
                        new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstants.Left.STACK_TO_E_JUNCTION)
                                .alongWith(new LiftHighJunctionCommand(liftSubsystem)),
                        new ClawOpenCommand(clawSubsystem),
                        new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstants.Left.E_JUNCTION_TO_LEFT_PARK)
                                .alongWith(new LiftCollectCommand(liftSubsystem))));
    }
}
