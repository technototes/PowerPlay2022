package org.firstinspires.ftc.sixteen750.command.claw;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.Command;

public class ElbowGroundIntakePositionCommand implements Command {
    private ClawSubsystem clawSubsystem;

    public ElbowGroundIntakePositionCommand(ClawSubsystem s) {
        clawSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        clawSubsystem.elbowIntakeGround();
    }

    //    @Override
    //    public boolean isFinished() {
    //        // TODO: Adjust this duration
    //        return getRuntime().seconds() > 0.5;
    //    }
}
