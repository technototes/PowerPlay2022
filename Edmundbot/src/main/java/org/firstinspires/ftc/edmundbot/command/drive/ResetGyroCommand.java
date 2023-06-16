package org.firstinspires.ftc.edmundbot.command.drive;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.edmundbot.subsystem.DrivebaseSubsystem;

public class ResetGyroCommand implements Command {

    public DrivebaseSubsystem subsystem;

    public ResetGyroCommand(DrivebaseSubsystem s) {
        subsystem = s;
    }

    @Override
    public void execute() {
        subsystem.setExternalHeading(0);
    }
}
