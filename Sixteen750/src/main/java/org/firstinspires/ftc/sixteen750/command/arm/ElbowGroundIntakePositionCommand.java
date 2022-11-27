package org.firstinspires.ftc.sixteen750.command.arm;

import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;

import com.technototes.library.command.Command;

public class ElbowGroundIntakePositionCommand implements Command {
    private ArmSubsystem armSubsystem;

    public ElbowGroundIntakePositionCommand(ArmSubsystem s) {
        armSubsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        armSubsystem.elbowIntakeGround();
    }
}
