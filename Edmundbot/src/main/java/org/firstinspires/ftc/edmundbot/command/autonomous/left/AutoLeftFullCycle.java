package org.firstinspires.ftc.edmundbot.command.autonomous.left;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;
import java.util.function.DoubleSupplier;
import java.util.function.Function;

import org.firstinspires.ftc.edmundbot.Robot;
import org.firstinspires.ftc.edmundbot.command.autonomous.AutoConstants;
import org.firstinspires.ftc.edmundbot.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.edmundbot.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.edmundbot.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.edmundbot.command.lift.LiftIntakeCommand;

public class AutoLeftFullCycle extends SequentialCommandGroup {

    public AutoLeftFullCycle(
        Robot r,
        Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> parkingDestination,
        DoubleSupplier currOpModeRunTime
    ) {
        super(
            new ClawCloseCommand(r.clawSubsystem),
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Left.START_TO_E_JUNCTION
            )
                .alongWith(
                    new SequentialCommandGroup(
                        new WaitCommand(0.2),
                        new LiftHighJunctionCommand(r.liftSubsystem)
                    )
                ),
            new ClawOpenCommand(r.clawSubsystem),
            new AutoLeftSingleCycle(r),
            new AutoLeftSingleCycleTwo(r),
            new AutoLeftSingleCycleTwo(r),
            //new AutoRightSingleCycleOne(r),
            //new AutoRightSingleCycleOne(r),
            //new AutoRightSingleCycleOne(r),
            new TrajectorySequenceCommand(r.drivebaseSubsystem, parkingDestination)
                .alongWith(new LiftIntakeCommand(r.liftSubsystem))
            //new SlowCommand(r.drivebaseSubsystem)
        );
        // new AutoRightSingleCycle(drivebaseSubsystem, liftSubsystem, clawSubsystem),
        // new AutoRightSingleCycle(drivebaseSubsystem, liftSubsystem, clawSubsystem),
    }
}
