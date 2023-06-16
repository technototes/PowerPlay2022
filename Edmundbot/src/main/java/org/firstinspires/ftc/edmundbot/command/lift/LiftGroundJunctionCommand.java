package org.firstinspires.ftc.edmundbot.command.lift;

import org.firstinspires.ftc.edmundbot.subsystem.LiftSubsystem;

public class LiftGroundJunctionCommand extends LiftHeightCommand {

    public LiftGroundJunctionCommand(LiftSubsystem ls) {
        super(ls, JunctionHeight.Ground);
    }
}
