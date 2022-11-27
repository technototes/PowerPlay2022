package org.firstinspires.ftc.sixteen750.command.arm;

import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;

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
    }
}
