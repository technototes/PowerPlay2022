package org.firstinspires.ftc.twenty403.Controls;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.claw.ClawAutoCloseToggleCommand;
import org.firstinspires.ftc.twenty403.command.drive.DriveCommand;
import org.firstinspires.ftc.twenty403.command.drive.ResetGyroCommand;
import org.firstinspires.ftc.twenty403.command.drive.SlowCommand;
import org.firstinspires.ftc.twenty403.command.drive.TurboCommand;
import org.firstinspires.ftc.twenty403.helpers.BothButtons;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

public class ControlsDriver {
    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraight, turboButton;
    public CommandButton clawToggleAutoCloseButton;
    public BothButtons override;

    public ControlsDriver(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;
        override = new BothButtons(g.leftTrigger.getAsButton(0.5));

        AssignNamedControllerButton();

        if (Robot.RobotConstant.DRIVE_CONNECTED) {
            bindDriveControls();
        }
        if (Robot.RobotConstant.CLAW_CONNECTED) {
            bindClawControls();
        }
        // if (Robot.RobotConstant.LIFT_CONNECTED) {
        //     bindLiftControls();
        // }
    }

    private void AssignNamedControllerButton() {
        resetGyroButton = gamepad.rightStickButton;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;

        turboButton = gamepad.triangle;

        driveStraight = gamepad.rightTrigger.getAsButton(0.5);
        clawToggleAutoCloseButton = gamepad.circle;

        // liftUpButton = gamepad.dpadRight;
        //
        // liftDownButton = gamepad.dpadDown;
        // liftIntakePos = gamepad.dpadLeft;
        //
        // clawOpenButton = gamepad.rightBumper;
        // clawCloseButton = gamepad.leftBumper;
        //
        // liftMedium = gamepad.circle;
        // liftHigh = gamepad.triangle;
        //
        // liftGroundOrOverrideDown = gamepad.cross;
        // liftLowOrOverrideUp = gamepad.square;
        // liftOverrideZeroButton = gamepad.triangle;

        // TODO: Identify other controls for
    }

    public void bindDriveControls() {
        CommandScheduler.getInstance()
                .scheduleJoystick(
                        new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick, driveStraight));
        turboButton.whenPressed(new TurboCommand(robot.drivebaseSubsystem));
        turboButton.whenReleased(new SlowCommand(robot.drivebaseSubsystem));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
    }

    public void bindClawControls() {
        clawToggleAutoCloseButton.whenPressed(new ClawAutoCloseToggleCommand(robot.clawSubsystem));
    }
    //
    // public void bindLiftControls() {
    //     // TODO: Name & Bind lift controls
    //     liftUpButton.whenPressed(new LiftUpCommand(robot.liftSubsystem));
    //     liftDownButton.whenPressed(new LiftDownCommand(robot.liftSubsystem));
    //     liftIntakePos.whenPressed(new LiftIntakeCommand(robot.liftSubsystem));
    //     liftOverrideZeroButton.whenPressed(
    //             new ConditionalCommand(override, new LiftSetZeroCommand(robot.liftSubsystem)));
    //
    //     liftGroundOrOverrideDown.whenPressed(new ConditionalCommand(
    //             override,
    //             new LiftMoveDownOverrideCommand(robot.liftSubsystem),
    //             new LiftGroundJunctionCommand(robot.liftSubsystem)));
    //     liftLowOrOverrideUp.whenPressed(new ConditionalCommand(
    //             override,
    //             new LiftMoveUpOverrideCommand(robot.liftSubsystem),
    //             new LiftLowJunctionCommand(robot.liftSubsystem)));
    //     liftMedium.whenPressed(new LiftMidJunctionCommand(robot.liftSubsystem));
    //     liftHigh.whenPressed(new LiftHighJunctionCommand(robot.liftSubsystem));
    // }
}
