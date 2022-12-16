package org.firstinspires.ftc.swerveteen750.command.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class LiftMoveUpOverrideCommand implements Command {
    private LiftSubsystem liftSubsystem;

    public LiftMoveUpOverrideCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
        addRequirements(ls);
    }

    @Override
    public void execute() {
        liftSubsystem.moveUpOverride();
    }
}
