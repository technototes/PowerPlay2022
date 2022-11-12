package org.firstinspires.ftc.sixteen750.command.lift;

import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.Command;

public class LiftIntakeCommand implements Command {
    public LiftSubsystem liftSubsystem;

    public LiftIntakeCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
    }

    @Override
    public void execute() {
        liftSubsystem.gotoGroundJunction();
    }
}
