package org.firstinspires.ftc.sixteen750;

import com.technototes.library.control.CommandGamepad;

import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ArmIntakeCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ArmScoreCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ArmUpwardCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftMoveDownOverrideCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftMoveUpOverrideCommand;
import org.firstinspires.ftc.sixteen750.opmode.diagnosis.TeleVisionTest;

public class ControlsCoDriver {
    public Robot robot;
    public CommandGamepad gamepad;

    public ControlsCoDriver(CommandGamepad g, Robot r, boolean enableLift, boolean enableArm, boolean enableClaw){
        this.robot = r;
        this.gamepad = g;

        if (enableLift){
            bindCoDriverLiftControls();
            System.out.println("Binding Lift Control for CoDriver");
        }

        if (enableArm){
            bindCoDriverArmControls();
            System.out.println("Binding Arm Control for CoDriver");
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
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.ARM_ENABLED : combo == Robot.SubsystemCombo.ARM_CLAW_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.CLAW_ENABLED : combo == Robot.SubsystemCombo.ARM_CLAW_ONLY
        );
    }

    public void bindCoDriverClawControls() {
        gamepad.leftBumper.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        gamepad.rightBumper.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
    }

    public void bindCoDriverArmControls(){
        gamepad.ps_square.whenPressed(new ArmScoreCommand(robot.armSubsystem));
       // gamepad.triangle.whenPressed(new ArmUpwardCommand(robot.armSubsystem));
        gamepad.ps_circle.whenPressed(new ArmIntakeCommand(robot.armSubsystem));
    }

    public void bindCoDriverLiftControls() {
        gamepad.leftTrigger.whenPressed(new LiftMoveUpOverrideCommand(robot.liftSubsystem));
        gamepad.rightTrigger.whenPressed(new LiftMoveDownOverrideCommand(robot.liftSubsystem));
    }
}
