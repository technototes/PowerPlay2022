package org.firstinspires.ftc.sixteen750.command.claw.Servos;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

public class FlipperLowJunction implements Command {
    private ClawSubsystem subsystem;

    public FlipperLowJunction(ClawSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {subsystem.flipperLowJunction();}
}
