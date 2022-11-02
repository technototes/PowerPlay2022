package org.firstinspires.ftc.twenty403.command;

import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.technototes.library.command.Command;

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
