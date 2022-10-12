package org.firstinspires.ftc.sixteen750.command.lift;

import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.Command;

public class LiftHighPoleCommand implements Command {
    public LiftSubsystem liftSubsystem;

    public LiftHighPoleCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
    }

    @Override
    public void execute() {
        liftSubsystem.highPole();
    }
}
