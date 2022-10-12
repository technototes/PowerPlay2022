package org.firstinspires.ftc.sixteen750.command.autonomous;

import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ConeReadyToIntakeCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ConeReadyToScoreHigh;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoRedHomeConeStackCommand extends SequentialCommandGroup {
    public AutoRedHomeConeStackCommand(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Home.S_JUNCTION_TO_STACK)
                        .alongWith(new ConeReadyToIntakeCommand(lift, claw)),
                new ClawCloseCommand(claw),
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Home.STACK_TO_S_JUNCTION)
                        .alongWith(new ConeReadyToScoreHigh(lift, claw)),
                new ClawOpenCommand(claw));
    }
}
