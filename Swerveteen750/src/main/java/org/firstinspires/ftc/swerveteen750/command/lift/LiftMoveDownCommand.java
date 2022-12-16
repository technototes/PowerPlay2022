package org.firstinspires.ftc.swerveteen750.command.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class LiftMoveDownCommand implements Command {
    private LiftSubsystem liftSubsystem;

    public LiftMoveDownCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
        addRequirements(ls);
    }

    @Override
    public void execute() {
        liftSubsystem.moveDown();
        System.out.println("Lift moving down");
    }
}
