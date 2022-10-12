package org.firstinspires.ftc.twenty403.command.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class LiftMidJunctionCommand implements Command {
    private LiftSubsystem name;

    public LiftMidJunctionCommand(LiftSubsystem subsystem) {
        name = subsystem;
    }

    @Override
    public void execute() {
        name.midPole();
    }
}
