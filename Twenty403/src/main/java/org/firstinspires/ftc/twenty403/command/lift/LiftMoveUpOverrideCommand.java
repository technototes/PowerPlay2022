package org.firstinspires.ftc.twenty403.command.lift;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.Command;

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
