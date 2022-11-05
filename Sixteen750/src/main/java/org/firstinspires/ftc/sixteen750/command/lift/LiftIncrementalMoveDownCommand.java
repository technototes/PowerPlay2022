package org.firstinspires.ftc.sixteen750.command.lift;

import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.Command;

public class LiftIncrementalMoveDownCommand implements Command {
    private LiftSubsystem liftSubsystem;

    public LiftIncrementalMoveDownCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
        addRequirements(ls);
    }

    @Override
    public void execute() {
        liftSubsystem.moveDown();
    }
}
