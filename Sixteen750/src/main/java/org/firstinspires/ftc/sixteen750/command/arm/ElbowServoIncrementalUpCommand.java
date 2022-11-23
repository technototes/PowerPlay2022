package org.firstinspires.ftc.sixteen750.command.arm;

import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;

import com.technototes.library.command.Command;

public class ElbowServoIncrementalUpCommand implements Command {
    private final ArmSubsystem armSubsystem;

    public ElbowServoIncrementalUpCommand(ArmSubsystem s) {
        armSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        armSubsystem.elbowServoIncrementalUp();
    }
}
