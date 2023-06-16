package org.firstinspires.ftc.edmundbot.command.drive;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.edmundbot.subsystem.DrivebaseSubsystem;

public class SetSpeedCommand implements Command {

    public DrivebaseSubsystem subsystem;

    public SetSpeedCommand(DrivebaseSubsystem s) {
        subsystem = s;
    }

    @Override
    public void execute() {
        subsystem.speed = 0.5;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean cancel) {
        subsystem.speed = 1;
    }
}
