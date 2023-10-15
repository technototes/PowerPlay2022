package org.firstinspires.ftc.twenty403.controls;

import com.technototes.library.command.ConditionalCommand;
import com.technototes.library.command.SimpleRequiredCommand;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftIntakeCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

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
        clawOpenButton.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        clawCloseButton.whenReleased(new ClawCloseCommand(robot.clawSubsystem));
    }

    public void bindLiftControls() {
        liftUpButton.whenPressed(robot.liftSubsystem, LiftSubsystem::moveUp);
        liftDownButton.whenPressed(robot.liftSubsystem, LiftSubsystem::moveDown);
        liftIntakePos.whenPressed(new LiftIntakeCommand(robot.liftSubsystem));
        liftHighOrOverrideZero.whenPressed(
            new ConditionalCommand(
                override,
                new SimpleRequiredCommand<>(robot.liftSubsystem, LiftSubsystem::setNewZero),
                new SimpleRequiredCommand<>(robot.liftSubsystem, LiftSubsystem::highPole)
            )
        );
        liftMediumOrToggleAutoClose.whenPressed(
            new ConditionalCommand(
                override,
                new SimpleRequiredCommand<>(robot.clawSubsystem, ClawSubsystem::toggleAutoClose),
                new SimpleRequiredCommand<>(robot.liftSubsystem, LiftSubsystem::midPole)
            )
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
    }
}
