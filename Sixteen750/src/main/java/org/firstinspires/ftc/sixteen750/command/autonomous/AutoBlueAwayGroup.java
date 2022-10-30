package org.firstinspires.ftc.sixteen750.command.autonomous;

import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ConeReadyToScoreCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoBlueAwayGroup extends SequentialCommandGroup {
    public AutoBlueAwayGroup(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                new TrajectorySequenceCommand(drive, AutoConstantBlue.Away.START_TO_NINEOCLOCK_JUNCTION)
                        .alongWith(new ConeReadyToScoreCommand(lift, claw)),
                new ClawOpenCommand(claw),
                new AutoBlueAwayConeStackCommand(drive, lift, claw),
                new AutoBlueAwayConeStackCommand(drive, lift, claw),
                new AutoBlueAwayConeStackCommand(drive, lift, claw),
                new AutoBlueAwayConeStackCommand(drive, lift, claw),
                new TrajectorySequenceCommand(
                        drive,
                        AutoConstantBlue.Away
                                .SIXOCLOCK_JUNCTION_TO_PARK_RIGHT) /*Placeholder for what we're doing for parking*/);
    }
}
