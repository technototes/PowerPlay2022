package org.firstinspires.ftc.edmundbot.command.claw;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.edmundbot.subsystem.ClawSubsystem;

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
