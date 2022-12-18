package org.firstinspires.ftc.swerveteen750;

import org.firstinspires.ftc.swerveteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.swerveteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftFloorIntakeCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftHighPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftLowPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftMidPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftMoveDownOverrideCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftMoveUpOverrideCommand;

import com.technototes.library.control.CommandGamepad;

public class ControlsOperator {
    public Robot robot;
    public CommandGamepad gamepad;

    public ControlsOperator(CommandGamepad g, Robot r, boolean enableLift, boolean enableClaw) {
        this.robot = r;
        this.gamepad = g;

        if (enableLift) {
            bindOperatorLiftControls();
            System.out.println("Binding Lift Controls for Operator");
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
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.CLAW_ENABLED : combo == Robot.SubsystemCombo.CLAW_ONLY
        );
    }

    public void bindOperatorLiftControls() {
        gamepad.leftTrigger.whenPressed(new LiftMoveUpOverrideCommand(robot.liftSubsystem));
        gamepad.rightTrigger.whenPressed(new LiftMoveDownOverrideCommand(robot.liftSubsystem));
    }

    public void bindOperatorClawControls() {
        gamepad.leftBumper.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        gamepad.rightBumper.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
        gamepad.dpadLeft.whenPressed(new LiftLowPoleCommand(robot.liftSubsystem));
        gamepad.dpadRight.whenPressed(new LiftMidPoleCommand(robot.liftSubsystem));
        gamepad.dpadUp.whenPressed(new LiftHighPoleCommand(robot.liftSubsystem));
        gamepad.dpadDown.whenPressed(new LiftFloorIntakeCommand(robot.liftSubsystem));
    }
}
