package org.firstinspires.ftc.sixteen750;

import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftIncrementalMoveDownCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftIncrementalMoveUpCommand;
import org.firstinspires.ftc.sixteen750.command.lift2.Lift2GroundJunction;
import org.firstinspires.ftc.sixteen750.command.lift2.Lift2HighJunction;
import org.firstinspires.ftc.sixteen750.command.lift2.Lift2LowJunction;
import org.firstinspires.ftc.sixteen750.command.lift2.Lift2MidJunction;
import org.firstinspires.ftc.sixteen750.command.lift2.Lift2MoveDown;
import org.firstinspires.ftc.sixteen750.command.lift2.Lift2MoveUp;

public class ControlsLift2 {
    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraightenButton, snailSpeedButton;
    public CommandAxis clawOpenButton, clawCloseButton;
    public CommandButton liftHighJunction, liftMidJunction, liftLowJunction, liftGroundJunction, liftMoveUp, liftMoveDown;
    public ControlsLift2(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;

        assignNamedControllerButton();

        if (Robot.RobotConstant.SWERVE_DRIVE_ENABLED) {
            bindDriveControls();
        }
        if (Robot.RobotConstant.CLAW_ENABLED) {
            bindClawControls();
        }
        if (Robot.RobotConstant.LIFT_ENABLED) {
            bindLiftControls();
        }
    }

    private void assignNamedControllerButton() {
        // TODO: re-assign buttons
        liftHighJunction = gamepad.dpadUp;
        liftMidJunction = gamepad.dpadRight;
        liftLowJunction = gamepad.dpadLeft;
        liftGroundJunction = gamepad.dpadDown;
        liftMoveUp = gamepad.leftBumper;
        liftMoveDown = gamepad.rightBumper;
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
        liftMoveUp.whenPressed(new Lift2MoveUp(robot.liftSubsystem2));
        liftMoveDown.whenPressed(new Lift2MoveDown(robot.liftSubsystem2));
        liftHighJunction.whenPressed(new Lift2HighJunction(robot.liftSubsystem2));
        liftMidJunction.whenPressed(new Lift2MidJunction(robot.liftSubsystem2));
        liftLowJunction.whenPressed(new Lift2LowJunction(robot.liftSubsystem2));
        liftGroundJunction.whenPressed(new Lift2GroundJunction(robot.liftSubsystem2));

    }
}
