package org.firstinspires.ftc.twenty403.command.lift;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class LiftGroundJunctionCommand extends LiftHeightCommand {

    public LiftGroundJunctionCommand(LiftSubsystem ls) {
        super(ls, JunctionHeight.Ground);
    }
}
