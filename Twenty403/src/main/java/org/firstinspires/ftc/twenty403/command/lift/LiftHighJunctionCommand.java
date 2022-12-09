package org.firstinspires.ftc.twenty403.command.lift;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class LiftHighJunctionCommand extends LiftHeightCommand {

    public LiftHighJunctionCommand(LiftSubsystem ls) {
        super(ls, JunctionHeight.High);
    }
}
