package org.firstinspires.ftc.edmundbot.command.drive;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.edmundbot.subsystem.DrivebaseSubsystem;

public class TurboCommand implements Command {

    public DrivebaseSubsystem subsystem;

    public TurboCommand(DrivebaseSubsystem s) {
        subsystem = s;
    }

    @Override
    public void execute() {
        subsystem.fast();
    }
}
