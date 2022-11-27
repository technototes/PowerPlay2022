package org.firstinspires.ftc.twenty403.command;

import com.technototes.library.command.Command;
import java.util.function.BooleanSupplier;
import java.util.function.BooleanSupplier;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

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
