package org.firstinspires.ftc.twenty403.command.autonomous;

import com.technototes.library.command.ChoiceCommand;
import com.technototes.library.command.Command;
import com.technototes.library.command.ConditionalCommand;

import java.util.function.BooleanSupplier;

public class MaybeRunThenFinishWith extends ConditionalCommand {


    public MaybeRunThenFinishWith(BooleanSupplier condition, Command optionalCommand, Command finishCommand) {
        super(condition, optionalCommand.andThen(finishCommand), finishCommand);
    }
}
