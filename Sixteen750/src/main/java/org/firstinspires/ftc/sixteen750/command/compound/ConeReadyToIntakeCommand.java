package org.firstinspires.ftc.sixteen750.command.compound;

import org.firstinspires.ftc.sixteen750.command.claw.ClawCarryCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftIntakeCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;

public class ConeReadyToIntakeCommand extends SequentialCommandGroup {
    public ConeReadyToIntakeCommand(LiftSubsystem lift, ClawSubsystem claw) {
        super(new ClawOpenCommand(claw), new ClawCarryCommand(claw), new LiftIntakeCommand(lift));
    }
}
