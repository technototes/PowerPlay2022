package org.firstinspires.ftc.sixteen750.command.cone;

import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;

import com.technototes.library.command.Command;

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
