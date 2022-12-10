package org.firstinspires.ftc.twenty403.command.drive;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

public class AutoSpeedCommand implements Command {

    public DrivebaseSubsystem subsystem;

    public AutoSpeedCommand(DrivebaseSubsystem s) {
        subsystem = s;
    }

    @Override
    public void execute() {
        subsystem.auto();
    }
}
