package org.firstinspires.ftc.forteaching.TechnoBot.Commands;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.MotorAsServoSubsystem;

public class TestEncMotorStopCommand implements Command {
    MotorAsServoSubsystem movement;

    public TestEncMotorStopCommand(MotorAsServoSubsystem ss) {
        movement = ss;
        addRequirements(movement);
    }

    @Override
    public void execute() {
            movement.stopEncMotor();
    }
}
