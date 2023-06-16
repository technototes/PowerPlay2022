package org.firstinspires.ftc.edmundbot.command.claw;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.edmundbot.subsystem.ClawSubsystem;

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
