package org.firstinspires.ftc.sixteen750.command.claw;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

public class ClawReadyToIntakeGroundCommandGroup extends SequentialCommandGroup {
    public ClawReadyToIntakeGroundCommandGroup(ClawSubsystem s) {
        super(
                new ElbowGroundIntakePositionCommand(s),
                new FlipperPreIntakeCommand(s),
                new ClawOpenCommand(s)
        );
    }
}
