package org.firstinspires.ftc.forteaching.TechnoBot;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.forteaching.TechnoBot.Commands.Operations;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.CloseClawCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.LiftDownCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.LiftUpCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.OpenClawCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TankDriveCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TestEncodedMotorCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TestMotorCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TestServoCommand;

public class Controls {
    public Alliance alliance;
    public CommandGamepad gamepad;
    public TheBot robot;

    public CommandAxis leftTankStick;
    public CommandAxis rightTankStick;
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
        snapToAngle = gpad.cross;

        servoTestUp = gpad.dpadUp;
        servoTestDown = gpad.dpadDown;

        encMotorTestUp = gpad.dpadLeft;
        encMotorTestDown = gpad.dpadRight;

        motorTestUp = gpad.triangle;
        motorTestDown = gpad.cross;

        stop = gpad.rightBumper;
        halt = gpad.leftBumper;

        openClaw = gpad.circle;
        closeClaw = gpad.triangle;
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
        openClaw.whenPressed(new OpenClawCommand(robot.clawSubsystem));
        closeClaw.whenPressed(new CloseClawCommand(robot.clawSubsystem));
    }

    public void bindLiftControls() {
        liftUp.whenPressed(new LiftUpCommand(robot.liftSubsystem));
        liftDown.whenPressed(new LiftDownCommand(robot.liftSubsystem));
    }

    // Joysticks require a "scheduleJoystick" thing, so the commands are invoked all the time
    private void bindDrivebaseControls() {
        CommandScheduler.getInstance().scheduleJoystick(
                new TankDriveCommand(robot.tankDriveBase, leftTankStick, rightTankStick, snapToAngle));
    }

    // Silly helpers to make the code more succinct below
    private TestEncodedMotorCommand MakeEncCommand(Operations op) {
        return new TestEncodedMotorCommand(robot.encodedMotorSubsystem, op);
    }

    private TestMotorCommand MakeMotorCommand(Operations op) {
        return new TestMotorCommand(robot.movementTestingSubsystem, op);
    }

    private TestServoCommand MakeServoCommand(Operations op) {
        return new TestServoCommand(robot.movementTestingSubsystem, op);
    }

    private void bindTesterControls() {
        encMotorTestUp.whenPressed(MakeEncCommand(Operations.Increase));
        encMotorTestDown.whenPressed(MakeEncCommand(Operations.Decrease));

        motorTestUp.whenPressed(MakeMotorCommand(Operations.Increase));
        motorTestDown.whenPressed(MakeMotorCommand(Operations.Decrease));

        servoTestUp.whenPressed(MakeServoCommand(Operations.Increase));
        servoTestDown.whenPressed(MakeServoCommand(Operations.Decrease));

        // For this stuff, ParallelCommandGroups or SequentialCommandGroups both work the same
        // For command that take "time" you parallel command groups (as they will run at the same
        // time as the other commands) but if you want "do A, then do B, then do C" you should
        // use a sequential command group
        stop.whenPressed(new ParallelCommandGroup(
                MakeEncCommand(Operations.Stop),
                MakeMotorCommand(Operations.Stop),
                MakeServoCommand(Operations.Stop)));
        halt.whenPressed(new ParallelCommandGroup(
                MakeMotorCommand(Operations.Halt),
                MakeEncCommand(Operations.Halt),
                MakeServoCommand(Operations.Halt)));

    }
}
