package org.firstinspires.ftc.edmundbot.command;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.edmundbot.subsystem.VisionPipeline;
import org.firstinspires.ftc.edmundbot.subsystem.VisionSubsystem;

public class VisionDuringTeleCommand implements Command {

    public VisionSubsystem subsystem;

    public VisionDuringTeleCommand(VisionSubsystem s) {
        subsystem = s;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.startVisionPipeline();
        subsystem.pauseScanning();
    }

    @Override
    public void execute() {
        if (subsystem.visionPipeline.activeMode == VisionPipeline.Mode.Junction) {
            subsystem.pauseScanning();
        } else {
            subsystem.startJunctionScanning();
        }
    }
}
