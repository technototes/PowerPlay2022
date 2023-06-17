package org.firstinspires.ftc.edmundbot.command.drive;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.technototes.library.command.Command;
import com.technototes.library.control.CommandGamepad;

import org.firstinspires.ftc.edmundbot.subsystem.DrivebaseSubsystem;

public class ResetGyroCommand implements Command {

    private final DrivebaseSubsystem subsystem;
    private CommandGamepad gamepad;

    public ResetGyroCommand(DrivebaseSubsystem s) {
        subsystem = s;
    }

    public ResetGyroCommand(DrivebaseSubsystem s, CommandGamepad g) {
        subsystem = s;
        gamepad = g;
    }

    @Override
    public void execute() {
        subsystem.setExternalHeading(0);
        if(gamepad != null){
            gamepad.rumble(0.5);
        }
    }
}
