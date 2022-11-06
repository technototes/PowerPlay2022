package org.firstinspires.ftc.sixteen750;

import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;

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

    public OperatorControls(CommandGamepad g, Robot r) {
        this.robot = r;
        this.gamepad = g;
    }

    public void assignNamedControllerButton() {
        this.liftDownButton = gamepad.leftBumper;
        this.liftUpButton = gamepad.rightBumper;
        this.normalizeFlipperButton = gamepad.triangle;
        this.readyToScoreButton = gamepad.x;
        this.scoreMidJunctionButton = gamepad.circle;
    }

    public void bindCoDriverClawControls() {
        clawOpenButton.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        clawCloseButton.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
        normalizeFlipperButton.whenPressed(new FlipperNormalCommand(robot.clawSubsystem));
        readyToScoreButton.whenPressed(new ClawReadyToScoreCommandGroup(robot.clawSubsystem));
        scoreMidJunctionButton.whenPressed(new ClawScoreCommandGroup(robot.clawSubsystem));
    }

    public void bindCoDriverLiftControls() {
        liftUpButton.whenPressed(new LiftIncrementalMoveUpCommand(robot.liftSubsystem));
        liftDownButton.whenPressed(new LiftIncrementalMoveDownCommand(robot.liftSubsystem));
    }
}