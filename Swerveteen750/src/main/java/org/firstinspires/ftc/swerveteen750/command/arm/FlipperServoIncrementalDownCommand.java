package org.firstinspires.ftc.swerveteen750.command.arm;

import org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem;

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
        System.out.println("armSubsystem.flipperServoIncrementalDown()");
    }
}
