package org.firstinspires.ftc.twenty403.controls;

import android.service.quicksettings.Tile;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import com.technototes.library.control.Stick;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.VisionDuringTeleCommand;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.claw.ClawAutoCloseToggleCommand;
import org.firstinspires.ftc.twenty403.command.drive.DriveCommand;
import org.firstinspires.ftc.twenty403.command.drive.ResetGyroCommand;
import org.firstinspires.ftc.twenty403.command.drive.SlowCommand;
import org.firstinspires.ftc.twenty403.command.drive.TileAbortCommand;
import org.firstinspires.ftc.twenty403.command.drive.TileMoveCommand;
import org.firstinspires.ftc.twenty403.command.drive.TileMoving;
import org.firstinspires.ftc.twenty403.command.drive.TurboCommand;
import org.firstinspires.ftc.twenty403.subsystem.VisionPipeline;

public class ControlDriver {

    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton tileRight, tileLeft, tileUp, tileDown, tileAbort;
    public CommandButton resetGyroButton, driveStraight, turboButton, autoAlign;
    public CommandButton clawToggleAutoCloseButton;
    public CommandButton override;
    VisionPipeline visionPipeline;

    public ControlDriver(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;
        override = g.leftTrigger.getAsButton(0.5);
        visionPipeline = robot.visionSystem.visionPipeline;

        AssignNamedControllerButton();

        if (Robot.RobotConstant.DRIVE_CONNECTED) {
            bindDriveControls();
        }
        if (Robot.RobotConstant.CLAW_CONNECTED) {
            bindClawControls();
        }
        if (Robot.RobotConstant.CAMERA_CONNECTED) {
            bindVisionCommand();
        }
    }

    private void AssignNamedControllerButton() {
        resetGyroButton = gamepad.rightStickButton;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;

        tileDown = gamepad.dpadDown;
        tileUp = gamepad.dpadUp;
        tileLeft = gamepad.dpadLeft;
        tileRight = gamepad.dpadRight;
        tileAbort = gamepad.leftBumper;
        turboButton = gamepad.ps_triangle;
        autoAlign = gamepad.ps_square;

        driveStraight = gamepad.rightTrigger.getAsButton(0.5);
        clawToggleAutoCloseButton = gamepad.ps_circle;
    }

    public void bindDriveControls() {
        CommandScheduler
            .getInstance()
            .scheduleJoystick(
                new DriveCommand(
                    robot.drivebaseSubsystem,
                    driveLeftStick,
                    driveRightStick,
                    driveStraight,
                    autoAlign,
                    visionPipeline
                )
            );
        turboButton.whenPressed(new TurboCommand(robot.drivebaseSubsystem));
        turboButton.whenReleased(new SlowCommand(robot.drivebaseSubsystem));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));

        tileRight.whenPressed(new TileMoveCommand(robot, TileMoving.Right));
        tileLeft.whenPressed(new TileMoveCommand(robot, TileMoving.Left));
        tileUp.whenPressed(new TileMoveCommand(robot, TileMoving.Up));
        tileDown.whenPressed(new TileMoveCommand(robot, TileMoving.Down));
        tileAbort.whenPressed(new TileAbortCommand(robot));
    }

    public void bindVisionCommand() {
        gamepad.ps_share.whenPressed(new VisionDuringTeleCommand(robot.visionSystem));
    }

    public void bindClawControls() {
        clawToggleAutoCloseButton.whenPressed(new ClawAutoCloseToggleCommand(robot.clawSubsystem));
    }
}
