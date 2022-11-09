package org.firstinspires.ftc.sixteen750.command.claw;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

public class ElbowGroundIntakePositionCommand implements Command {
    private ClawSubsystem clawSubsystem;

    public ElbowGroundIntakePositionCommand(ClawSubsystem s) {
        clawSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        clawSubsystem.elbowGroundIntake();
    }

//    @Override
//    public boolean isFinished() {
//        // TODO: Adjust this duration
//        return getRuntime().seconds() > 0.5;
//    }
}
