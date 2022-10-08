package org.firstinspires.ftc.twenty403;

import org.firstinspires.ftc.twenty403.Robot.RobotConstant;
import org.firstinspires.ftc.twenty403.command.drive.DriveCommand;
import org.firstinspires.ftc.twenty403.command.drive.ResetGyroCommand;
import org.firstinspires.ftc.twenty403.command.drive.SetSpeedCommand;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

public class Controls {
    public Robot robot;
    public CommandGamepad gamepad;
    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraightenButton, snailSpeedButton;

    public Controls(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;
        resetGyroButton = gamepad.rightStickButton;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        driveStraightenButton = gamepad.square;
        if (RobotConstant.DRIVE_CONNECTED) {
            bindDriveControls();
        }
    }

    public void bindDriveControls() {
        CommandScheduler.getInstance()
                .scheduleJoystick(new DriveCommand(
                        robot.drivebaseSubsystem, driveLeftStick, driveRightStick, driveStraightenButton));
//        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
//        snailSpeedButton.whilePressedOnce(new SetSpeedCommand(robot.drivebaseSubsystem));
    }
}
