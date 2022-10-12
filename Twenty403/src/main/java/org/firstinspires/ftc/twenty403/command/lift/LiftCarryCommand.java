package org.firstinspires.ftc.twenty403.command.lift;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.Command;

public class LiftCarryCommand implements Command {
    private LiftSubsystem liftSubsystem;

    public LiftCarryCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
    }

    @Override
    public void execute() {
        liftSubsystem.carry();
    }
}
