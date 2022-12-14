package org.firstinspires.ftc.swerveteen750.command.arm;

import org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem;

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
        System.out.println("armSubsystem.elbowServoIncrementalUp()");
    }
}
