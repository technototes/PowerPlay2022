package org.firstinspires.ftc.edmundbot.command.lift;

import org.firstinspires.ftc.edmundbot.subsystem.LiftSubsystem;

public class LiftLowJunctionCommand extends LiftHeightCommand {

    public LiftLowJunctionCommand(LiftSubsystem ls) {
        super(ls, JunctionHeight.Low);
    }
}
