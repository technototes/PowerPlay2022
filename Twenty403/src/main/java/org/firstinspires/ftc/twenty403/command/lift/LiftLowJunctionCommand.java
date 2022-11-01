package org.firstinspires.ftc.twenty403.command.lift;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.Command;

public class LiftLowJunctionCommand implements Command {
    private LiftSubsystem liftSubsystem;

    public LiftLowJunctionCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
        addRequirements(ls);
    }

    @Override
    public void execute() {
        liftSubsystem.lowPole();
    }
}
