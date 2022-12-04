package org.firstinspires.ftc.twenty403.command.autonomous;

import java.util.function.BooleanSupplier;

import com.technototes.library.command.Command;
import com.technototes.library.command.ConditionalCommand;

public class MaybeRunThenFinishWith extends ConditionalCommand {

    public MaybeRunThenFinishWith(BooleanSupplier condition, Command optionalCommand, Command finishCommand) {
        super(condition, optionalCommand.andThen(finishCommand), finishCommand);
    }
}
