package org.firstinspires.ftc.edmundbot.command.shooter;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.edmundbot.subsystem.ShooterSubsystem;

public class StartIntakeCommand implements Command {
    private ShooterSubsystem shooterSubsystem;

    public StartIntakeCommand(ShooterSubsystem s) {
        shooterSubsystem = s;

        addRequirements(s);
    }

    @Override
    public void execute() {
        shooterSubsystem.startIntake();
    }
}
