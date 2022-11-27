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

import com.technototes.library.control.CommandGamepad;

public class ControlsOperator {
    public Robot robot;
    public CommandGamepad gamepad;

    public ControlsOperator(CommandGamepad g, Robot r) {
        this.robot = r;
        this.gamepad = g;

        if (RobotConstant.CLAW_ENABLED) {
            bindCoDriverClawControls();
        }
        if (RobotConstant.LIFT_ENABLED) {
            bindCoDriverLiftControls();
        }

        gamepad.leftStickButton.whenPressed(new ResetCommandSchedulerCommand(gamepad));
    }

    public void bindCoDriverClawControls() {
        gamepad.rightTrigger.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        gamepad.leftTrigger.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
        gamepad.triangle.whenPressed(new ArmRetractCommand(robot.armSubsystem));
        gamepad.square.whenPressed(new ClawArmReadyToIntakeGroundCommandGroup(robot.armSubsystem, robot.clawSubsystem));
        gamepad.circle.whenPressed(new ArmScoreMidJunctionCommandGroup(robot.armSubsystem));
        gamepad.x.whenPressed(new FlipperIntakePositionCommand(robot.armSubsystem));

        gamepad.dpadLeft.whenPressed(new FlipperServoIncrementalDownCommand(robot.armSubsystem));
        gamepad.dpadRight.whenPressed(new FlipperServoIncrementalUpCommand(robot.armSubsystem));
        gamepad.dpadDown.whenPressed(new ElbowServoIncrementalDownCommand(robot.armSubsystem));
        gamepad.dpadUp.whenPressed(new ElbowServoIncrementalUpCommand(robot.armSubsystem));
    }

    public void bindCoDriverLiftControls() {
        gamepad.leftBumper.whenPressed(new LiftIncrementalMoveUpCommand(robot.liftSubsystem));
        gamepad.rightBumper.whenPressed(new LiftIncrementalMoveDownCommand(robot.liftSubsystem));
    }
}
