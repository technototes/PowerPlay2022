package org.firstinspires.ftc.forteaching.TechnoBot;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.forteaching.TechnoBot.Commands.CloseClawCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.LiftDownCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.LiftUpCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.OpenClawCommand;
import org.firstinspires.ftc.forteaching.TechnoBot.Commands.TankDriveCommand;

public class Controls {
    public Alliance alliance;
    public CommandGamepad gamepad;
    public TheBot robot;

    public CommandAxis leftTankStick;
    public CommandAxis rightTankStick;
    public CommandButton moveServoLeft;
    public CommandButton moveServoRight;
    public CommandButton snapToAngle;
    public CommandButton openClaw;
    public CommandButton closeClaw;
    public CommandButton liftUp;
    public CommandButton liftDown;

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
        openClaw = gpad.circle;
        closeClaw = gpad.triangle;
        liftDown = gpad.dpadDown;
        liftUp = gpad.dpadUp;

        // Now that we've got our controls lined up, we need to configure the controls to behave
        // the way we want them to:
        if (TheBot.Connected.DriveTrain) {
            bindDrivebaseControls();
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

    private void bindDrivebaseControls() {
        CommandScheduler.getInstance().scheduleJoystick(
                new TankDriveCommand(robot.tankDriveBase, leftTankStick, rightTankStick, snapToAngle));
    }
}
