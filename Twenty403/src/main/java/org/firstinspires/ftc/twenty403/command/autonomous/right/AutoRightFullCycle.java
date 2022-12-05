package org.firstinspires.ftc.twenty403.command.autonomous.right;

import java.util.function.DoubleSupplier;
import java.util.function.Function;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftIntakeCommand;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

// autonomous for blue home and red away
// parks in left position
// scores preload & 2 more cones from stack
public class AutoRightFullCycle extends SequentialCommandGroup {
    public AutoRightFullCycle(
            Robot r,
            Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> parkingDestination,
            DoubleSupplier currOpModeRunTime) {
        super(
                new ClawCloseCommand(r.clawSubsystem),
                new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.Right.START_TO_W_JUNCTION)
                        .alongWith(new SequentialCommandGroup(
                                new WaitCommand(0.2), new LiftHighJunctionCommand(r.liftSubsystem))),
                new ClawOpenCommand(r.clawSubsystem),
                new AutoRightSingleCycle(r.drivebaseSubsystem, r.liftSubsystem, r.clawSubsystem),
                new AutoRightSingleCycle(r.drivebaseSubsystem, r.liftSubsystem, r.clawSubsystem),
                new TrajectorySequenceCommand(r.drivebaseSubsystem, parkingDestination)
                        .alongWith(new LiftIntakeCommand(r.liftSubsystem)));

        //                new AutoRightSingleCycle(drivebaseSubsystem, liftSubsystem, clawSubsystem),
        //                new AutoRightSingleCycle(drivebaseSubsystem, liftSubsystem, clawSubsystem),

        ;
    }
}
