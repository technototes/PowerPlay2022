package org.firstinspires.ftc.sixteen750.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.cone.ConeReadyToScoreCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

public class BlueAwayConeStackCommand extends SequentialCommandGroup {
    public BlueAwayConeStackCommand(MecanumDrivebaseSubsystem drive, ClawSubsystem claw, LiftSubsystem lift, ConeSubsystem cone) {
        super(new TrajectorySequenceCommand(drive, RobotConstant.HIGH_JUNCTION_AWAY).alongWith(new ConeReadyToScoreCommand(cone)),
                new ClawOpenCommand(claw), new BlueAwayAutoConeStack(drive, claw, lift, cone), new BlueAwayAutoConeStack(drive, claw, lift, cone),
                new BlueAwayAutoConeStack(drive, claw, lift, cone), new BlueAwayAutoConeStack(drive, claw, lift, cone),
                new TrajectorySequenceCommand(drive.RobotConstant.PARK_LOCATION)/*Placeholder for what we're doing for parking*/);
    }
}
