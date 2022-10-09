package org.firstinspires.ftc.sixteen750.command.autonomous;

import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.cone.ConeReadyToScoreHigh;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoRedHomeGroup extends SequentialCommandGroup {
    public AutoRedHomeGroup(
            MecanumDrivebaseSubsystem drive, ClawSubsystem claw, ConeSubsystem cone, LiftSubsystem lift) {
        super(
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Home.START_TO_SIXOCLOCK_JUNCTION)
                        .alongWith(new ConeReadyToScoreHigh(cone)),
                new ClawOpenCommand(claw),
                new AutoRedHomeConeStackCommand(drive, cone, lift, claw),
                new AutoRedHomeConeStackCommand(drive, cone, lift, claw),
                new AutoRedHomeConeStackCommand(drive, cone, lift, claw),
                new AutoRedHomeConeStackCommand(drive, cone, lift, claw),
                // TODO: This should be our "use vision to decide where to park" command
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Home.SIXCLOCK_JUNCTION_TO_RIGHT));
    }
}
