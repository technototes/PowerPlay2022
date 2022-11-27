package org.firstinspires.ftc.sixteen750;

import org.firstinspires.ftc.sixteen750.Robot.RobotConstant;
import org.firstinspires.ftc.sixteen750.command.ResetCommandSchedulerCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.drive.MecanumDriveCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftGroundJunctionCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftHighPoleCommand;
import org.firstinspires.ftc.sixteen750.command.drive.ResetGyroCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftIncrementalMoveDownCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftIncrementalMoveUpCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftLowPoleCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftMidPoleCommand;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandGamepad;

public class ControlsDriver {
    public Robot robot;
    public CommandGamepad gamepad;

    public ControlsDriver(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;

        if (RobotConstant.MECANUM_DRIVE_ENABLED) {
            bindMecanumDriveControls();
        }
        if (RobotConstant.CLAW_ENABLED) {
            bindDriverClawControls();
        }
        if (RobotConstant.LIFT_ENABLED) {
            bindDriverLiftControls();
        }

        gamepad.leftStickButton.whenPressed(new ResetCommandSchedulerCommand(gamepad));
    }

    public void bindMecanumDriveControls() {
        // Probably not a good idea to bind the drive controls to more than one gamepad
         CommandScheduler
                 .getInstance()
                 .scheduleJoystick(new MecanumDriveCommand(robot.mecanumDriveSubsystem, gamepad.leftStick, gamepad.rightStick, gamepad.square));
        gamepad.rightStickButton.whenPressed(new ResetGyroCommand(robot.mecanumDriveSubsystem, gamepad));
    }

    public void bindDriverClawControls() {
        gamepad.leftTrigger.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        gamepad.rightTrigger.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
    }

    public void bindDriverLiftControls() {
        gamepad.leftBumper.whenPressed(new LiftIncrementalMoveUpCommand(robot.liftSubsystem));
        gamepad.rightBumper.whenPressed(new LiftIncrementalMoveDownCommand(robot.liftSubsystem));
        gamepad.square.whenPressed(new LiftLowPoleCommand(robot.liftSubsystem));
        gamepad.triangle.whenPressed(new LiftMidPoleCommand(robot.liftSubsystem));
        gamepad.circle.whenPressed(new LiftHighPoleCommand(robot.liftSubsystem));
        gamepad.cross.whenPressed(new LiftGroundJunctionCommand(robot.liftSubsystem));
    }
}
