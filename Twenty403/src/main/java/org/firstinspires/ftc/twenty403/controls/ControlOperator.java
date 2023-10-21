package org.firstinspires.ftc.twenty403.controls;

import com.technototes.library.command.ConditionalCommand;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.Commands;

public class ControlOperator {

    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraighten, turboButton;
    public CommandButton liftDownButton, liftUpButton, clawOpenButton, clawCloseButton, liftHighOrOverrideZero;
    public CommandButton liftGroundOrOverrideDown, liftLowOrOverrideUp, liftMediumOrToggleAutoClose, liftIntakePos;
    public CommandButton override;

    public ControlOperator(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;
        override = g.leftTrigger.getAsButton(0.5);

        AssignNamedControllerButton();

        if (Robot.RobotConstant.CLAW_CONNECTED) {
            bindClawControls();
        }
        if (Robot.RobotConstant.LIFT_CONNECTED) {
            bindLiftControls();
        }
    }

    private void AssignNamedControllerButton() {
        liftUpButton = gamepad.dpadRight;
        liftDownButton = gamepad.dpadDown;
        liftIntakePos = gamepad.dpadLeft;

        clawOpenButton = gamepad.rightBumper;
        clawCloseButton = gamepad.leftBumper;

        liftMediumOrToggleAutoClose = gamepad.ps_circle;
        liftHighOrOverrideZero = gamepad.ps_triangle;
        liftGroundOrOverrideDown = gamepad.ps_cross;
        liftLowOrOverrideUp = gamepad.ps_square;
    }

    public void bindClawControls() {
        clawOpenButton.whenPressed(Commands.Claw.open(robot.clawSubsystem));
        clawCloseButton.whenReleased(Commands.Claw.close(robot.clawSubsystem));
    }

    public void bindLiftControls() {
        liftUpButton.whenPressed(Commands.Lift.moveUp(robot.liftSubsystem));
        liftDownButton.whenPressed(Commands.Lift.moveDown(robot.liftSubsystem));
        liftIntakePos.whenPressed(Commands.Lift.intake(robot.liftSubsystem));
        liftHighOrOverrideZero.whenPressed(
            new ConditionalCommand(
                override,
                Commands.Lift.setNewZero(robot.liftSubsystem),
                Commands.Lift.highJunction(robot.liftSubsystem)
            )
        );
        liftMediumOrToggleAutoClose.whenPressed(
            new ConditionalCommand(
                override,
                Commands.Claw.toggleAutoClose(robot.clawSubsystem),
                Commands.Lift.midJunction(robot.liftSubsystem)
            )
        );
        liftGroundOrOverrideDown.whenPressed(
            new ConditionalCommand(
                override,
                Commands.Lift.moveDown_OVERRIDE(robot.liftSubsystem),
                Commands.Lift.groundJunction(robot.liftSubsystem)
            )
        );
        liftLowOrOverrideUp.whenPressed(
            new ConditionalCommand(
                override,
                Commands.Lift.moveUp_OVERRIDE(robot.liftSubsystem),
                Commands.Lift.lowJunction(robot.liftSubsystem)
            )
        );
    }
}
