package org.firstinspires.ftc.swerveteen750.command.claw;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.ClawSubsystem;

public class ClawFlatCommand implements Command {
    ClawSubsystem clawSubsystem;
    public ClawFlatCommand(ClawSubsystem clawSubsystem) {
        this.clawSubsystem = clawSubsystem;
    }

    @Override
    public void execute() {
        clawSubsystem.clawFlat();
    }
}
