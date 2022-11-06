package org.firstinspires.ftc.sixteen750.command.claw;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

public class ArmRetractCommand extends SequentialCommandGroup {
    public ArmRetractCommand(ClawSubsystem s) {
        super(
                new ElbowIntakePositionCommand(s),
                new FlipperPreIntakeCommand(s)
        );
    }
}
