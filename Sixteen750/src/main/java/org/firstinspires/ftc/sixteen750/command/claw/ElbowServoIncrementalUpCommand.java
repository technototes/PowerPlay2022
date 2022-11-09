package org.firstinspires.ftc.sixteen750.command.claw;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.Command;

public class ElbowServoIncrementalUpCommand implements Command {
    private final ClawSubsystem clawSubsystem;

    public ElbowServoIncrementalUpCommand(ClawSubsystem s) {
        clawSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        clawSubsystem.elbowServoIncrementalUp();
    }
}
