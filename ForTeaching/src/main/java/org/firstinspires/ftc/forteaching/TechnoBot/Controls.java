package org.firstinspires.ftc.forteaching.TechnoBot;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TankDriveCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TestEncMotorDecCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TestEncMotorIncCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TestEncMotorStopCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TestMotorDecCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TestMotorIncCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TestMotorStopCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TestServoDecCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TestServoIncCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TestServoStopCommand;

public class Controls {
    public Alliance alliance;
    public CommandGamepad gamepad;
    public TheBot robot;

    public CommandAxis leftTankStick;
    public CommandAxis rightTankStick;
    public CommandButton moveServoLeft;
    public CommandButton moveServoRight;
    public CommandButton snapToAngle;

    public CommandButton servoTestUp;
    public CommandButton servoTestDown;
    public CommandButton servoTestStop;
    public CommandButton motorTestUp;
    public CommandButton motorTestDown;
    public CommandButton motorTestStop;
    public CommandButton encMotorTestUp;
    public CommandButton encMotorTestDown;
    public CommandButton encMotorTestStop;

    public Controls(CommandGamepad gpad, TheBot bot, Alliance ally) {
        robot = bot;
        gamepad = gpad;
        alliance = ally;
        // This is where you set up the buttons for what you want them to control:
        leftTankStick = gpad.leftStickY;
        rightTankStick = gpad.rightStickY;
        moveServoLeft = gpad.leftBumper;
        moveServoRight = gpad.rightBumper;
        snapToAngle = gpad.cross;

        servoTestUp = gpad.dpadUp;
        servoTestDown = gpad.dpadDown;
        servoTestStop = gpad.square;
        encMotorTestUp = gpad.dpadLeft;
        encMotorTestDown = gpad.dpadRight;
        encMotorTestStop = gpad.circle;
        motorTestUp = gpad.triangle;
        motorTestDown = gpad.cross;
        motorTestStop = gpad.rightBumper;

        // Now that we've got our controls lined up, we need to configure the controls to behave
        // the way we want them to:
        if (TheBot.Connected.DriveTrain) {
            bindDrivebaseControls();
        }
        if (TheBot.Connected.MovementTesters) {
            bindTesterControls();
        }
    }

    private void bindDrivebaseControls() {
        CommandScheduler.getInstance().scheduleJoystick(
                new TankDriveCommand(robot.tankDriveBase, leftTankStick, rightTankStick, snapToAngle));
    }

    private void bindTesterControls() {
        encMotorTestUp.whenPressed(new TestEncMotorIncCommand(robot.encodedMotorSubsystem));
        encMotorTestDown.whenPressed(new TestEncMotorDecCommand(robot.encodedMotorSubsystem));
        encMotorTestStop.whenPressed(new TestEncMotorStopCommand(robot.encodedMotorSubsystem));
        motorTestUp.whenPressed(new TestMotorIncCommand(robot.movementTestingSubsystem));
        motorTestDown.whenPressed(new TestMotorDecCommand(robot.movementTestingSubsystem));
        motorTestStop.whenPressed(new TestMotorStopCommand(robot.movementTestingSubsystem));
        servoTestUp.whenPressed(new TestServoIncCommand(robot.movementTestingSubsystem));
        servoTestDown.whenPressed(new TestServoDecCommand(robot.movementTestingSubsystem));
        servoTestStop.whenPressed(new TestServoStopCommand(robot.movementTestingSubsystem));
    }
}
