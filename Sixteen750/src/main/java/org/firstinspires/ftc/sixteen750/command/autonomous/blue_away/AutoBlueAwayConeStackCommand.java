package org.firstinspires.ftc.sixteen750.command.autonomous.blue_away;

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

public class AutoBlueAwayConeStackCommand extends SequentialCommandGroup {
    public AutoBlueAwayConeStackCommand(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                new TrajectorySequenceCommand(drive, AutoConstantBlue.Away.NINEOCLOCK_TO_STACK)
                        .alongWith(new ConeReadyToIntakeCommand(lift, claw)),
                new ClawCloseCommand(claw),
                new TrajectorySequenceCommand(drive, AutoConstantBlue.Away.STACK_TO_NINEOCLOCK_JUNCTION)
                        .alongWith(new ConeReadyToScoreCommand(lift, claw)),
                new ClawOpenCommand(claw));
    }
}
