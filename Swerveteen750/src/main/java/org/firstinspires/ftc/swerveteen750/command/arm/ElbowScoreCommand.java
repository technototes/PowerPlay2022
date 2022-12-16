package org.firstinspires.ftc.swerveteen750.command.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem;

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
