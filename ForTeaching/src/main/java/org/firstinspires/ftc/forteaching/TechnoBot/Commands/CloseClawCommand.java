package org.firstinspires.ftc.forteaching.TechnoBot.Commands;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.ClawSubsystem;

public class CloseClawCommand implements Command {

    private ClawSubsystem clawSubsystem;

    public CloseClawCommand(ClawSubsystem claw) {
        addRequirements(claw);
        clawSubsystem = claw;
    }

    @Override
    public void execute() {
        clawSubsystem.close();
    }
}
