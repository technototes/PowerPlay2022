package org.firstinspires.ftc.twenty403.command;

import com.technototes.library.command.Command;
import java.util.function.BooleanSupplier;
import java.util.function.BooleanSupplier;
import org.firstinspires.ftc.twenty403.subsystem.VisionPipeline;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

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
