package org.firstinspires.ftc.swerveteen750.command.arm;

import org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem;

import com.technototes.library.command.Command;

public class FlipperIntakeCommand implements Command {
    private ArmSubsystem armSubsystem;

    public FlipperIntakeCommand(ArmSubsystem s) {
        this.armSubsystem = s;
        addRequirements(this.armSubsystem);
    }

    @Override
    public void execute() {
        this.armSubsystem.flipperIntake();
    }
}
