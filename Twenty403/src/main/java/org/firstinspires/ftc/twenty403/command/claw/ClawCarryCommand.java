package org.firstinspires.ftc.twenty403.command.claw;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;

public class ClawCarryCommand implements Command {
    private ClawSubsystem subsystem;

    public ClawCarryCommand(ClawSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.carry();
    }
}
