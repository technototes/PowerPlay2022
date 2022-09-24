package org.firstinspires.ftc.sixteen750.Commands.Cone;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.sixteen750.Commands.Claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.Commands.Claw.ClawReleaseCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;

public class ScoreHighJunctionCommandGroup extends SequentialCommandGroup {
    public ScoreHighJunctionCommandGroup(LiftSubsystem lift, ClawSubsystem claw, ConeSubsystem cone){
        super(
                new LiftHighJunction(lift),
                new ClawReleaseCommand(claw),
                new ClawOpenCommand(claw),
                new ConeReadyToIntakeCommand(cone)
        );
    }
}
