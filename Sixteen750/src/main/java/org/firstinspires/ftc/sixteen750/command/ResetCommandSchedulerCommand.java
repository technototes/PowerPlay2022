package org.firstinspires.ftc.sixteen750.command;

import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandGamepad;

public class ResetCommandSchedulerCommand implements Command {
    CommandGamepad gamepad;
    public ResetCommandSchedulerCommand(CommandGamepad gamepad) {
        this.gamepad = gamepad;
    }

    @Override
    public void execute() {
        CommandScheduler.resetScheduler();
        gamepad.rumble(0.5);
    }
}
