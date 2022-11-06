package org.firstinspires.ftc.twenty403.command.lift;

import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class LiftIntakeCommand extends LiftHeightCommand {
    public LiftIntakeCommand(LiftSubsystem ls) {
        super(ls, JunctionHeight.Intake);
    }
}
