package org.firstinspires.ftc.sixteen750;

import org.firstinspires.ftc.sixteen750.Robot.RobotConstant;
import org.firstinspires.ftc.sixteen750.command.claw.ArmRetractCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawReadyToIntakeGroundCommandGroup;
import org.firstinspires.ftc.sixteen750.command.claw.ClawScoreCommandGroup;
import org.firstinspires.ftc.sixteen750.command.claw.ElbowServoIncrementalDownCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ElbowServoIncrementalUpCommand;
import org.firstinspires.ftc.sixteen750.command.claw.FlipperIntakePositionCommand;
import org.firstinspires.ftc.sixteen750.command.claw.FlipperServoIncrementalDownCommand;
import org.firstinspires.ftc.sixteen750.command.claw.FlipperServoIncrementalUpCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftIncrementalMoveDownCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftIncrementalMoveUpCommand;

import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;

public class OperatorControls {
    public Robot robot;
    public CommandGamepad gamepad;

    public CommandButton liftUpButton, liftDownButton;
    public CommandAxis clawOpenButton, clawCloseButton;
    public CommandButton armRetractButton, readyToScoreButton, scoreMidJunctionButton, flipperIntakePositionButton;
    public CommandButton rumbleTestButton;
    public CommandButton flipperUpButton, flipperDownButton, elbowUpButton, elbowDownButton;

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
        //        this.rumbleTestButton = gamepad.x;
        //        rumbleTestButton.whenPressed(new RumbleTestCommand(gamepad));
    }

    public void bindCoDriverClawControls() {
        this.clawOpenButton = gamepad.rightTrigger;
        this.clawCloseButton = gamepad.leftTrigger;
        clawOpenButton.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        clawCloseButton.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
        this.armRetractButton = gamepad.triangle;
        this.readyToScoreButton = gamepad.square;
        this.scoreMidJunctionButton = gamepad.circle;
        this.flipperIntakePositionButton = gamepad.x;
        armRetractButton.whenPressed(new ArmRetractCommand(robot.clawSubsystem));
        readyToScoreButton.whenPressed(new ClawReadyToIntakeGroundCommandGroup(robot.clawSubsystem));
        scoreMidJunctionButton.whenPressed(new ClawScoreCommandGroup(robot.clawSubsystem));
        flipperIntakePositionButton.whenPressed(new FlipperIntakePositionCommand(robot.clawSubsystem));

        this.flipperDownButton = gamepad.dpadLeft;
        this.flipperUpButton = gamepad.dpadRight;
        this.elbowDownButton = gamepad.dpadDown;
        this.elbowUpButton = gamepad.dpadUp;
        flipperDownButton.whenPressed(new FlipperServoIncrementalDownCommand(robot.clawSubsystem));
        flipperUpButton.whenPressed(new FlipperServoIncrementalUpCommand(robot.clawSubsystem));
        elbowDownButton.whenPressed(new ElbowServoIncrementalDownCommand(robot.clawSubsystem));
        elbowUpButton.whenPressed(new ElbowServoIncrementalUpCommand(robot.clawSubsystem));
    }

    public void bindCoDriverLiftControls() {
        this.liftDownButton = gamepad.leftBumper;
        this.liftUpButton = gamepad.rightBumper;
        liftUpButton.whenPressed(new LiftIncrementalMoveUpCommand(robot.liftSubsystem));
        liftDownButton.whenPressed(new LiftIncrementalMoveDownCommand(robot.liftSubsystem));
    }
}