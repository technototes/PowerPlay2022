package org.firstinspires.ftc.sixteen750;

import org.firstinspires.ftc.sixteen750.Robot.RobotConstant;
import org.firstinspires.ftc.sixteen750.command.ResetCommandSchedulerCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.arm.ElbowServoIncrementalDownCommand;
import org.firstinspires.ftc.sixteen750.command.arm.ElbowServoIncrementalUpCommand;
import org.firstinspires.ftc.sixteen750.command.arm.FlipperServoIncrementalDownCommand;
import org.firstinspires.ftc.sixteen750.command.arm.FlipperServoIncrementalUpCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ArmIntakeCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ArmScoreCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ArmUpwardCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftMoveDownCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftMoveUpCommand;

import com.technototes.library.control.CommandGamepad;

public class ControlsOperator {
    public Robot robot;
    public CommandGamepad gamepad;

    public ControlsOperator(CommandGamepad g, Robot r, boolean enableLift, boolean enableArm, boolean enableClaw) {
        this.robot = r;
        this.gamepad = g;

        if (enableLift) {
            bindCoDriverLiftControls();
        }
        if (enableArm) {
            bindCoDriverArmControls();
        }
        if (enableClaw) {
            bindCoDriverClawControls();
        }

        gamepad.leftStickButton.whenPressed(new ResetCommandSchedulerCommand(gamepad));
    }

    public ControlsOperator(CommandGamepad g, Robot r, Robot.SubsystemCombo combo){
        this(
                g,
                r,
                combo == Robot.SubsystemCombo.DEFAULT ? RobotConstant.LIFT_ENABLED : combo == Robot.SubsystemCombo.LIFT_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? RobotConstant.ARM_ENABLED : combo == Robot.SubsystemCombo.ARM_CLAW_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? RobotConstant.CLAW_ENABLED : combo == Robot.SubsystemCombo.ARM_CLAW_ONLY
        );
    }

    public void bindCoDriverClawControls() {
        gamepad.rightTrigger.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        gamepad.leftTrigger.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
    }

    public void bindCoDriverArmControls() {
        gamepad.dpadLeft.whenPressed(new FlipperServoIncrementalDownCommand(robot.armSubsystem));
        gamepad.dpadRight.whenPressed(new FlipperServoIncrementalUpCommand(robot.armSubsystem));
        gamepad.dpadDown.whenPressed(new ElbowServoIncrementalDownCommand(robot.armSubsystem));
        gamepad.dpadUp.whenPressed(new ElbowServoIncrementalUpCommand(robot.armSubsystem));

        gamepad.square.whenPressed(new ArmScoreCommand(robot.armSubsystem));
        gamepad.triangle.whenPressed(new ArmUpwardCommand(robot.armSubsystem));
        gamepad.circle.whenPressed(new ArmIntakeCommand(robot.armSubsystem));
    }

    public void bindCoDriverLiftControls() {
        gamepad.leftBumper.whenPressed(new LiftMoveUpCommand(robot.liftSubsystem));
        gamepad.rightBumper.whenPressed(new LiftMoveDownCommand(robot.liftSubsystem));
    }
}
