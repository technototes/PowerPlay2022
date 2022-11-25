package org.firstinspires.ftc.twenty403.Controls;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.drive.DriveCommand;
import org.firstinspires.ftc.twenty403.command.drive.ResetGyroCommand;
import org.firstinspires.ftc.twenty403.command.drive.SlowCommand;
import org.firstinspires.ftc.twenty403.command.drive.TurboCommand;
import org.firstinspires.ftc.twenty403.helpers.BothButtons;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

public class ControlsDriver {
    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraighten, turboButton;
    public CommandButton liftDownButton, liftUpButton, clawOpenButton, clawCloseButton, liftOverrideZeroButton;
    public CommandButton liftGroundOrOverrideDown, liftLowOrOverrideUp, liftMedium, liftHigh, liftIntakePos;
    public CommandAxis driveStraight;
    public BothButtons override;

    public ControlsDriver(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;
        override = new BothButtons(g.leftTrigger.getAsButton(0.5));

        AssignNamedControllerButton();

        if (Robot.RobotConstant.DRIVE_CONNECTED) {
            bindDriveControls();
        }
//        if (Robot.RobotConstant.CLAW_CONNECTED) {
//            bindClawControls();
//        }
//        if (Robot.RobotConstant.LIFT_CONNECTED) {
//            bindLiftControls();
//        }
    }

    private void AssignNamedControllerButton() {
        resetGyroButton = gamepad.rightStickButton;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        turboButton = gamepad.leftStickButton;
        driveStraight = gamepad.rightTrigger;

        liftUpButton = gamepad.dpadRight;

        liftDownButton = gamepad.dpadDown;
        liftIntakePos = gamepad.dpadLeft;

        clawOpenButton = gamepad.rightBumper;
        clawCloseButton = gamepad.leftBumper;

        liftMedium = gamepad.circle;
        liftHigh = gamepad.triangle;

        liftGroundOrOverrideDown = gamepad.cross;
        liftLowOrOverrideUp = gamepad.square;
        liftOverrideZeroButton = gamepad.triangle;


        // TODO: Identify other controls for
    }

    public void bindDriveControls() {
        CommandScheduler.getInstance()
                .scheduleJoystick(
                        new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick, driveStraight));
        turboButton.whenPressed(new TurboCommand(robot.drivebaseSubsystem));
        turboButton.whenReleased(new SlowCommand(robot.drivebaseSubsystem));
        // TODO: We probably want buttons to reset the Gyro...
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
    }

//    public void bindClawControls() {
//        // TODO: Name & Bind claw controls
//        clawOpenButton.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
//        clawCloseButton.whenReleased(new ClawCloseCommand(robot.clawSubsystem));
//    }
//
//    public void bindLiftControls() {
//        // TODO: Name & Bind lift controls
//        liftUpButton.whenPressed(new LiftUpCommand(robot.liftSubsystem));
//        liftDownButton.whenPressed(new LiftDownCommand(robot.liftSubsystem));
//        liftIntakePos.whenPressed(new LiftIntakeCommand(robot.liftSubsystem));
//        liftOverrideZeroButton.whenPressed(
//                new ConditionalCommand(override, new LiftSetZeroCommand(robot.liftSubsystem)));
//
//        liftGroundOrOverrideDown.whenPressed(new ConditionalCommand(
//                override,
//                new LiftMoveDownOverrideCommand(robot.liftSubsystem),
//                new LiftGroundJunctionCommand(robot.liftSubsystem)));
//        liftLowOrOverrideUp.whenPressed(new ConditionalCommand(
//                override,
//                new LiftMoveUpOverrideCommand(robot.liftSubsystem),
//                new LiftLowJunctionCommand(robot.liftSubsystem)));
//        liftMedium.whenPressed(new LiftMidJunctionCommand(robot.liftSubsystem));
//        liftHigh.whenPressed(new LiftHighJunctionCommand(robot.liftSubsystem));
//    }
}
