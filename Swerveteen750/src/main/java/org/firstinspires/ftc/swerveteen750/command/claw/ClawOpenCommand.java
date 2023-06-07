package org.firstinspires.ftc.swerveteen750.command.claw;

import org.firstinspires.ftc.swerveteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.Command;

public class ClawOpenCommand implements Command {
    private ClawSubsystem subsystem;

    public ClawOpenCommand(ClawSubsystem s) {
        this.subsystem = s;
        addRequirements(this.subsystem); // Keeps robot from breaking
    }

    @Override
    public void execute() {
        this.subsystem.openClaw();
    }

    //    @Override
    //    public boolean isFinished() {
    //        // TODO: Adjust this duration
    //        return getRuntime().seconds() > 0.3;
    //    }
}
