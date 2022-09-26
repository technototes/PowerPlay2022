package org.firstinspires.ftc.forteaching.TechnoBot.Commands;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.MovementTestingSubsystem;

public class TestMotorDecCommand implements Command {
    MovementTestingSubsystem movement;

    public TestMotorDecCommand(MovementTestingSubsystem ss) {
        movement = ss;
        addRequirements(movement);
    }
    
    @Override
    public void execute() {
            movement.decreaseMotorSpeed();
    }
}
