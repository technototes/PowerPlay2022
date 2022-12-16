package org.firstinspires.ftc.swerveteen750.command.arm;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem;

public class ElbowServoIncrementalDownCommand implements Command {
    private final ArmSubsystem armSubsystem;

    public ElbowServoIncrementalDownCommand(ArmSubsystem s) {
        armSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        armSubsystem.elbowServoIncrementalDown();
        System.out.println("armSubsystem.elbowServoIncrementalDown()");
    }
}
