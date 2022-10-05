package org.firstinspires.ftc.sixteen750.command.cone;

import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;

import com.technototes.library.command.Command;

public class ConeReadyToScoreHigh implements Command {
    private ConeSubsystem subsystem;

    public ConeReadyToScoreHigh(ConeSubsystem s) {
        subsystem = s;
    }

    @Override
    public void execute() {
        subsystem.readyToScoreHigh();
    }
}
