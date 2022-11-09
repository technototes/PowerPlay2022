package org.firstinspires.ftc.sixteen750.command.claw;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.Command;

public class FlipperServoIncrementalDownCommand implements Command {
    private final ClawSubsystem clawSubsystem;

    public FlipperServoIncrementalDownCommand(ClawSubsystem s) {
        clawSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        clawSubsystem.flipperServoIncrementalDown();
    }
}