package org.firstinspires.ftc.sixteen750.command.lift;

import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.Command;

public class LiftCarryCommand implements Command {
    public LiftSubsystem liftSubsystem;

    public LiftCarryCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
    }

    @Override
    public void execute() {
        liftSubsystem.groundJunction();
    }
}
