package org.firstinspires.ftc.twenty403;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.drive.DriveCommand;
import org.firstinspires.ftc.twenty403.command.drive.ResetGyroCommand;
import org.firstinspires.ftc.twenty403.command.drive.SlowCommand;
import org.firstinspires.ftc.twenty403.command.drive.TurboCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftDownCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftGroundJunctionCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftIntakeCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftLowJunctionCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftMidJunctionCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftUpCommand;

public class Controls2 {
    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraightenButton, snailSpeedButton, liftUpButton, clawOpenButton;
    public CommandButton liftDownButton, clawCloseButtonBackup, liftDownButtonBackup, clawCloseButton, liftIntakePos;
    public CommandButton liftGround, liftLow, liftMedium, liftHigh;
    public CommandButton turboButton;

    public Controls2(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;

        AssignNamedControllerButton();

        if (Robot.RobotConstant.DRIVE_CONNECTED) {
            bindDriveControls();
        }
        if (Robot.RobotConstant.CLAW_CONNECTED) {
            bindClawControls();
        }
        if (Robot.RobotConstant.LIFT_CONNECTED) {
            bindLiftControls();
        }
    }

    private void AssignNamedControllerButton() {
        resetGyroButton = gamepad.rightStickButton;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        turboButton = gamepad.leftStickButton;

        liftUpButton = gamepad.dpadRight; //square
        liftDownButton = gamepad.dpadDown; //cross
        liftIntakePos = gamepad.dpadLeft; //circle

//        liftDownButtonBackup = gamepad.rightTrigger.getAsButton();


        clawOpenButton = gamepad.rightBumper; //left
        clawCloseButton = gamepad.leftBumper; //right

//        clawCloseButtonBackup = gamepad.leftTrigger.getAsButton();

        liftGround = gamepad.cross; //dpadDown
        liftLow = gamepad.square; //dpadLeft
        liftMedium = gamepad.circle; //dpadRight
        liftHigh = gamepad.triangle; //dpadUp

        // TODO: Identify other controls for
    }

    public void bindDriveControls() {
        CommandScheduler.getInstance()
                .scheduleJoystick(new DriveCommand(
                        robot.drivebaseSubsystem, driveLeftStick, driveRightStick, driveStraightenButton));
        turboButton.whenPressed(new TurboCommand(robot.drivebaseSubsystem));
        turboButton.whenReleased(new SlowCommand(robot.drivebaseSubsystem));
        // TODO: We probably want buttons to reset the Gyro...
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
        //   snailSpeedButton.whilePressedOnce(new SetSpeedCommand(robot.drivebaseSubsystem));
    }

    public void bindClawControls() {
        // TODO: Name & Bind claw controls
        clawOpenButton.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        clawOpenButton.whenReleased(new ClawCloseCommand(robot.clawSubsystem));
        //clawCloseButton.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
        //clawCloseButtonBackup.whenPressed(new ClawCloseCommand(robot.clawSubsystem));
    }

    public void bindLiftControls() {
        // TODO: Name & Bind lift controls
        liftUpButton.whenPressed(new LiftUpCommand(robot.liftSubsystem));
        liftDownButton.whenPressed(new LiftDownCommand(robot.liftSubsystem));
        //liftDownButtonBackup.whenPressed(new LiftDownCommand(robot.liftSubsystem));
        liftIntakePos.whenPressed(new LiftIntakeCommand(robot.liftSubsystem));

        liftLow.whenPressed(new LiftLowJunctionCommand(robot.liftSubsystem));
        liftGround.whenPressed(new LiftGroundJunctionCommand(robot.liftSubsystem));
        liftMedium.whenPressed(new LiftMidJunctionCommand(robot.liftSubsystem));
        liftHigh.whenPressed(new LiftHighJunctionCommand(robot.liftSubsystem));
    }
}

