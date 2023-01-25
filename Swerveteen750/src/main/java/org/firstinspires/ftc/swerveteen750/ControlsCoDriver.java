package org.firstinspires.ftc.swerveteen750;

import com.technototes.library.control.CommandGamepad;

import org.firstinspires.ftc.swerveteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.swerveteen750.command.claw.ClawFlatCommand;
import org.firstinspires.ftc.swerveteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftFloorIntakeCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftHighPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftLowPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftMidPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftMoveDownOverrideCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftMoveUpOverrideCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.IncrementTurretDownCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.IncrementTurretUpCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.TurretFrontCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.TurretRearCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.TurretLeftCommand;

public class ControlsCoDriver {
    public Robot robot;
    public CommandGamepad gamepad;

    public ControlsCoDriver(CommandGamepad g,
                            Robot r,
                            boolean enableLift,
                            boolean enableClaw){
        this.robot = r;
        this.gamepad = g;

        if (enableLift){
            bindCoDriverLiftControls();
            System.out.println("Binding Lift Control for CoDriver");
        }

        if (enableClaw){
            bindCoDriverClawControls();
            System.out.println("Binding Claw Control for CoDriver");
        }
    }

    public ControlsCoDriver(CommandGamepad g, Robot r, Robot.SubsystemCombo combo){
        this(
                g,
                r,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.LIFT_ENABLED : combo == Robot.SubsystemCombo.LIFT_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.CLAW_ENABLED : combo == Robot.SubsystemCombo.CLAW_ONLY
        );
    }

    public void bindCoDriverClawControls() {
        gamepad.rightBumper.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        gamepad.leftBumper.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
        gamepad.rightStickButton.whenPressed(new ClawFlatCommand(robot.clawSubsystem));
    }

    // Using left trigger to lower the lift after align with the pole, then use right bumper to open the claw

    public void bindCoDriverLiftControls() {
        gamepad.leftTrigger.whenPressed(new LiftMoveDownOverrideCommand(robot.liftSubsystem));
        gamepad.rightTrigger.whenPressed(new LiftMoveUpOverrideCommand(robot.liftSubsystem));
        gamepad.dpadLeft.whenPressed(new LiftLowPoleCommand(robot.liftSubsystem));
        gamepad.dpadRight.whenPressed(new LiftMidPoleCommand(robot.liftSubsystem));
        gamepad.dpadUp.whenPressed(new LiftHighPoleCommand(robot.liftSubsystem));
        gamepad.dpadDown.whenPressed(new LiftFloorIntakeCommand(robot.liftSubsystem));

        gamepad.triangle.whenPressed(new TurretFrontCommand(robot.liftSubsystem));
        gamepad.square.whenPressed(new TurretLeftCommand(robot.liftSubsystem));
        gamepad.cross.whenPressed(new TurretRearCommand(robot.liftSubsystem));

        // using left stick for turret control instead
//        gamepad.leftBumper.whenPressed(new IncrementTurretDownCommand(robot.liftSubsystem));
//        gamepad.rightBumper.whenPressed(new IncrementTurretUpCommand(robot.liftSubsystem));
    }
}
