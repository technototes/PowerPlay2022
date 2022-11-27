package org.firstinspires.ftc.sixteen750.command.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

public class LiftMidPoleCommand implements Command {
    public LiftSubsystem liftSubsystem;

    public LiftMidPoleCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
    }

    @Override
    public void execute() {
        liftSubsystem.midPole();
    }
}
