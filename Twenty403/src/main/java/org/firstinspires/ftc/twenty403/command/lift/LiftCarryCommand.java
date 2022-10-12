package org.firstinspires.ftc.twenty403.command.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

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
