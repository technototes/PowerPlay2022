package org.firstinspires.ftc.forteaching.TechnoBot.Commands;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.MotorAsServoSubsystem;

public class TestEncodedMotorCommand implements Command {

    MotorAsServoSubsystem motorAsServo;
    Operations which;

    public TestEncodedMotorCommand(MotorAsServoSubsystem ss, Operations op) {
        motorAsServo = ss;
        addRequirements(motorAsServo);
        which = op;
    }

    @Override
    public void execute() {
        if (which == Operations.Decrease) {
            motorAsServo.decreaseEncMotor();
        } else if (which == Operations.Increase) {
            motorAsServo.increaseEncMotor();
        } else if (which == Operations.Stop) {
            motorAsServo.stopEncMotor();
        } else if (which == Operations.Halt) {
            motorAsServo.halt();
        } else {
            // Shouldn't ever get here:
            motorAsServo.halt();
        }
    }
}
