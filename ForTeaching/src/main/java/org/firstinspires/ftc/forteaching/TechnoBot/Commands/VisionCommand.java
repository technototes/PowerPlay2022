package org.firstinspires.ftc.forteaching.TechnoBot.Commands;

import com.technototes.library.command.Command;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.VisionSubsystem;

public class VisionCommand implements Command {
    public VisionSubsystem subsystem;

    public VisionCommand(VisionSubsystem s, Alliance alliance) {
        subsystem = s;
        // subsystem.setStartingPosition(alliance, side);
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.startVisionPipeline();
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean cancel) {
        subsystem.stopVisionPipeline();
    }
}
