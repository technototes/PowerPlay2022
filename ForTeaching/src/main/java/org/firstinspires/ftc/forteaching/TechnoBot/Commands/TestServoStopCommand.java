package org.firstinspires.ftc.forteaching.TechnoBot.Commands;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.MovementTestingSubsystem;

public class TestServoStopCommand implements Command {
    MovementTestingSubsystem movement;

    public TestServoStopCommand(MovementTestingSubsystem ss) {
        movement = ss;
        addRequirements(movement);
    }

    @Override
    public void execute() {
            movement.neutralServoMotor();
    }
}
