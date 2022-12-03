package org.firstinspires.ftc.twenty403.command.autonomous.red_away;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsRed;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoRedAwayCycleRight extends SequentialCommandGroup {
    public AutoRedAwayCycleRight(
            DrivebaseSubsystem drivebaseSubsystem, ClawSubsystem clawSubsystem, LiftSubsystem liftSubsystem) {
        super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsRed.Away.START_TO_W_JUNCTION)
                .alongWith(
                        (new LiftHighJunctionCommand(liftSubsystem)),
                        new ClawOpenCommand(clawSubsystem),
                        new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsRed.Away.W_JUNCTION_TO_STACK)
                                .alongWith(new LiftCollectCommand(liftSubsystem)),
                        new ClawCloseCommand(clawSubsystem),
                        new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsRed.Away.STACK_TO_W_JUNCTION)
                                .alongWith(new LiftHighJunctionCommand(liftSubsystem)),
                        new ClawOpenCommand(clawSubsystem),
                        new TrajectorySequenceCommand(
                                        drivebaseSubsystem, AutoConstantsRed.Away.W_JUNCTION_TO_RIGHT_PARK)
                                .alongWith(new LiftCollectCommand(liftSubsystem))));
    }
}
