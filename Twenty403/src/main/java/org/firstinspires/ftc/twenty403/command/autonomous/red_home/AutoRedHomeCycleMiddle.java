package org.firstinspires.ftc.twenty403.command.autonomous.red_home;

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

public class AutoRedHomeCycleMiddle extends SequentialCommandGroup {
    public AutoRedHomeCycleMiddle(
            DrivebaseSubsystem drivebaseSubsystem, ClawSubsystem clawSubsystem, LiftSubsystem liftSubsystem) {
        super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsRed.Home.START_TO_E_JUNCTION)
                .alongWith(
                        (new LiftHighJunctionCommand(liftSubsystem)),
                        new ClawOpenCommand(clawSubsystem),
                        new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsRed.Home.E_JUNCTION_TO_STACK)
                                .alongWith(new LiftCollectCommand(liftSubsystem)),
                        new ClawCloseCommand(clawSubsystem),
                        new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsRed.Home.STACK_TO_E_JUNCTION)
                                .alongWith(new LiftHighJunctionCommand(liftSubsystem)),
                        new ClawOpenCommand(clawSubsystem),
                        new TrajectorySequenceCommand(
                                        drivebaseSubsystem, AutoConstantsRed.Home.E_JUNCTION_TO_MIDDLE_PARK)
                                .alongWith(new LiftCollectCommand(liftSubsystem))));
    }
}
