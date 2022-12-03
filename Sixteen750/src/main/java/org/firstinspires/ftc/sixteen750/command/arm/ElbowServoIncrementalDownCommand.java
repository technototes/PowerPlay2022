package org.firstinspires.ftc.sixteen750.command.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;

public class ElbowServoIncrementalDownCommand implements Command {
    private final ArmSubsystem armSubsystem;

    public ElbowServoIncrementalDownCommand(ArmSubsystem s) {
        armSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        armSubsystem.elbowServoIncrementalDown();
    }
}
