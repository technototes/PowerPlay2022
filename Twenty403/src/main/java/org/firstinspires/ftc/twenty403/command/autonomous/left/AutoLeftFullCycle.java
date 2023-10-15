package org.firstinspires.ftc.twenty403.command.autonomous.left;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class AutoLeftFullCycle extends SequentialCommandGroup {

    public AutoLeftFullCycle(
        Robot r,
        Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> parkingDestination,
        DoubleSupplier currOpModeRunTime
    ) {
        super(
            r.clawSubsystem.closeCommand,
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.Left.START_TO_E_JUNCTION
            )
                .alongWith(
                    new SequentialCommandGroup(
                        new WaitCommand(0.2),
                        r.liftSubsystem.highCommand
                    )
                ),
            r.clawSubsystem.openCommand,
            new AutoLeftSingleCycle(r),
            new AutoLeftSingleCycleTwo(r),
            new AutoLeftSingleCycleTwo(r),
            //new AutoRightSingleCycleOne(r),
            //new AutoRightSingleCycleOne(r),
            //new AutoRightSingleCycleOne(r),
            new TrajectorySequenceCommand(r.drivebaseSubsystem, parkingDestination)
                .alongWith(r.liftSubsystem.intakeCommand)
            //new SlowCommand(r.drivebaseSubsystem)
        );
        // new AutoRightSingleCycle(drivebaseSubsystem, liftSubsystem, clawSubsystem),
        // new AutoRightSingleCycle(drivebaseSubsystem, liftSubsystem, clawSubsystem),
    }
}
