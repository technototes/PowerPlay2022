package org.firstinspires.ftc.twenty403.command.lift;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class LiftLowJunctionCommand extends LiftHeightCommand {

    public LiftLowJunctionCommand(LiftSubsystem ls) {
        super(ls, JunctionHeight.Low);
    }
}
