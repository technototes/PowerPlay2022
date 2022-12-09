package org.firstinspires.ftc.twenty403.command.drive;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

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
