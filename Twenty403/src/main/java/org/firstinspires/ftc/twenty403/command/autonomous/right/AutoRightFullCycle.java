package org.firstinspires.ftc.twenty403.command.autonomous.right;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.Commands;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

// autonomous for right
// parks in left position
// scores preload & 2 more cones from stack
public class AutoRightFullCycle extends SequentialCommandGroup {

    public AutoRightFullCycle(
        Robot r,
        Function<
            Function<Pose2d, TrajectorySequenceBuilder>,
            TrajectorySequence
        > parkingDestination,
        DoubleSupplier currOpModeRunTime
    ) {
        super(
            Commands.Claw.close(r.clawSubsystem),
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Right.START_TO_W_JUNCTION
            )
                .alongWith(
                    new WaitCommand(0.2).andThen(Commands.Lift.highJunction(r.liftSubsystem))
                ),
            Commands.Claw.open(r.clawSubsystem),
            new AutoRightSingleCycleOne(r),
            new AutoRightSingleCycleTwo(r),
            new AutoRightSingleCycleTwo(r),
            //new AutoRightSingleCycleOne(r),
            //new AutoRightSingleCycleOne(r),
            //new AutoRightSingleCycleOne(r),
            new TrajectorySequenceCommand(r.drivebaseSubsystem, parkingDestination)
                .alongWith(Commands.Lift.intake(r.liftSubsystem))
            //new SlowCommand(r.drivebaseSubsystem)
        );
        // new AutoRightSingleCycle(drivebaseSubsystem, liftSubsystem, clawSubsystem),
        // new AutoRightSingleCycle(drivebaseSubsystem, liftSubsystem, clawSubsystem),
    }
}
