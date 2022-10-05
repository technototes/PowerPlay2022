package org.firstinspires.ftc.sixteen750.command.cone;

import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;

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
