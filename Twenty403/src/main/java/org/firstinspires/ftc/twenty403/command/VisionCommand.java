package org.firstinspires.ftc.twenty403.command;

import com.technototes.library.command.Command;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.twenty403.command.autonomous.StartingPosition;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

public class VisionCommand implements Command {
    public VisionSubsystem subsystem;

    public VisionCommand(VisionSubsystem s) {
        subsystem = s;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.startVisionPipeline();
    }

    @Override
    public void execute() {}

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean cancel) {
        subsystem.stopVisionPipeline();
    }
}

