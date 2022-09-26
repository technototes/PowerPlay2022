package org.firstinspires.ftc.sixteen750.command.cone;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;

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
