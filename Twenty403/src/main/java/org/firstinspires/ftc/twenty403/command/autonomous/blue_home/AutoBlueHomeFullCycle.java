package org.firstinspires.ftc.twenty403.command.autonomous.blue_home;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.twenty403.helpers.ElapsedTimeHelper;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.ChoiceCommand;
import com.technototes.library.command.ConditionalCommand;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Function;

// autonomous for blue home and red away
// parks in left position
// scores preload & 2 more cones from stack
public class AutoBlueHomeFullCycle extends SequentialCommandGroup {
    public AutoBlueHomeFullCycle(
            Robot r, Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> parkingDestination,
            DoubleSupplier currOpModeRunTime) {
        super(
                new ClawCloseCommand(r.clawSubsystem),
                new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstantsBlue.Home.START_TO_W_JUNCTION)
                        .alongWith(new SequentialCommandGroup(
                                new WaitCommand(0.2), new LiftHighJunctionCommand(r.liftSubsystem))),
                new ClawOpenCommand(r.clawSubsystem),
                new AutoRightSingleCycle(r.drivebaseSubsystem, r.liftSubsystem, r.clawSubsystem),
                new AutoRightSingleCycle(r.drivebaseSubsystem, r.liftSubsystem, r.clawSubsystem),
                new TrajectorySequenceCommand(r.drivebaseSubsystem, parkingDestination)
                        .alongWith(new LiftCollectCommand(r.liftSubsystem)));


//                new AutoRightSingleCycle(drivebaseSubsystem, liftSubsystem, clawSubsystem),
//                new AutoRightSingleCycle(drivebaseSubsystem, liftSubsystem, clawSubsystem),

        ;
    }
}
