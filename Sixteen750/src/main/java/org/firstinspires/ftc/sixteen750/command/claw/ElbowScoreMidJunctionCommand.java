package org.firstinspires.ftc.sixteen750.command.claw;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

public class ElbowScoreMidJunctionCommand implements Command {
    private ClawSubsystem clawSubsystem;

    public ElbowScoreMidJunctionCommand(ClawSubsystem s) {
        this.clawSubsystem = s;
        addRequirements(this.clawSubsystem);
    }

    @Override
    public void execute() {
        this.clawSubsystem.elbowScoreMidJunction();
    }

    @Override
    public boolean isFinished() {
        // TODO: Adjust this duration
        return getRuntime().seconds() > 1.0;
    }
}
