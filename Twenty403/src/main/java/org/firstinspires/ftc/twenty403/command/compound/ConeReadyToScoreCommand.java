package org.firstinspires.ftc.twenty403.command.compound;

import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;

import com.technototes.library.command.Command;

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
