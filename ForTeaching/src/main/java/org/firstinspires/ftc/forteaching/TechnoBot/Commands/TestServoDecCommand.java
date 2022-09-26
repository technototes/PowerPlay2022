package org.firstinspires.ftc.forteaching.TechnoBot.Commands;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.MovementTestingSubsystem;

public class TestServoDecCommand implements Command {
    MovementTestingSubsystem movement;

    public TestServoDecCommand(MovementTestingSubsystem ss) {
        movement = ss;
        addRequirements(movement);
    }
    
    @Override
    public void execute() {
            movement.decreaseServoMotor();
    }
}
