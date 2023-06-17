package org.firstinspires.ftc.edmundbot.controls;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandGamepad;

import org.firstinspires.ftc.edmundbot.Robot;
import org.firstinspires.ftc.edmundbot.command.drive.DriveCommand;
import org.firstinspires.ftc.edmundbot.command.drive.ResetGyroCommand;
import org.firstinspires.ftc.edmundbot.command.shooter.StartShooterCommand;
import org.firstinspires.ftc.edmundbot.command.shooter.StopShooterCommand;

public class NewControl {
    public Robot robot;
    public CommandGamepad gamepad;

    public NewControl(Robot r,
                      CommandGamepad gp,
                      boolean enableDrive,
                      boolean enableShooter
    ) {
        robot = r;
        gamepad = gp;

        if (enableDrive) {
            bindDriveControls();
            System.out.println("Binding Drive Controls for Driver");
        }

        if (enableShooter) {
            bindShooterControls();
            System.out.println("Binding Shooter Controls for Driver");
        }
    }

    public void bindDriveControls() {
        // Probably not a good idea to bind the drive controls to more than one gamepad
        CommandScheduler
                .getInstance()
                .scheduleJoystick(
                        new DriveCommand(
                                robot.drivebaseSubsystem,
                                gamepad.leftStick,
                                gamepad.rightStick,
                                gamepad.leftStickButton
                        )
                );
        gamepad.rightStickButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
    }

    public void bindShooterControls() {
        gamepad.leftTrigger.whenPressed(new StartShooterCommand(robot.shooterSubsystem));
        gamepad.rightTrigger.whenPressed(new StopShooterCommand(robot.shooterSubsystem));
    }
}
