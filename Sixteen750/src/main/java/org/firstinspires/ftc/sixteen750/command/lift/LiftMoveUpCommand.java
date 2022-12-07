package org.firstinspires.ftc.sixteen750.command.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

public class LiftMoveUpCommand implements Command {
    private LiftSubsystem liftSubsystem;

    public LiftMoveUpCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
        addRequirements(ls);
    }

    @Override
    public void execute() {
        liftSubsystem.moveUp();
        System.out.println("Lift moving up");
    }
}
