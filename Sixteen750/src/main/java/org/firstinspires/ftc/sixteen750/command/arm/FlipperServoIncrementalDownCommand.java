package org.firstinspires.ftc.sixteen750.command.arm;

import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;

import com.technototes.library.command.Command;

public class FlipperServoIncrementalDownCommand implements Command {
    private final ArmSubsystem armSubsystem;

    public FlipperServoIncrementalDownCommand(ArmSubsystem s) {
        armSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        armSubsystem.flipperServoIncrementalDown();
    }
}
