package org.firstinspires.ftc.twenty403.command.autonomous.blue_home;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class AutoRightSingleCycle extends SequentialCommandGroup {
    public AutoRightSingleCycle(DrivebaseSubsystem drivebaseSubsystem, LiftSubsystem liftSubsystem, ClawSubsystem clawSubsystem) {
        super(
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.W_JUNCTION_TO_STACK)
                        .alongWith(new LiftCollectCommand(liftSubsystem)),
                new ClawCloseCommand(clawSubsystem),
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.STACK_TO_W_JUNCTION)
                        .alongWith(new LiftHighJunctionCommand(liftSubsystem)),
                new ClawOpenCommand(clawSubsystem)

        );
    }
}
