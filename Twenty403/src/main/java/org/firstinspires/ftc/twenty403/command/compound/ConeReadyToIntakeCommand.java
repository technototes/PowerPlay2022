package org.firstinspires.ftc.twenty403.command.compound;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;

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
