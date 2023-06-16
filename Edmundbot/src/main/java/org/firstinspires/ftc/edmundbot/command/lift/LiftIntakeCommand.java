package org.firstinspires.ftc.edmundbot.command.lift;

import org.firstinspires.ftc.edmundbot.subsystem.LiftSubsystem;

public class LiftIntakeCommand extends LiftHeightCommand {

    public LiftIntakeCommand(LiftSubsystem ls) {
        super(ls, JunctionHeight.Intake);
    }
}
