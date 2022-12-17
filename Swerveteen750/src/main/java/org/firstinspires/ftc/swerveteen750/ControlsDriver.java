package org.firstinspires.ftc.swerveteen750;

import org.firstinspires.ftc.swerveteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.swerveteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.swerveteen750.command.drive.ApplyTurboModeCommand;
import org.firstinspires.ftc.swerveteen750.command.drive.MecanumDriveCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftFloorIntakeCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftHighPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.drive.ResetGyroCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftMoveDownOverrideCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftLowPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftMidPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftMoveUpOverrideCommand;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandGamepad;

public class ControlsDriver {
    public Robot robot;
    public CommandGamepad gamepad;

    public ControlsDriver(CommandGamepad g,
                          Robot r,
                          boolean enableSwerveDrive,
                          boolean enableMecanumDrive,
                          boolean enableLift,
                          boolean enableClaw
    ) {
        this.robot = r;
        gamepad = g;

        if (enableMecanumDrive) {
            bindMecanumDriveControls();
            System.out.println("Binding Mecanum Drive Controls for Driver");
        }
        else if (enableSwerveDrive) {
            bindSwerveDriveControls();
            System.out.println("Binding Swerve Drive Controls for Driver");
        }
        if (enableLift) {
            bindDriverLiftControls();
            System.out.println("Binding Lift Controls for Driver");
        }
        if (enableClaw) {
            bindDriverClawControls();
            System.out.println("Binding Claw Controls for Driver");
        }

//        gamepad.leftStickButton.whenPressed(new ResetCommandSchedulerCommand(gamepad));
    }

    public ControlsDriver(CommandGamepad g, Robot r, Robot.SubsystemCombo combo) {
        this(
                g,
                r,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.SWERVE_DRIVE_ENABLED : combo == Robot.SubsystemCombo.S_DRIVE_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.MECANUM_DRIVE_ENABLED : combo == Robot.SubsystemCombo.M_DRIVE_ONLY || combo == Robot.SubsystemCombo.VISION_M_DRIVE,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.LIFT_ENABLED : combo == Robot.SubsystemCombo.LIFT_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.CLAW_ENABLED : combo == Robot.SubsystemCombo.CLAW_ONLY
        );
    }

    public void bindMecanumDriveControls() {
        // Probably not a good idea to bind the drive controls to more than one gamepad
        CommandScheduler
                .getInstance()
                .scheduleJoystick(new MecanumDriveCommand(robot.mecanumDriveSubsystem, gamepad.leftStick, gamepad.rightStick));
        gamepad.leftStickButton.whenPressed(new ApplyTurboModeCommand(robot.mecanumDriveSubsystem));
        gamepad.rightStickButton.whenPressed(new ApplyTurboModeCommand(robot.mecanumDriveSubsystem));
        gamepad.x.whenPressed(new ResetGyroCommand(robot.mecanumDriveSubsystem, gamepad));
    }

    public void bindSwerveDriveControls(){
        System.err.println("Swerve Drive Controls Not Implemented");
    }

    public void bindDriverClawControls() {
        gamepad.leftBumper.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        gamepad.rightBumper.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
    }

    public void bindDriverLiftControls() {
        gamepad.leftTrigger.whenPressed(new LiftMoveUpOverrideCommand(robot.liftSubsystem));
        gamepad.rightTrigger.whenPressed(new LiftMoveDownOverrideCommand(robot.liftSubsystem));
        gamepad.dpadLeft.whenPressed(new LiftLowPoleCommand(robot.liftSubsystem));
        gamepad.dpadRight.whenPressed(new LiftMidPoleCommand(robot.liftSubsystem));
        gamepad.dpadUp.whenPressed(new LiftHighPoleCommand(robot.liftSubsystem));
        gamepad.dpadDown.whenPressed(new LiftFloorIntakeCommand(robot.liftSubsystem));
    }
}
