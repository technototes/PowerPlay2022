package org.firstinspires.ftc.swerveteen750.command.claw;

import org.firstinspires.ftc.swerveteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.Command;

public class ClawCloseCommand implements Command {
    private ClawSubsystem subsystem;

    public ClawCloseCommand(ClawSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.closeClaw();
    }

    //    @Override
    //    public boolean isFinished() {
    //        // TODO: Adjust this duration
    //        return getRuntime().seconds() > 0.5;
    //    }
}
