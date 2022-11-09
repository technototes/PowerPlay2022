package org.firstinspires.ftc.sixteen750.command.claw;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

public class FlipperServoIncrementalUpCommand implements Command {
    private final ClawSubsystem clawSubsystem;

    public FlipperServoIncrementalUpCommand(ClawSubsystem s) {
        clawSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        clawSubsystem.flipperServoIncrementalUp();
    }
}
