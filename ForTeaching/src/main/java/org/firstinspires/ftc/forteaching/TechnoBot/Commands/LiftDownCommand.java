package org.firstinspires.ftc.forteaching.TechnoBot.Commands;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.LiftSubsystem;

public class LiftDownCommand implements Command {
    public LiftSubsystem lift;
    public LiftDownCommand(LiftSubsystem liftSubsystem) {
        addRequirements(liftSubsystem);
        lift = liftSubsystem;
    }
    @Override
    public void execute() {
        lift.moveDown();
    }
}
