package org.firstinspires.ftc.forteaching.TechnoBot.Commands;

import com.technototes.library.command.SimpleCommand;
import com.technototes.library.command.SimpleRequiredCommand;

import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.ClawSubsystem;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.LiftSubsystem;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.MotorAsServoSubsystem;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.MovementTestingSubsystem;

public class Commands {
    public static class Claw {
        public static SimpleRequiredCommand<ClawSubsystem> open(ClawSubsystem clawSubsystem) {
            return new SimpleRequiredCommand<>(clawSubsystem, ClawSubsystem::open);
        }
        public static SimpleRequiredCommand<ClawSubsystem> close(ClawSubsystem clawSubsystem) {
            return new SimpleRequiredCommand<>(clawSubsystem, ClawSubsystem::close);
        }
    }

    public static class Lift {
        public static SimpleRequiredCommand<LiftSubsystem> moveUp(LiftSubsystem lift) {
            return new SimpleRequiredCommand<>(lift, LiftSubsystem::moveUp);
        }
        public static SimpleRequiredCommand<LiftSubsystem> moveDown(LiftSubsystem lift) {
            return new SimpleRequiredCommand<>(lift, LiftSubsystem::moveUp);
        }
    }

    public static class MotorAsServo {
        public static SimpleRequiredCommand<MotorAsServoSubsystem> increaseEncMotor(MotorAsServoSubsystem m) {
            return new SimpleRequiredCommand<>(m, MotorAsServoSubsystem::increaseEncMotor);
        }
        public static SimpleRequiredCommand<MotorAsServoSubsystem> decreaseEncMotor(MotorAsServoSubsystem m) {
            return new SimpleRequiredCommand<>(m, MotorAsServoSubsystem::decreaseEncMotor);
        }
        public static SimpleRequiredCommand<MotorAsServoSubsystem> stop(MotorAsServoSubsystem m) {
            return new SimpleRequiredCommand<>(m, MotorAsServoSubsystem::stop);
        }
        public static SimpleRequiredCommand<MotorAsServoSubsystem> halt(MotorAsServoSubsystem m) {
            return new SimpleRequiredCommand<>(m, MotorAsServoSubsystem::halt);
        }
    }

    public static class MovementTesting {
        public static SimpleRequiredCommand<MovementTestingSubsystem> incMotorSpeed(MovementTestingSubsystem m) {
            return new SimpleRequiredCommand<>(m, MovementTestingSubsystem::increaseMotorSpeed);
        }
        public static SimpleRequiredCommand<MovementTestingSubsystem> decMotorSpeed(MovementTestingSubsystem m) {
            return new SimpleRequiredCommand<>(m, MovementTestingSubsystem::decreaseMotorSpeed);
        }
        public static SimpleRequiredCommand<MovementTestingSubsystem> incServoSpeed(MovementTestingSubsystem m) {
            return new SimpleRequiredCommand<>(m, MovementTestingSubsystem::increaseServoMotor);
        }
        public static SimpleRequiredCommand<MovementTestingSubsystem> decServoSpeed(MovementTestingSubsystem m) {
            return new SimpleRequiredCommand<>(m, MovementTestingSubsystem::decreaseServoMotor);
        }
        public static SimpleCommand neutralMotorSpeed(MovementTestingSubsystem m) {
            return new SimpleCommand(m::neutralMotorSpeed);
        }
        public static SimpleCommand neutralServoSpeed(MovementTestingSubsystem m) {
            return new SimpleCommand(m::neutralServoMotor);
        }
        public static SimpleCommand brakeMotor(MovementTestingSubsystem m) {
            return new SimpleCommand(m::brakeMotor);
        }
    }
}
