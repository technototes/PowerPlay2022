package org.firstinspires.ftc.edmundbot.command.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.edmundbot.subsystem.LiftSubsystem;

public class LiftMoveUpOverrideCommand implements Command {

    private LiftSubsystem subsystem;

    public LiftMoveUpOverrideCommand(LiftSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.moveUp_OVERRIDE();
    }
}
