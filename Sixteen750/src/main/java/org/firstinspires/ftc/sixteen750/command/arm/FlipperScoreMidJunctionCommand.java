package org.firstinspires.ftc.sixteen750.command.arm;

import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;

import com.technototes.library.command.Command;

public class FlipperScoreMidJunctionCommand implements Command {
    private ArmSubsystem armSubsystem;

    public FlipperScoreMidJunctionCommand(ArmSubsystem s) {
        this.armSubsystem = s;
        addRequirements(this.armSubsystem);
    }

    @Override
    public void execute() {
        this.armSubsystem.flipperScoreMidJunction();
    }
}
