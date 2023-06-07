package org.firstinspires.ftc.swerveteen750.command.lift;

import com.technototes.library.command.Command;
import com.technototes.library.control.CommandGamepad;

import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class ResetLiftZeroCommand implements Command {
    LiftSubsystem liftSubsystem;
    CommandGamepad gamepad;

    public ResetLiftZeroCommand(LiftSubsystem l, CommandGamepad g) {
        liftSubsystem = l;
        gamepad = g;
    }

    @Override
    public void execute() {
        liftSubsystem.setNewZero();
        gamepad.rumble(1);
    }
}
