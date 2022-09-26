package org.firstinspires.ftc.sixteen750.command.cone;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;

public class ConeReadyToIntakeCommand implements Command {
    private ConeSubsystem subsystem;

    public ConeReadyToIntakeCommand(ConeSubsystem s) {
        subsystem = s;
    }

    @Override
    public void execute() {
        subsystem.readyIntake();
    }
}
