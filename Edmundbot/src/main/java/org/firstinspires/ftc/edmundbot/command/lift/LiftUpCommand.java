package org.firstinspires.ftc.edmundbot.command.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.edmundbot.subsystem.LiftSubsystem;

public class LiftUpCommand implements Command {

    private LiftSubsystem liftSubsystem;

    public LiftUpCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
        addRequirements(ls);
    }

    @Override
    public void execute() {
        liftSubsystem.moveUp();
    }
}
