package org.firstinspires.ftc.sixteen750;

import org.firstinspires.ftc.sixteen750.Robot.RobotConstant;
import org.firstinspires.ftc.sixteen750.command.ResetCommandSchedulerCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ArmIntakeCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ArmScoreCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ArmUpwardCommand;
import org.firstinspires.ftc.sixteen750.command.drive.MecanumDriveCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftGroundJunctionCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftHighPoleCommand;
import org.firstinspires.ftc.sixteen750.command.drive.ResetGyroCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftMoveDownCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftMoveDownOverrideCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftMoveUpCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftLowPoleCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftMidPoleCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftMoveUpOverrideCommand;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandGamepad;

public class ControlsDriver {
    public Robot robot;
    public CommandGamepad gamepad;

    public ControlsDriver(CommandGamepad g, Robot r, boolean enableMecanumDrive, boolean enableLift, boolean enableArm, boolean enableClaw) {
        this.robot = r;
        gamepad = g;

        if (enableMecanumDrive) {
            bindMecanumDriveControls();
        }
        if (enableLift) {
            bindDriverLiftControls();
        }
        if (enableArm) {
            bindDriverArmControls();
        }
        if (enableClaw) {
            bindDriverClawControls();
        }

        gamepad.leftStickButton.whenPressed(new ResetCommandSchedulerCommand(gamepad));
    }

    public ControlsDriver(CommandGamepad g, Robot r, Robot.SubsystemCombo combo) {
        this(
                g,
                r,
                combo == Robot.SubsystemCombo.DEFAULT ? RobotConstant.MECANUM_DRIVE_ENABLED : combo == Robot.SubsystemCombo.DRIVE_ONLY || combo == Robot.SubsystemCombo.VISION_DRIVE,
                combo == Robot.SubsystemCombo.DEFAULT ? RobotConstant.LIFT_ENABLED : combo == Robot.SubsystemCombo.LIFT_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? RobotConstant.ARM_ENABLED : combo == Robot.SubsystemCombo.ARM_CLAW_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? RobotConstant.CLAW_ENABLED : combo == Robot.SubsystemCombo.ARM_CLAW_ONLY
        );
    }

    public void bindMecanumDriveControls() {
        // Probably not a good idea to bind the drive controls to more than one gamepad
        CommandScheduler
                .getInstance()
                .scheduleJoystick(new MecanumDriveCommand(robot.mecanumDriveSubsystem, gamepad.leftStick, gamepad.rightStick, gamepad.square));
        gamepad.rightStickButton.whenPressed(new ResetGyroCommand(robot.mecanumDriveSubsystem, gamepad));
    }

    public void bindDriverClawControls() {
        gamepad.leftBumper.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        gamepad.leftBumper.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
    }

    public void bindDriverArmControls() {
        gamepad.square.whenPressed(new ArmScoreCommand(robot.armSubsystem));
        gamepad.triangle.whenPressed(new ArmUpwardCommand(robot.armSubsystem));
        gamepad.circle.whenPressed(new ArmIntakeCommand(robot.armSubsystem));
    }

    public void bindDriverLiftControls() {
        gamepad.leftTrigger.whenPressed(new LiftMoveUpOverrideCommand(robot.liftSubsystem));
        gamepad.rightTrigger.whenPressed(new LiftMoveDownOverrideCommand(robot.liftSubsystem));
        gamepad.dpadLeft.whenPressed(new LiftLowPoleCommand(robot.liftSubsystem));
        gamepad.dpadRight.whenPressed(new LiftMidPoleCommand(robot.liftSubsystem));
        gamepad.dpadUp.whenPressed(new LiftHighPoleCommand(robot.liftSubsystem));
        gamepad.dpadDown.whenPressed(new LiftGroundJunctionCommand(robot.liftSubsystem));
    }
}
