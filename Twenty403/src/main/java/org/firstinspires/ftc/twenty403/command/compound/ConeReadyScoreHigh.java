package org.firstinspires.ftc.twenty403.command.compound;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;

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
