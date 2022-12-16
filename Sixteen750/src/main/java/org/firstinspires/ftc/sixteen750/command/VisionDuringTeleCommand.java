package org.firstinspires.ftc.sixteen750.command;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.VisionSubsystem;

import java.util.function.BooleanSupplier;

public class VisionDuringTeleCommand implements Command {

    public VisionSubsystem subsystem;
    public BooleanSupplier scanButton;

    public VisionDuringTeleCommand(VisionSubsystem s, BooleanSupplier shouldScan) {
        subsystem = s;
        addRequirements(subsystem);
        scanButton = shouldScan;
    }

    @Override
    public void initialize() {
        subsystem.startVisionPipeline();
        subsystem.pauseScanning();
    }

    @Override
    public void execute() {
        if (scanButton.getAsBoolean() == true) {
            subsystem.startJunctionScanning();
        } else {
            subsystem.pauseScanning();
        }
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
