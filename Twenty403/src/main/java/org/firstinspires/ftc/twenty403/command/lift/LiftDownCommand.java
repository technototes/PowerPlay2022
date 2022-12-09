package org.firstinspires.ftc.twenty403.command.lift;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class LiftDownCommand implements Command {

    private LiftSubsystem liftSubsystem;

    public LiftDownCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
        addRequirements(ls);
    }

    @Override
    public void execute() {
        liftSubsystem.moveDown();
    }
}
