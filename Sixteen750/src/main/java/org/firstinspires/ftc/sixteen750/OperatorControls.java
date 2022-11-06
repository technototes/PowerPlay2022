package org.firstinspires.ftc.sixteen750;

import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;

import org.firstinspires.ftc.sixteen750.Robot.RobotConstant;
import org.firstinspires.ftc.sixteen750.command.RumbleTestCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawReadyToScoreCommandGroup;
import org.firstinspires.ftc.sixteen750.command.claw.ClawScoreCommandGroup;
import org.firstinspires.ftc.sixteen750.command.claw.FlipperNormalCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftIncrementalMoveDownCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftIncrementalMoveUpCommand;

public class OperatorControls {
    public Robot robot;
    public CommandGamepad gamepad;

    public CommandButton liftUpButton, liftDownButton;
    public CommandAxis clawOpenButton, clawCloseButton;
    public CommandButton normalizeFlipperButton, readyToScoreButton, scoreMidJunctionButton;
    public CommandButton rumbleTestButton;

    public OperatorControls(CommandGamepad g, Robot r) {
        this.robot = r;
        this.gamepad = g;

        assignNamedControllerButton();

        if (RobotConstant.CLAW_CONNECTED) {
            bindCoDriverClawControls();
        }
        if (RobotConstant.LIFT_CONNECTED) {
            bindCoDriverLiftControls();
        }
    }

    public void assignNamedControllerButton() {
        this.rumbleTestButton = gamepad.x;
        rumbleTestButton.whenPressed(new RumbleTestCommand(gamepad));
    }

    public void bindCoDriverClawControls() {
        this.normalizeFlipperButton = gamepad.triangle;
        this.readyToScoreButton = gamepad.square;
        this.scoreMidJunctionButton = gamepad.circle;
        clawOpenButton.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        clawCloseButton.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
        normalizeFlipperButton.whenPressed(new FlipperNormalCommand(robot.clawSubsystem));
        readyToScoreButton.whenPressed(new ClawReadyToScoreCommandGroup(robot.clawSubsystem));
        scoreMidJunctionButton.whenPressed(new ClawScoreCommandGroup(robot.clawSubsystem));
    }

    public void bindCoDriverLiftControls() {
        this.liftDownButton = gamepad.leftBumper;
        this.liftUpButton = gamepad.rightBumper;
        liftUpButton.whenPressed(new LiftIncrementalMoveUpCommand(robot.liftSubsystem));
        liftDownButton.whenPressed(new LiftIncrementalMoveDownCommand(robot.liftSubsystem));
    }
}