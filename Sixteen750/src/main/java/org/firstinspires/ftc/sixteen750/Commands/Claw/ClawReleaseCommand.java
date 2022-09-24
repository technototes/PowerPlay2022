package org.firstinspires.ftc.sixteen750.Commands.Claw;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

public class ClawReleaseCommand implements Command {
    private ClawSubsystem subsystem;

    public ClawReleaseCommand (ClawSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.release();
    }

}
