package org.firstinspires.ftc.twenty403.command.autonomous;

import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.compound.ConeReadyScoreHigh;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoRedAwayGroup extends SequentialCommandGroup {
    public AutoRedAwayGroup(
            MecanumDrivebaseSubsystem drive, ClawSubsystem claw, ConeSubsystem cone, LiftSubsystem lift) {
        super(
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.START_TO_E_JUNCTION)
                        .alongWith(new ConeReadyScoreHigh(cone)),
                new ClawOpenCommand(claw),
                new AutoRedAwayConeStackCommand(drive, cone, lift, claw),
                new AutoRedAwayConeStackCommand(drive, cone, lift, claw),
                new AutoRedAwayConeStackCommand(drive, cone, lift, claw),
                new AutoRedAwayConeStackCommand(drive, cone, lift, claw),
                // TODO: This should call the vision choice command to decide where to park
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.E_JUNCTION_LEFT));
    }
}
