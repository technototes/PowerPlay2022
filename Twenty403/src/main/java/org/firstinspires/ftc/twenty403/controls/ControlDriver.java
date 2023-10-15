package org.firstinspires.ftc.twenty403.controls;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.drive.DriveCommand;
import org.firstinspires.ftc.twenty403.command.drive.TileMoveCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;

public class ControlDriver {

    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton tileRight, tileLeft, tileUp, tileDown, tileAbort;
    public CommandButton resetGyroButton, driveStraight, turboButton, autoAlign;
    public CommandButton clawToggleAutoCloseButton;
    public CommandButton override;

    public ControlDriver(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;
        override = g.leftTrigger.getAsButton(0.5);

        AssignNamedControllerButton();

        if (Robot.RobotConstant.DRIVE_CONNECTED) {
            bindDriveControls();
        }
        if (Robot.RobotConstant.CLAW_CONNECTED) {
            bindClawControls();
        }
    }

    private void AssignNamedControllerButton() {
        resetGyroButton = gamepad.rightStickButton;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;

        tileDown = gamepad.dpadDown;
        tileUp = gamepad.dpadUp;
        tileLeft = gamepad.dpadLeft;
        tileRight = gamepad.dpadRight;
        tileAbort = gamepad.leftBumper;
        turboButton = gamepad.ps_triangle;
        autoAlign = gamepad.ps_square;

        driveStraight = gamepad.rightTrigger.getAsButton(0.5);
        clawToggleAutoCloseButton = gamepad.ps_circle;
    }

    public void bindDriveControls() {
        CommandScheduler
            .scheduleJoystick(
                new DriveCommand(
                    robot.drivebaseSubsystem,
                    driveLeftStick,
                    driveRightStick,
                    driveStraight,
                    autoAlign
                )
            );
        turboButton.whenPressed(robot.drivebaseSubsystem::fast);
        turboButton.whenReleased(robot.drivebaseSubsystem::slow);
        resetGyroButton.whenPressed(robot.drivebaseSubsystem::setExternalHeading, 0.0);

        tileRight.whenPressed(new TileMoveCommand(robot, TileMoveCommand.TileMoving.Right));
        tileLeft.whenPressed(new TileMoveCommand(robot, TileMoveCommand.TileMoving.Left));
        tileUp.whenPressed(new TileMoveCommand(robot, TileMoveCommand.TileMoving.Up));
        tileDown.whenPressed(new TileMoveCommand(robot, TileMoveCommand.TileMoving.Down));
        tileAbort.whenPressed(robot.drivebaseSubsystem::requestCancelled);
    }

    public void bindClawControls() {
        clawToggleAutoCloseButton.whenPressed(robot.clawSubsystem, ClawSubsystem::toggleAutoClose);
    }
}
