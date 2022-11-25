package org.firstinspires.ftc.sixteen750;

import org.firstinspires.ftc.sixteen750.Robot.RobotConstant;
import org.firstinspires.ftc.sixteen750.command.ResetCommandSchedulerCommand;
import org.firstinspires.ftc.sixteen750.command.arm.ArmRetractCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ClawArmReadyToIntakeGroundCommandGroup;
import org.firstinspires.ftc.sixteen750.command.compound.ArmScoreMidJunctionCommandGroup;
import org.firstinspires.ftc.sixteen750.command.arm.ElbowServoIncrementalDownCommand;
import org.firstinspires.ftc.sixteen750.command.arm.ElbowServoIncrementalUpCommand;
import org.firstinspires.ftc.sixteen750.command.arm.FlipperIntakePositionCommand;
import org.firstinspires.ftc.sixteen750.command.arm.FlipperServoIncrementalDownCommand;
import org.firstinspires.ftc.sixteen750.command.arm.FlipperServoIncrementalUpCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftIncrementalMoveDownCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftIncrementalMoveUpCommand;

import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;

public class ControlsOperator {
    public Robot robot;
    public CommandGamepad gamepad;

    public CommandButton liftUpButton, liftDownButton;
    public CommandAxis clawOpenButton, clawCloseButton;
    public CommandButton armRetractButton, readyToScoreButton, scoreMidJunctionButton, flipperIntakePositionButton;
    public CommandButton rumbleTestButton;
    public CommandButton flipperUpButton, flipperDownButton, elbowUpButton, elbowDownButton;

    public ControlsOperator(CommandGamepad g, Robot r) {
        this.robot = r;
        this.gamepad = g;

        assignNamedControllerButton();

        if (RobotConstant.CLAW_ENABLED) {
            bindCoDriverClawControls();
        }
        if (RobotConstant.LIFT_ENABLED) {
            bindCoDriverLiftControls();
        }

        gamepad.leftStickButton.whenPressed(new ResetCommandSchedulerCommand(gamepad));
    }

    public void assignNamedControllerButton() {
        //        this.rumbleTestButton = gamepad.x;
        //        rumbleTestButton.whenPressed(new RumbleTestCommand(gamepad));
    }

    public void bindCoDriverClawControls() {
        //      this.clawOpenButton = gamepad.rightTrigger;
        //      this.clawCloseButton = gamepad.leftTrigger;
        //      clawOpenButton.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        //      clawCloseButton.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
        //      this.armRetractButton = gamepad.triangle;
        //      this.readyToScoreButton = gamepad.square;
        //      this.scoreMidJunctionButton = gamepad.circle;
        //      this.flipperIntakePositionButton = gamepad.x;
        //      armRetractButton.whenPressed(new ArmRetractCommand(robot.clawSubsystem));
        //      readyToScoreButton.whenPressed(new ClawReadyToIntakeGroundCommandGroup(robot.clawSubsystem));
        //      scoreMidJunctionButton.whenPressed(new ClawScoreCommandGroup(robot.clawSubsystem));
        //      flipperIntakePositionButton.whenPressed(new FlipperIntakePositionCommand(robot.clawSubsystem));
        // What if we don't assign them to a variable?
        gamepad.rightTrigger.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        gamepad.leftTrigger.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
        gamepad.triangle.whenPressed(new ArmRetractCommand(robot.armSubsystem));
        gamepad.square.whenPressed(new ClawArmReadyToIntakeGroundCommandGroup(robot.armSubsystem, robot.clawSubsystem));
        gamepad.circle.whenPressed(new ArmScoreMidJunctionCommandGroup(robot.armSubsystem));
        gamepad.x.whenPressed(new FlipperIntakePositionCommand(robot.armSubsystem));

        //      this.flipperDownButton = gamepad.dpadLeft;
        //      this.flipperUpButton = gamepad.dpadRight;
        //      this.elbowDownButton = gamepad.dpadDown;
        //      this.elbowUpButton = gamepad.dpadUp;
        //      flipperDownButton.whenPressed(new FlipperServoIncrementalDownCommand(robot.clawSubsystem));
        //      flipperUpButton.whenPressed(new FlipperServoIncrementalUpCommand(robot.clawSubsystem));
        //      elbowDownButton.whenPressed(new ElbowServoIncrementalDownCommand(robot.clawSubsystem));
        //      elbowUpButton.whenPressed(new ElbowServoIncrementalUpCommand(robot.clawSubsystem));
        gamepad.dpadLeft.whenPressed(new FlipperServoIncrementalDownCommand(robot.armSubsystem));
        gamepad.dpadRight.whenPressed(new FlipperServoIncrementalUpCommand(robot.armSubsystem));
        gamepad.dpadDown.whenPressed(new ElbowServoIncrementalDownCommand(robot.armSubsystem));
        gamepad.dpadUp.whenPressed(new ElbowServoIncrementalUpCommand(robot.armSubsystem));
    }

    public void bindCoDriverLiftControls() {
        //      this.liftDownButton = gamepad.leftBumper;
        //      this.liftUpButton = gamepad.rightBumper;
        //      liftUpButton.whenPressed(new LiftIncrementalMoveUpCommand(robot.liftSubsystem));
        //      liftDownButton.whenPressed(new LiftIncrementalMoveDownCommand(robot.liftSubsystem));
        gamepad.leftBumper.whenPressed(new LiftIncrementalMoveUpCommand(robot.liftSubsystem));
        gamepad.rightBumper.whenPressed(new LiftIncrementalMoveDownCommand(robot.liftSubsystem));
    }
}
