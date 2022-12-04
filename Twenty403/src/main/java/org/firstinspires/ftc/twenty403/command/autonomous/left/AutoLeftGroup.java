package org.firstinspires.ftc.twenty403.command.autonomous.left;

import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoLeftGroup extends SequentialCommandGroup {
    public AutoLeftGroup(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                /*new TrajectorySequenceCommand(drive, Robot.Trajectories.BLUE_HIGH_JUNCTION_AWAY)
                .alongWith(new ConeReadyToScoreCommand(cone)),*/
                new ClawOpenCommand(claw),
                new AutoLeftConeStackCommand(drive, lift, claw),
                new AutoLeftConeStackCommand(drive, lift, claw),
                new AutoLeftConeStackCommand(drive, lift, claw),
                new AutoLeftConeStackCommand(drive, lift, claw)
                /*new TrajectorySequenceCommand(
                drive,
                Robot.Trajectories.BLUE_PARK_LOCATION_AWAY) */
                /*Placeholder for what we're doing for parking*/ );
    }
}
