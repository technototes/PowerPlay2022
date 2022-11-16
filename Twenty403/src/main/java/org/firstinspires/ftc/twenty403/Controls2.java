package org.firstinspires.ftc.twenty403;

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

public class Controls2 {
    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraighten, turboButton;
    public CommandButton liftDownButton, liftUpButton, clawOpenButton, clawCloseButton, liftOverrideZeroButton;
    public CommandButton liftGroundOrOverrideDown, liftLow, liftMedium, liftHighOrOverrideUp, liftIntakePos;
    public BothButtons override;

    public Controls2(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;
        override = new BothButtons(g.leftTrigger.getAsButton(0.5), g.rightTrigger.getAsButton(0.5));

        AssignNamedControllerButton();

        if (Robot.RobotConstant.DRIVE_CONNECTED) {
            bindDriveControls();
        }
        if (Robot.RobotConstant.CLAW_CONNECTED) {
            bindClawControls();
        }
        if (Robot.RobotConstant.LIFT_CONNECTED) {
            bindLiftControls();
        }
    }

    private void AssignNamedControllerButton() {
        resetGyroButton = gamepad.rightStickButton;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        turboButton = gamepad.leftStickButton;

        liftUpButton = gamepad.dpadRight; // square
        liftDownButton = gamepad.dpadDown; // cross
        liftIntakePos = gamepad.dpadLeft; // circle
        liftOverrideZeroButton = gamepad.share;


        clawOpenButton = gamepad.rightBumper; // left
        clawCloseButton = gamepad.leftBumper; // right


        liftGroundOrOverrideDown = gamepad.cross; // dpadDown
        liftLow = gamepad.square; // dpadLeft
        liftMedium = gamepad.circle; // dpadRight
        liftHighOrOverrideUp = gamepad.triangle; // dpadUp

        driveStraighten = gamepad.options;

        // TODO: Identify other controls for
    }

    public void bindDriveControls() {
        CommandScheduler.getInstance()
                .scheduleJoystick(new DriveCommand(
                        robot.drivebaseSubsystem, driveLeftStick, driveRightStick, driveStraighten));
        turboButton.whenPressed(new TurboCommand(robot.drivebaseSubsystem));
        turboButton.whenReleased(new SlowCommand(robot.drivebaseSubsystem));
        // TODO: We probably want buttons to reset the Gyro...
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
    }

    public void bindClawControls() {
        // TODO: Name & Bind claw controls
        clawOpenButton.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        clawCloseButton.whenReleased(new ClawCloseCommand(robot.clawSubsystem));
    }

    public void bindLiftControls() {
        // TODO: Name & Bind lift controls
        liftUpButton.whenPressed(new LiftUpCommand(robot.liftSubsystem));
        liftDownButton.whenPressed(new LiftDownCommand(robot.liftSubsystem));
        liftIntakePos.whenPressed(new LiftIntakeCommand(robot.liftSubsystem));
        liftOverrideZeroButton.whenPressed(new ConditionalCommand(
                override,
                new LiftSetZeroCommand(robot.liftSubsystem)));


        liftGroundOrOverrideDown.whenPressed(
                new ConditionalCommand(override,
                    new LiftMoveDownOverrideCommand(robot.liftSubsystem),
                    new LiftGroundJunctionCommand(robot.liftSubsystem)));
        liftLow.whenPressed(new LiftLowJunctionCommand(robot.liftSubsystem));
        liftMedium.whenPressed(new LiftMidJunctionCommand(robot.liftSubsystem));
        liftHighOrOverrideUp.whenPressed(
                new ConditionalCommand(override,
                        new LiftMoveUpOverrideCommand(robot.liftSubsystem),
                        new LiftHighJunctionCommand(robot.liftSubsystem)));
    }
}
