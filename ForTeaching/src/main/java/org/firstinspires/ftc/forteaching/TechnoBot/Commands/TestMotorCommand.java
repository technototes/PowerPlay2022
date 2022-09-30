package org.firstinspires.ftc.forteaching.TechnoBot.Commands;

import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.MovementTestingSubsystem;

import com.technototes.library.command.Command;

public class TestMotorCommand implements Command {

    MovementTestingSubsystem movement;
    Operations which;

    public TestMotorCommand(MovementTestingSubsystem ss, Operations op) {
        movement = ss;
        addRequirements(movement);
        which = op;
    }

    @Override
    public void execute() {
        switch (which) {
            case Increase:
                movement.increaseMotorSpeed();
                break;
            case Decrease:
                movement.decreaseMotorSpeed();
                break;
            case Stop:
                movement.neutralMotorSpeed();
                break;
            case Halt:
                movement.brakeMotor();
                break;
        }
    }
}
