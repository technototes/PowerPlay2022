/*
package org.firstinspires.ftc.twenty403;

public class DualController {
    public Robot robot;
    public CommandGamepad primaryGamepad, tileGamePad;
    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraighten, turboButton;
    public CommandButton liftDownButton, liftUpButton, clawOpenButton, clawCloseButton, liftOverrideZeroButton;
    public CommandButton liftGroundOrOverrideDown, liftLow, liftMedium, liftHighOrOverrideUp, liftIntakePos;
    public BothButtons override;
    public CommandButton forwardButton, leftButton, rightButton, downButton;

    public DualController(CommandGamepad g, CommandGamepad h, Robot r) {
        this.robot = r;
        primaryGamepad = g;
        tileGamePad = h;
        override = new BothButtons(g.leftTrigger.getAsButton(0.5)) //, g.rightTrigger.getAsButton(0.5));

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
        forwardButton = tileGamePad.dpadUp;
        leftButton = tileGamePad.dpadLeft;
        rightButton = tileGamePad.dpadRight;
        downButton = tileGamePad.dpadDown;
        resetGyroButton = primaryGamepad.rightStickButton;
        driveLeftStick = primaryGamepad.leftStick;
        driveRightStick = primaryGamepad.rightStick;
        turboButton = primaryGamepad.leftStickButton;

        liftUpButton = primaryGamepad.dpadRight; // square
        liftDownButton = primaryGamepad.dpadDown; // cross
        liftIntakePos = primaryGamepad.dpadLeft; // circle
        liftOverrideZeroButton = primaryGamepad.share;

        clawOpenButton = primaryGamepad.rightBumper; // left
        clawCloseButton = primaryGamepad.leftBumper; // right

        liftGroundOrOverrideDown = primaryGamepad.cross; // dpadDown
        liftLow = primaryGamepad.square; // dpadLeft
        liftMedium = primaryGamepad.circle; // dpadRight
        liftHighOrOverrideUp = primaryGamepad.triangle; // dpadUp

        driveStraighten = primaryGamepad.options;

        // TODO: Identify other controls for
    }

    public void bindDriveControls() {
        CommandScheduler.getInstance()
                .scheduleJoystick(
                        new DriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick, driveStraighten));
        turboButton.whenPressed(new TurboCommand(robot.drivebaseSubsystem));
        turboButton.whenReleased(new SlowCommand(robot.drivebaseSubsystem));
        // TODO: We probably want buttons to reset the Gyro...
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
        forwardButton.whenPressed(new TrajectorySequenceCommand(
                robot.drivebaseSubsystem, AutoConstantsBlue.Home.TELESTART_TO_FORWARD_MOVE));
        leftButton.whenPressed(
                new TrajectorySequenceCommand(robot.drivebaseSubsystem, AutoConstantsBlue.Home.TELESTART_TO_LEFT_MOVE));
        rightButton.whenPressed(new TrajectorySequenceCommand(
                robot.drivebaseSubsystem, AutoConstantsBlue.Home.TELESTART_TO_RIGHT_MOVE));
        downButton.whenPressed(new TrajectorySequenceCommand(
                robot.drivebaseSubsystem, AutoConstantsBlue.Home.TELESTART_TO_BACKWARD_MOVE));
    }

    public void bindClawControls() {
        // TODO: Name & Bind claw controls
        clawOpenButton.whenPressed(new ClawOpenCommand(robot.clawSubsystem));
        clawCloseButton.whenReleased(new ClawCloseCommand(robot.clawSubsystem));
    }

    public void bindLiftControls() {
        // TODO: Name & Bind lift controls
        liftUpButton.whenPressed(new LiftUpCommand(robot.liftSubsystem));
        liftDownButton.whenPressed(new LiftDownCommand(robot.liftSubsystem));
        liftIntakePos.whenPressed(new LiftIntakeCommand(robot.liftSubsystem));
        liftOverrideZeroButton.whenPressed(
                new ConditionalCommand(override, new LiftSetZeroCommand(robot.liftSubsystem)));

        liftGroundOrOverrideDown.whenPressed(new ConditionalCommand(
                override,
                new LiftMoveDownOverrideCommand(robot.liftSubsystem),
                new LiftGroundJunctionCommand(robot.liftSubsystem)));
        liftLow.whenPressed(new LiftLowJunctionCommand(robot.liftSubsystem));
        liftMedium.whenPressed(new LiftMidJunctionCommand(robot.liftSubsystem));
        liftHighOrOverrideUp.whenPressed(new ConditionalCommand(
                override,
                new LiftMoveUpOverrideCommand(robot.liftSubsystem),
                new LiftHighJunctionCommand(robot.liftSubsystem)));
    }
}
*/
