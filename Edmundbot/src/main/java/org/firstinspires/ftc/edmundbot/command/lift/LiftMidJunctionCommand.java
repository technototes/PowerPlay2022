package org.firstinspires.ftc.edmundbot.command.lift;

import org.firstinspires.ftc.edmundbot.subsystem.LiftSubsystem;

public class LiftMidJunctionCommand extends LiftHeightCommand {

    public LiftMidJunctionCommand(LiftSubsystem ls) {
        super(ls, JunctionHeight.Medium);
    }
}
