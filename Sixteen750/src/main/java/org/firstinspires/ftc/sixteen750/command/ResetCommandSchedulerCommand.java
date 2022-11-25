package org.firstinspires.ftc.sixteen750.command;

import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;

public class ResetCommandSchedulerCommand implements Command {
    public ResetCommandSchedulerCommand() {
    }

    @Override
    public void execute() {
        CommandScheduler.resetScheduler();
    }
}
