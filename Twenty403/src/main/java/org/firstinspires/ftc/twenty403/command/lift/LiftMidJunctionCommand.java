package org.firstinspires.ftc.twenty403.command.lift;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class LiftMidJunctionCommand extends LiftHeightCommand {

    public LiftMidJunctionCommand(LiftSubsystem ls) {
        super(ls, JunctionHeight.Medium);
    }
}
