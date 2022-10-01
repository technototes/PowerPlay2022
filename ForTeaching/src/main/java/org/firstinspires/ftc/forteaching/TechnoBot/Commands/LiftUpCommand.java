package org.firstinspires.ftc.forteaching.TechnoBot.Commands;

import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.LiftSubsystem;

import com.technototes.library.command.Command;

public class LiftUpCommand implements Command {
    public LiftSubsystem lift;

    public LiftUpCommand(LiftSubsystem liftSubsystem) {
        addRequirements(liftSubsystem);
        lift = liftSubsystem;
    }

    @Override
    public void execute() {
        lift.moveUp();
    }
}
