package org.firstinspires.ftc.swerveteen750;

import org.firstinspires.ftc.swerveteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.swerveteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.swerveteen750.command.arm.ElbowServoIncrementalDownCommand;
import org.firstinspires.ftc.swerveteen750.command.arm.ElbowServoIncrementalUpCommand;
import org.firstinspires.ftc.swerveteen750.command.arm.FlipperServoIncrementalDownCommand;
import org.firstinspires.ftc.swerveteen750.command.arm.FlipperServoIncrementalUpCommand;
import org.firstinspires.ftc.swerveteen750.command.compound.ArmIntakeCommand;
import org.firstinspires.ftc.swerveteen750.command.compound.ArmScoreCommand;
import org.firstinspires.ftc.swerveteen750.command.compound.ArmUpwardCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftMoveDownOverrideCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftMoveUpOverrideCommand;

import com.technototes.library.control.CommandGamepad;

public class ControlsOperator {
    public Robot robot;
    public CommandGamepad gamepad;

    public ControlsOperator(CommandGamepad g, Robot r, boolean enableLift, boolean enableArm, boolean enableClaw) {
        this.robot = r;
        this.gamepad = g;

        if (enableLift) {
            bindOperatorArmControls();
            System.out.println("Binding Lift Controls for Operator");
        }
        if (enableArm) {
            bindOperatorArmControls();
            System.out.println("Binding Arm Controls for Operator");
        }
        if (enableClaw) {
            bindOperatorClawControls();
            System.out.println("Binding Claw Controls for Operator");
        }

//        gamepad.leftStickButton.whenPressed(new ResetCommandSchedulerCommand(gamepad));
    }

    public ControlsOperator(CommandGamepad g, Robot r, Robot.SubsystemCombo combo){
        this(
                g,
                r,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.LIFT_ENABLED : combo == Robot.SubsystemCombo.LIFT_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.ARM_ENABLED : combo == Robot.SubsystemCombo.ARM_CLAW_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.CLAW_ENABLED : combo == Robot.SubsystemCombo.ARM_CLAW_ONLY
        );
    }

    public void bindOperatorLiftControls() {
        gamepad.leftTrigger.whenPressed(new LiftMoveUpOverrideCommand(robot.liftSubsystem));
        gamepad.rightTrigger.whenPressed(new LiftMoveDownOverrideCommand(robot.liftSubsystem));
    }

    public void bindOperatorClawControls() {
        gamepad.leftBumper.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        gamepad.rightBumper.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
    }

    public void bindOperatorArmControls() {
        gamepad.dpadLeft.whenPressed(new FlipperServoIncrementalDownCommand(robot.armSubsystem));
        gamepad.dpadRight.whenPressed(new FlipperServoIncrementalUpCommand(robot.armSubsystem));
        gamepad.dpadDown.whenPressed(new ElbowServoIncrementalDownCommand(robot.armSubsystem));
        gamepad.dpadUp.whenPressed(new ElbowServoIncrementalUpCommand(robot.armSubsystem));

        gamepad.square.whenPressed(new ArmScoreCommand(robot.armSubsystem));
        gamepad.triangle.whenPressed(new ArmUpwardCommand(robot.armSubsystem));
        gamepad.circle.whenPressed(new ArmIntakeCommand(robot.armSubsystem));
    }
}
