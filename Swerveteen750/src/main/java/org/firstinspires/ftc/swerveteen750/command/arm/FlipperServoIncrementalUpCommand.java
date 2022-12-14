package org.firstinspires.ftc.swerveteen750.command.arm;

import org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem;

import com.technototes.library.command.Command;

public class FlipperServoIncrementalUpCommand implements Command {
    private final ArmSubsystem armSubsystem;

    public FlipperServoIncrementalUpCommand(ArmSubsystem s) {
        armSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        armSubsystem.flipperServoIncrementalUp();
        System.out.println("armSubsystem.flipperServoIncrementalUp()");
    }
}
