package org.firstinspires.ftc.forteaching.TechnoBot.Commands;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.MotorAsServoSubsystem;

public class TestEncMotorDecCommand implements Command {
    MotorAsServoSubsystem motorAsServo;

    public TestEncMotorDecCommand(MotorAsServoSubsystem ss) {
        motorAsServo = ss;
        addRequirements(motorAsServo);
    }
    
    @Override
    public void execute() {
        motorAsServo.decreaseEncMotor();
    }
}
