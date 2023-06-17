package org.firstinspires.ftc.edmundbot.command.shooter;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.edmundbot.subsystem.ShooterSubsystem;

public class StopShooterCommand implements Command {
    ShooterSubsystem shooterSubsystem;

    public StopShooterCommand(ShooterSubsystem s) {
        shooterSubsystem = s;
    }

    @Override
    public void execute() {
        shooterSubsystem.setSpeed(0);
    }
}