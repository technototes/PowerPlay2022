package org.firstinspires.ftc.sixteen750.command.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;

public class ElbowScoreCommand implements Command {
    private ArmSubsystem armSubsystem;

    public ElbowScoreCommand(ArmSubsystem s) {
        this.armSubsystem = s;
        addRequirements(this.armSubsystem);
    }

    @Override
    public void execute() {
        this.armSubsystem.elbowScore();
    }
}
