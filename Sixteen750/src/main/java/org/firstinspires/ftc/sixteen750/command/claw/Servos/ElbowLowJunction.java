package org.firstinspires.ftc.sixteen750.command.claw.Servos;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.Command;

public class ElbowLowJunction implements Command {

    private ClawSubsystem subsystem;

    public ElbowLowJunction(ClawSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.elbowLowJunction();
    }
}
