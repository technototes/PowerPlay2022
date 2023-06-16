package org.firstinspires.ftc.edmundbot.command.lift;

import org.firstinspires.ftc.edmundbot.subsystem.LiftSubsystem;

public class LiftHighJunctionCommand extends LiftHeightCommand {

    public LiftHighJunctionCommand(LiftSubsystem ls) {
        super(ls, JunctionHeight.High);
    }
}
