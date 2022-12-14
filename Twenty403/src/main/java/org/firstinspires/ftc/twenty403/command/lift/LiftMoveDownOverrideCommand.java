package org.firstinspires.ftc.twenty403.command.lift;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class LiftMoveDownOverrideCommand implements Command {

    private LiftSubsystem subsystem;

    public LiftMoveDownOverrideCommand(LiftSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.moveDown_OVERRIDE();
    }
}
