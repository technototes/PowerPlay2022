package org.firstinspires.ftc.sixteen750.command.claw;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

public class ScoreGroundJunction implements Command {
    private ClawSubsystem subsystem;

    public ScoreGroundJunction(ClawSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {subsystem.scoreGroundJunction();}
}
