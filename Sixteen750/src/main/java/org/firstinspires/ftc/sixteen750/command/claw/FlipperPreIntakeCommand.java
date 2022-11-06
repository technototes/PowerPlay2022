package org.firstinspires.ftc.sixteen750.command.claw;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

public class FlipperPreIntakeCommand implements Command {
    private ClawSubsystem clawSubsystem;

    public FlipperPreIntakeCommand(ClawSubsystem s) {
        this.clawSubsystem = s;
        addRequirements(this.clawSubsystem);
    }

    @Override
    public void execute() {
        this.clawSubsystem.flipperPreIntake();
    }

    @Override
    public boolean isFinished() {
        // TODO: Adjust this duration
        return getRuntime().seconds() > 1.0;
    }
}
