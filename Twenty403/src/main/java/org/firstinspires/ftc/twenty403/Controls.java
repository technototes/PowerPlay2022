package org.firstinspires.ftc.twenty403;

import org.firstinspires.ftc.twenty403.Robot.RobotConstant;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoBluePathingTest;
import org.firstinspires.ftc.twenty403.command.drive.DriveCommand;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

public class Controls {
    public Robot robot;
    public CommandGamepad gamepad;
    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraightenButton, snailSpeedButton;
    public CommandButton pathTestingButton;

    public Controls(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;
        resetGyroButton = gamepad.rightStickButton;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        driveStraightenButton = gamepad.square;
        pathTestingButton = gamepad.cross;
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

    public void bindPathTestingButton() {
        pathTestingButton.whenPressed(new AutoBluePathingTest(robot.drivebaseSubsystem));
    }
}
