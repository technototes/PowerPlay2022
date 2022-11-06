package org.firstinspires.ftc.sixteen750.command.claw;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

public class ArmRetractCommand extends SequentialCommandGroup {
    public ArmRetractCommand(ClawSubsystem s) {
        super(
                new ElbowGroundIntakePositionCommand(s),
                new FlipperPreIntakeCommand(s)
        );
    }
}
