package org.firstinspires.ftc.twenty403.command.drive;

import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.Command;

public class SlowCommand implements Command {
    public DrivebaseSubsystem subsystem;

    public SlowCommand(DrivebaseSubsystem s) {
        subsystem = s;
    }

    @Override
    public void execute() {
        subsystem.slow();
    }
}
