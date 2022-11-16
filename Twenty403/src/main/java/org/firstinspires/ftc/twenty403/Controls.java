package org.firstinspires.ftc.twenty403;

import org.firstinspires.ftc.twenty403.Robot.RobotConstant;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.drive.DriveCommand;
import org.firstinspires.ftc.twenty403.command.drive.ResetGyroCommand;
import org.firstinspires.ftc.twenty403.command.drive.SlowCommand;
import org.firstinspires.ftc.twenty403.command.drive.TurboCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftDownCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftGroundJunctionCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftIntakeCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftLowJunctionCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftMidJunctionCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftMoveDownOverrideCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftMoveUpOverrideCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftSetZeroCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftUpCommand;
import org.firstinspires.ftc.twenty403.helpers.BothButtons;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.ConditionalCommand;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

public class Controls {

    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraightenButton, liftUpButton, clawOpenButton;
    public CommandButton liftDownButton, clawCloseButton, liftIntakePos;
    public CommandButton liftGroundOrOverrideDown, liftLow, liftMedium, liftHighOrOverrideUp, liftOverrideZeroButton;
    public CommandButton turboButton;

    public BothButtons override;

    public Controls(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;

        override = new BothButtons(g.leftTrigger.getAsButton(0.5), g.rightTrigger.getAsButton(.5));

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
        liftOverrideZeroButton = gamepad.share;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        turboButton = gamepad.triangle;
        liftUpButton = gamepad.square;
        liftDownButton = gamepad.cross;

        liftIntakePos = gamepad.circle;

        clawOpenButton = gamepad.leftBumper;

        clawCloseButton = gamepad.leftTrigger.getAsButton();

        liftGroundOrOverrideDown = gamepad.dpadDown;

        liftLow = gamepad.dpadLeft;
        liftMedium = gamepad.dpadRight;
        liftHighOrOverrideUp = gamepad.dpadUp;

        // TODO: Identify other controls for drive straighten button
        driveStraightenButton = gamepad.options;
    }

    public void bindDriveControls() {
        CommandScheduler.getInstance()
                .scheduleJoystick(new DriveCommand(
                        robot.drivebaseSubsystem, driveLeftStick, driveRightStick, driveStraightenButton));
        turboButton.whenPressed(new TurboCommand(robot.drivebaseSubsystem));
        turboButton.whenReleased(new SlowCommand(robot.drivebaseSubsystem));
        // TODO: We probably want buttons to reset the Gyro...
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
    }

    public void bindClawControls() {
        // TODO: Name & Bind claw controls
        clawOpenButton.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        clawCloseButton.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
    }

    public void bindLiftControls() {
        // TODO: Name & Bind lift controls
        liftUpButton.whenPressed(new LiftUpCommand(robot.liftSubsystem));
        liftDownButton.whenPressed(new LiftDownCommand(robot.liftSubsystem));
        liftIntakePos.whenPressed(new LiftIntakeCommand(robot.liftSubsystem));

        liftLow.whenPressed(new LiftLowJunctionCommand(robot.liftSubsystem));
        liftGroundOrOverrideDown.whenPressed(new ConditionalCommand(
                override,
                new LiftMoveDownOverrideCommand(robot.liftSubsystem),
                new LiftGroundJunctionCommand(robot.liftSubsystem)));
        liftMedium.whenPressed(new LiftMidJunctionCommand(robot.liftSubsystem));
        liftHighOrOverrideUp.whenPressed(new ConditionalCommand(
                override,
                new LiftMoveUpOverrideCommand(robot.liftSubsystem),
                new LiftHighJunctionCommand(robot.liftSubsystem)));
        liftOverrideZeroButton.whenPressed(
                new ConditionalCommand(override, new LiftSetZeroCommand(robot.liftSubsystem)));
    }
}
