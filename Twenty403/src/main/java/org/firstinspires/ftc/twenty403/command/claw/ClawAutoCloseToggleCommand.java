package org.firstinspires.ftc.twenty403.command.claw;

import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;

import com.technototes.library.command.Command;

public class ClawAutoCloseToggleCommand implements Command {
    private ClawSubsystem subsystem;

    public ClawAutoCloseToggleCommand(ClawSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.toggleAutoClose();
    }
}