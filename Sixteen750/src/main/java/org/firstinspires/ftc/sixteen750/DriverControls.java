package org.firstinspires.ftc.sixteen750;

import org.firstinspires.ftc.sixteen750.Robot.RobotConstant;
import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftIncrementalMoveDownCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftIncrementalMoveUpCommand;

import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

public class DriverControls {
    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraightenButton, snailSpeedButton;
    public CommandAxis clawOpenButton, clawCloseButton;
    public CommandButton liftUpButton, liftDownButton;

    public DriverControls(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;

        AssignNamedControllerButton();

        if (RobotConstant.SWERVE_DRIVE_CONNECTED) {
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
        // TODO: re-assign buttons
        resetGyroButton = gamepad.rightStickButton;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        driveStraightenButton = gamepad.square;
        liftUpButton = gamepad.leftBumper;
        liftDownButton = gamepad.rightBumper;
        clawCloseButton = gamepad.leftTrigger;
        clawOpenButton = gamepad.rightTrigger;
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
        liftUpButton.whenPressed(new LiftIncrementalMoveUpCommand(robot.liftSubsystem));
        liftDownButton.whenPressed(new LiftIncrementalMoveDownCommand(robot.liftSubsystem));
    }
}