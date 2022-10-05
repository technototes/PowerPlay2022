package org.firstinspires.ftc.forteaching.TechnoBot.Commands;

import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.MovementTestingSubsystem;

import com.technototes.library.command.Command;

public class TestServoCommand implements Command {
    MovementTestingSubsystem movement;
    Operations which;

    public TestServoCommand(MovementTestingSubsystem ss, Operations op) {
        movement = ss;
        addRequirements(movement);
        which = op;
    }

    @Override
    public void execute() {
        switch (which) {
            case Increase:
                movement.increaseServoMotor();
                break;
            case Decrease:
                movement.decreaseServoMotor();
                break;
            case Stop:
            case Halt:
                movement.neutralServoMotor();
                break;
        }
    }
}
