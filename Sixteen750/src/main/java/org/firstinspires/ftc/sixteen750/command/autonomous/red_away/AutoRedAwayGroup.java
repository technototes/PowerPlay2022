package org.firstinspires.ftc.sixteen750.command.autonomous.red_away;

import org.firstinspires.ftc.sixteen750.command.autonomous.AutoConstantsRed;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoRedAwayGroup extends SequentialCommandGroup {
    public AutoRedAwayGroup(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.START_TO_E_JUNCTION)
                /*.alongWith(new ConeReadyScoreHigh(lift, claw))*/ ,
                new ClawOpenCommand(claw),
                new AutoRedAwayConeStackCommand(drive, lift, claw),
                new AutoRedAwayConeStackCommand(drive, lift, claw),
                new AutoRedAwayConeStackCommand(drive, lift, claw),
                new AutoRedAwayConeStackCommand(drive, lift, claw)
                // ,
                // TODO: This should call the vision choice command to decide where to park
                // new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.E_JUNCTION_LEFT)
                );
    }
}
