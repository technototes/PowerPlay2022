package org.firstinspires.ftc.sixteen750;

import org.firstinspires.ftc.sixteen750.Robot.RobotConstant;
import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;

import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

public class ControlDriver {
    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraightenButton, snailSpeedButton, liftUpButton, clawOpenButton;
    public CommandAxis liftDownButton, clawCloseButton;

    public ControlDriver(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;

        AssignNamedControllerButton();

        if (RobotConstant.DRIVE_CONNECTED) {
            bindDriveControls();
        }
        if (RobotConstant.CLAW_CONNECTED) {
            bindClawControls();
        }
        if (RobotConstant.LIFT_CONNECTED) {
            bindLiftControls();
        }
    }

    private void AssignNamedControllerButton() {
        resetGyroButton = gamepad.rightStickButton;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        driveStraightenButton = gamepad.square;
        liftDownButton = gamepad.rightTrigger;
        liftUpButton = gamepad.rightBumper;
        clawCloseButton = gamepad.leftTrigger;
        clawOpenButton = gamepad.leftBumper;
        // TODO: Identify other controls for
    }

    public void bindDriveControls() {
        // CommandScheduler.getInstance()
        //  .scheduleJoystick(new DriveCommand(
        //      robot.drivebaseSubsystem, driveLeftStick, driveRightStick, driveStraightenButton));
        // TODO: We probably want buttons to reset the Gyro...
        //   resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
        //   snailSpeedButton.whilePressedOnce(new SetSpeedCommand(robot.drivebaseSubsystem));
    }

    public void bindClawControls() {
        // TODO: Name & Bind claw controls
        clawOpenButton.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        clawCloseButton.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
    }

    public void bindLiftControls() {
        // TODO: Name & Bind lift controls
        // liftUpButton.whenPressed(new LiftUpCommand(robot.liftSubsystem));
        // liftDownButton.whenPressed(new LiftDownCommand(robot.liftSubsystem));
    }
}
