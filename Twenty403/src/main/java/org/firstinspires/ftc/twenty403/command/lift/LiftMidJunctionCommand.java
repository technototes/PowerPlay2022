package org.firstinspires.ftc.twenty403.command.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

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
