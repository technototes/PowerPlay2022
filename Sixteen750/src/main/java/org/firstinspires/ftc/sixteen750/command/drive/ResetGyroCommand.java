package org.firstinspires.ftc.sixteen750.command.drive;

import com.technototes.library.command.Command;
import com.technototes.library.control.CommandGamepad;

import org.firstinspires.ftc.sixteen750.subsystem.MecanumDriveSubsystem;

public class ResetGyroCommand implements Command {
    public MecanumDriveSubsystem mecanumDriveSubsystem;
    public CommandGamepad gamepad;

    public ResetGyroCommand(MecanumDriveSubsystem s, CommandGamepad g) {
        mecanumDriveSubsystem = s;
        gamepad = g;
    }

    @Override
    public void execute() {
        mecanumDriveSubsystem.setExternalHeading(0);
        gamepad.rumble(0.5);
    }
}
