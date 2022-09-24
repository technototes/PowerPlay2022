package org.firstinspires.ftc.sixteen750.Commands.Cone;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;

public class ConeReadyToScoreHigh implements Command {
    private ConeSubsystem subsystem;

    public ConeReadyToScoreHigh(ConeSubsystem s){
       subsystem = s;
    }

    @Override
    public void execute() {
        subsystem.readyToScoreHigh();
    }
}
