package org.firstinspires.ftc.twenty403.command.compound;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;

public class ConeReadyToScoreCommand implements Command {
    private ConeSubsystem subsystem;

    public ConeReadyToScoreCommand(ConeSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.readyToScore();
    }
}