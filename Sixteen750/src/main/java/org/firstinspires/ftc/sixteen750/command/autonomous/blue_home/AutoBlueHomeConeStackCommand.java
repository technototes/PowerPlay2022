package org.firstinspires.ftc.sixteen750.command.autonomous.blue_home;

import org.firstinspires.ftc.sixteen750.command.autonomous.AutoConstantBlue;
import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ConeReadyToIntakeCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ConeReadyToScoreCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoBlueHomeConeStackCommand extends SequentialCommandGroup {
    public AutoBlueHomeConeStackCommand(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                new TrajectorySequenceCommand(drive, AutoConstantBlue.Home.E_JUNCTION_TO_STACK)
                        .alongWith(new ConeReadyToIntakeCommand(lift, claw)),
                new ClawCloseCommand(claw),
                new TrajectorySequenceCommand(drive, AutoConstantBlue.Home.STACK_TO_E_JUNCTION)
                        .alongWith(new ConeReadyToScoreCommand(lift, claw)),
                new ClawOpenCommand(claw));
    }
}
