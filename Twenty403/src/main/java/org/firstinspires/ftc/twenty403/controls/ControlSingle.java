package org.firstinspires.ftc.twenty403.controls;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.ConditionalCommand;
import com.technototes.library.command.SimpleRequiredCommand;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.drive.DriveCommand;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class ControlSingle {

    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraight, turboButton;
    public CommandButton liftDownButton, liftUpButton, clawOpenButton, clawCloseButton, liftOverrideZeroButton;
    public CommandButton liftGroundOrOverrideDown, liftLowOrOverrideUp, liftMedium, liftHigh, liftIntakePos;
    public CommandButton override;
    public CommandButton watchButton;

    public ControlSingle(CommandGamepad g, Robot r) {
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
        if (Robot.RobotConstant.LIFT_CONNECTED) {
            bindLiftControls();
        }
        if (Robot.RobotConstant.CAMERA_CONNECTED) {
            // TODO: bindAlignControls();
        }
    }

    private void AssignNamedControllerButton() {
        resetGyroButton = gamepad.rightStickButton;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        turboButton = gamepad.leftStickButton;
        driveStraight = gamepad.rightTrigger.getAsButton(0.5);

        liftUpButton = gamepad.dpadRight;

        liftDownButton = gamepad.dpadDown;
        liftIntakePos = gamepad.dpadLeft;

        clawOpenButton = gamepad.rightBumper;
        clawCloseButton = gamepad.leftBumper;

        liftMedium = gamepad.ps_circle;
        liftHigh = gamepad.ps_triangle;

        liftGroundOrOverrideDown = gamepad.ps_cross;
        liftLowOrOverrideUp = gamepad.ps_square;
        liftOverrideZeroButton = gamepad.ps_triangle;
        watchButton = gamepad.ps_options;
        // TODO: Identify other controls for
    }

    public void bindDriveControls() {
        CommandScheduler
            .scheduleJoystick(
                new DriveCommand(
                    robot.drivebaseSubsystem,
                    driveLeftStick,
                    driveRightStick,
                    driveStraight,
                    watchButton,
                    robot.visionSystem.visionPipeline
                )
            );
        turboButton.whenPressed(robot.drivebaseSubsystem::fast);
        turboButton.whenReleased(robot.drivebaseSubsystem::slow);
        // TODO: We probably want buttons to reset the Gyro...
        resetGyroButton.whenPressed(robot.drivebaseSubsystem::setExternalHeading, 0.0);
    }

    public void bindClawControls() {
        // TODO: Name & Bind claw controls
        clawOpenButton.whenPressed(robot.clawSubsystem.openCommand);
        clawCloseButton.whenReleased(robot.clawSubsystem.closeCommand);
    }

    public void bindLiftControls() {
        // TODO: Name & Bind lift controls
        liftUpButton.whenPressed(robot.liftSubsystem, LiftSubsystem::moveUp);
        liftDownButton.whenPressed(robot.liftSubsystem, LiftSubsystem::moveDown);
        liftIntakePos.whenPressed(robot.liftSubsystem.intakeCommand);
        liftOverrideZeroButton.whenPressed(
            new ConditionalCommand(override, new SimpleRequiredCommand<>(robot.liftSubsystem, LiftSubsystem::setNewZero))
        );

        liftGroundOrOverrideDown.whenPressed(
            new ConditionalCommand(
                override,
                new SimpleRequiredCommand<>(robot.liftSubsystem, LiftSubsystem::moveDown_OVERRIDE),
                new SimpleRequiredCommand<>(robot.liftSubsystem, LiftSubsystem::groundJunction)
            )
        );
        liftLowOrOverrideUp.whenPressed(
            new ConditionalCommand(
                override,
                new SimpleRequiredCommand<>(robot.liftSubsystem, LiftSubsystem::moveUp_OVERRIDE),
                new SimpleRequiredCommand<>(robot.liftSubsystem, LiftSubsystem::lowPole)
            )
        );
        liftMedium.whenPressed(robot.liftSubsystem, LiftSubsystem::midPole);
        liftHigh.whenPressed(robot.liftSubsystem.highCommand);
    }
}
