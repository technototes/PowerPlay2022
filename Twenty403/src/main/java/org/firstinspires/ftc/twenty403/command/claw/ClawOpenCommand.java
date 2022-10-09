package org.firstinspires.ftc.twenty403.command.claw;

import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;

import com.technototes.library.command.Command;

public class ClawOpenCommand implements Command {
    private ClawSubsystem subsystem;

    public ClawOpenCommand(ClawSubsystem s) {
        this.subsystem = s;
        addRequirements(this.subsystem); // Keeps robot from breaking
    }

    @Override
    public void execute() {
        this.subsystem.open();
    }
}
