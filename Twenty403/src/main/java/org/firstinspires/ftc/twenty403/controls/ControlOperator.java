package org.firstinspires.ftc.twenty403.controls;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.claw.ClawAutoCloseToggleCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
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

import com.technototes.library.command.ConditionalCommand;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

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

        liftMediumOrToggleAutoClose = gamepad.circle;
        liftHighOrOverrideZero = gamepad.triangle;
        liftGroundOrOverrideDown = gamepad.cross;
        liftLowOrOverrideUp = gamepad.square;
    }

    public void bindClawControls() {
        clawOpenButton.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        clawCloseButton.whenReleased(new ClawCloseCommand(robot.clawSubsystem));
    }

    public void bindLiftControls() {
        liftUpButton.whenPressed(new LiftUpCommand(robot.liftSubsystem));
        liftDownButton.whenPressed(new LiftDownCommand(robot.liftSubsystem));
        liftIntakePos.whenPressed(new LiftIntakeCommand(robot.liftSubsystem));
        liftHighOrOverrideZero.whenPressed(new ConditionalCommand(
                override,
                new LiftSetZeroCommand(robot.liftSubsystem),
                new LiftHighJunctionCommand(robot.liftSubsystem)));
        liftMediumOrToggleAutoClose.whenPressed(new ConditionalCommand(
                override,
                new ClawAutoCloseToggleCommand(robot.clawSubsystem),
                new LiftMidJunctionCommand(robot.liftSubsystem)));
        liftGroundOrOverrideDown.whenPressed(new ConditionalCommand(
                override,
                new LiftMoveDownOverrideCommand(robot.liftSubsystem),
                new LiftGroundJunctionCommand(robot.liftSubsystem)));
        liftLowOrOverrideUp.whenPressed(new ConditionalCommand(
                override,
                new LiftMoveUpOverrideCommand(robot.liftSubsystem),
                new LiftLowJunctionCommand(robot.liftSubsystem)));
    }
}
