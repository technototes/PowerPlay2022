package org.firstinspires.ftc.twenty403.command.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class LiftCarryCommand implements Command {
    private LiftSubsystem subsystem;

    public LiftCarryCommand(LiftSubsystem subsystem) {
        this.subsystem = subsystem;
    }

    @Override
    public void execute() {
        subsystem.carry();
    }
}
