package org.firstinspires.ftc.twenty403.command.lift;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.Command;

public class LiftMidJunctionCommand implements Command {
    private LiftSubsystem liftSubsystem;

    public LiftMidJunctionCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
    }

    @Override
    public void execute() {
        liftSubsystem.midPole();
    }
}
