package org.firstinspires.ftc.twenty403.command.compound;

import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;

import com.technototes.library.command.Command;

public class ConeReadyScoreHigh implements Command {
    private ConeSubsystem subsystem;

    public ConeReadyScoreHigh(ConeSubsystem s) {
        subsystem = s;
    }

    @Override
    public void execute() {
        subsystem.readyToScoreHigh();
    }
}
