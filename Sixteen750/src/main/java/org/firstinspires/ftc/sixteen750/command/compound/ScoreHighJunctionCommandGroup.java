package org.firstinspires.ftc.sixteen750.command.compound;

import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawReleaseCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;

public class ScoreHighJunctionCommandGroup extends SequentialCommandGroup {
    public ScoreHighJunctionCommandGroup(LiftSubsystem lift, ClawSubsystem claw) {
        super(
                new LiftHighJunctionCommand(lift),
                new ClawReleaseCommand(claw),
                new ClawOpenCommand(claw),
                new ConeReadyToIntakeCommand(lift, claw));
    }
}
