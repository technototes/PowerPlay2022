package org.firstinspires.ftc.forteaching.TechnoBot;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SimpleCommand;
import com.technototes.library.command.SimpleRequiredCommand;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.forteaching.TechnoBot.Commands.MecDriveCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.ClawSubsystem;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.LiftSubsystem;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.MotorAsServoSubsystem;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.MovementTestingSubsystem;

public class Controls {

    public Alliance alliance;
    public CommandGamepad gamepad;
    public TheBot robot;

    public CommandAxis leftTankStick;
    public CommandAxis rightTankStick;

    public Stick leftMecDriveStick;
    public Stick rightMecDriveStick;

    public CommandButton snapToAngle;
    public CommandButton openClaw;
    public CommandButton closeClaw;
    public CommandButton liftUp;
    public CommandButton liftDown;

    public CommandButton servoTestUp;
    public CommandButton servoTestDown;

    public CommandButton motorTestUp;
    public CommandButton motorTestDown;

    public CommandButton encMotorTestUp;
    public CommandButton encMotorTestDown;

    public CommandButton stop;
    public CommandButton halt;

    public Controls(CommandGamepad gpad, TheBot bot, Alliance ally) {
        robot = bot;
        gamepad = gpad;
        alliance = ally;

        // This is where you set up the buttons for what you want them to control:
        leftTankStick = gpad.leftStickY;
        rightTankStick = gpad.rightStickY;

        leftMecDriveStick = gpad.leftStick;
        rightMecDriveStick = gpad.rightStick;
        snapToAngle = gpad.ps_cross;

        servoTestUp = gpad.dpadUp;
        servoTestDown = gpad.dpadDown;

        encMotorTestUp = gpad.dpadLeft;
        encMotorTestDown = gpad.dpadRight;

        motorTestUp = gpad.ps_triangle;
        motorTestDown = gpad.ps_cross;

        stop = gpad.rightBumper;
        halt = gpad.leftBumper;

        openClaw = gpad.ps_circle;
        closeClaw = gpad.ps_triangle;
        liftDown = gpad.dpadDown;
        liftUp = gpad.dpadUp;

        // Now that we've got our controls lined up, we need to configure the controls to behave
        // the way we want them to:
        if (TheBot.Connected.DriveTrain) {
            bindDrivebaseControls();
        }
        if (TheBot.Connected.MovementTesters) {
            bindTesterControls();
        }
        if (TheBot.Connected.Claw) {
            bindClawControls();
        }
        if (TheBot.Connected.Slider) {
            bindLiftControls();
        }
    }

    public void bindClawControls() {
        openClaw.whenPressed(robot.clawSubsystem, ClawSubsystem::open);
        closeClaw.whenPressed(robot.clawSubsystem, ClawSubsystem::close);
    }

    public void bindLiftControls() {
        liftUp.whenPressed(robot.liftSubsystem, LiftSubsystem::moveUp);
        liftDown.whenPressed(robot.liftSubsystem, LiftSubsystem::moveDown);
    }

    // Joysticks require a "scheduleJoystick" thing, so the commands are invoked all the time
    private void bindDrivebaseControls() {
        CommandScheduler

            .scheduleJoystick(
                // new TankDriveCommand(robot.tankDriveBase, leftTankStick, rightTankStick, snapToAngle)
                new MecDriveCommand(robot.mecanumDrivebase, leftMecDriveStick, rightMecDriveStick)
            );
    }

    private void bindTesterControls() {
        encMotorTestUp.whenPressed(robot.encodedMotorSubsystem, MotorAsServoSubsystem::increaseEncMotor);
        encMotorTestDown.whenPressed(robot.encodedMotorSubsystem, MotorAsServoSubsystem::decreaseEncMotor);

        motorTestUp.whenPressed(robot.movementTestingSubsystem, MovementTestingSubsystem::increaseMotorSpeed);
        motorTestDown.whenPressed(robot.movementTestingSubsystem, MovementTestingSubsystem::decreaseMotorSpeed);

        servoTestUp.whenPressed(robot.movementTestingSubsystem, MovementTestingSubsystem::increaseServoMotor);
        servoTestDown.whenPressed(robot.movementTestingSubsystem, MovementTestingSubsystem::decreaseServoMotor);

        // For this stuff, ParallelCommandGroups or SequentialCommandGroups both work the same
        // For command that take "time" you parallel command groups (as they will run at the same
        // time as the other commands) but if you want "do A, then do B, then do C" you should
        // use a sequential command group
        stop.whenPressed(
            new ParallelCommandGroup(
                    new SimpleCommand(robot.movementTestingSubsystem::neutralMotorSpeed),
                    new SimpleCommand(robot.movementTestingSubsystem::neutralServoMotor),
                    new SimpleRequiredCommand<>(robot.encodedMotorSubsystem, MotorAsServoSubsystem::stop)
            )
        );
        halt.whenPressed(
            new ParallelCommandGroup(
                new SimpleCommand(robot.movementTestingSubsystem::brakeMotor),
                new SimpleCommand(robot.movementTestingSubsystem::neutralServoMotor),
                new SimpleRequiredCommand<>(robot.encodedMotorSubsystem, MotorAsServoSubsystem::halt)
            )
        );
    }
}
