package org.firstinspires.ftc.twenty403.command.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class LiftIntakeCommand implements Command {

    LiftSubsystem lift;

    public LiftIntakeCommand(LiftSubsystem ls) {
        lift = ls;
        addRequirements(lift);
    }

    public void execute() {
        lift.intakePos();
    }
}
