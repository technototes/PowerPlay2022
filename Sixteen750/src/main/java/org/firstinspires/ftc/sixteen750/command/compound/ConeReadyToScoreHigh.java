package org.firstinspires.ftc.sixteen750.command.compound;

import org.firstinspires.ftc.sixteen750.command.claw.ClawReleaseCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftHighPoleCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;

public class ConeReadyToScoreHigh extends SequentialCommandGroup {
    public ConeReadyToScoreHigh(LiftSubsystem lift, ClawSubsystem claw) {
        super(new ClawReleaseCommand(claw), new LiftHighPoleCommand(lift));
    }
}
