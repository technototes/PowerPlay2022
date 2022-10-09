package org.firstinspires.ftc.twenty403.command.compound;

import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;

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
