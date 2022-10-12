package org.firstinspires.ftc.sixteen750.command.compound;

import org.firstinspires.ftc.sixteen750.command.claw.ClawReleaseCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftCarryCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.Command;
import com.technototes.library.command.SequentialCommandGroup;

public class ConeReadyToScoreCommand extends SequentialCommandGroup {
    public ConeReadyToScoreCommand(LiftSubsystem lift, ClawSubsystem claw) {
        super(
                new ClawReleaseCommand(claw),
                new LiftCarryCommand(lift)
        );
    }
}
