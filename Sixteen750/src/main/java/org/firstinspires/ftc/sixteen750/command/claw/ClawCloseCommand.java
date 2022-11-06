package org.firstinspires.ftc.sixteen750.command.claw;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.Command;

public class ClawCloseCommand implements Command {
    private ClawSubsystem subsystem;

    public ClawCloseCommand(ClawSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.clawClose();
    }

    @Override
    public boolean isFinished() {
        // TODO: Adjust this duration
        return getRuntime().seconds() > 0.5;
    }
}
