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

public class ControlsDriver {
    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraightenButton, snailSpeedButton;
    public CommandAxis clawOpenButton, clawCloseButton;
    public CommandButton liftUpButton, liftDownButton;
    public CommandButton rumbleTestButton;

    public ControlsDriver(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;

        assignNamedControllerButton();

        if (RobotConstant.SWERVE_DRIVE_ENABLED) {
            bindDriveControls();
        }
        if (RobotConstant.CLAW_ENABLED) {
            bindClawControls();
        }
        if (RobotConstant.LIFT_ENABLED) {
            bindLiftControls();
        }
    }

    private void assignNamedControllerButton() {
        // TODO: re-assign buttons
        // resetGyroButton = gamepad.rightStickButton;
        // driveLeftStick = gamepad.leftStick;
        // driveRightStick = gamepad.rightStick;
        // driveStraightenButton = gamepad.square;
        // TODO: Identify other controls for
        // rumbleTestButton = gamepad.x;
        // rumbleTestButton.whenPressed(new RumbleTestCommand(gamepad));
    }

    public void bindDriveControls() {
        // CommandScheduler.getInstance()
        //  .scheduleJoystick(new DriveCommand(
        //      robot.drivebaseSubsystem, driveLeftStick, driveRightStick, driveStraightenButton));
        // TODO: We probably want buttons to reset the Gyro...
        // resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
        // snailSpeedButton.whilePressedOnce(new SetSpeedCommand(robot.drivebaseSubsystem));
    }

    public void bindClawControls() {
//        this.clawCloseButton = gamepad.leftTrigger;
//        this.clawOpenButton = gamepad.rightTrigger;
//        this.clawOpenButton.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
//        this.clawCloseButton.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
        gamepad.leftTrigger.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        gamepad.rightTrigger.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
    }

    public void bindLiftControls() {
//        this.liftUpButton = gamepad.leftBumper;
//        this.liftDownButton = gamepad.rightBumper;
//        this.liftUpButton.whenPressed(new LiftIncrementalMoveUpCommand(robot.liftSubsystem));
//        this.liftDownButton.whenPressed(new LiftIncrementalMoveDownCommand(robot.liftSubsystem));
        gamepad.leftBumper.whenPressed(new LiftIncrementalMoveUpCommand(robot.liftSubsystem));
        gamepad.rightBumper.whenPressed(new LiftIncrementalMoveDownCommand(robot.liftSubsystem));
    }
}
