package org.firstinspires.ftc.forteaching.TechnoBot.Commands;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.ClawSubsystem;

public class OpenClawCommand implements Command {
    private ClawSubsystem clawSubsystem;
    public OpenClawCommand(ClawSubsystem claw) {
        addRequirements(claw);
        clawSubsystem = claw;
    }
    @Override
    public void execute() {
        clawSubsystem.open();
    }
}
