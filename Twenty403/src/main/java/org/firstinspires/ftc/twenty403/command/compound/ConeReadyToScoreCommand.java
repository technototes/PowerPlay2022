package org.firstinspires.ftc.twenty403.command.compound;

import org.firstinspires.ftc.twenty403.command.claw.ClawReleaseCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftCarryCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.ParallelCommandGroup;

public class ConeReadyToScoreCommand extends ParallelCommandGroup {

    public ConeReadyToScoreCommand(LiftSubsystem liftSubsystem, ClawSubsystem clawSubsystem) {
        super(
                new ClawReleaseCommand(clawSubsystem),
                new LiftCarryCommand(liftSubsystem)
        );
    }
}