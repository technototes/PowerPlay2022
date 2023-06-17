package org.firstinspires.ftc.edmundbot.command.shooter;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.edmundbot.subsystem.ShooterSubsystem;

public class StopIntakeCommand implements Command {
    private ShooterSubsystem shooterSubsystem;

    public StopIntakeCommand(ShooterSubsystem s) {
        shooterSubsystem = s;

        addRequirements(s);
    }

    @Override
    public void execute() {
        shooterSubsystem.stopIntake();
    }
}
