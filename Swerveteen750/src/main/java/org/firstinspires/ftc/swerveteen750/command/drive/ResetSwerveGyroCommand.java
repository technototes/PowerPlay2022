package org.firstinspires.ftc.swerveteen750.command.drive;

import com.technototes.library.command.Command;
import com.technototes.library.control.CommandGamepad;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;

public class ResetSwerveGyroCommand implements Command {
    public ConfigurableSwerveDriveSubsystem swerveDriveSubsystem;
    public CommandGamepad gamepad;

    public ResetSwerveGyroCommand(ConfigurableSwerveDriveSubsystem s, CommandGamepad g) {
        swerveDriveSubsystem = s;
    }

    @Override
    public void execute() {
        swerveDriveSubsystem.setExternalHeading(0);
        gamepad.rumble(0.5);
    }
}
