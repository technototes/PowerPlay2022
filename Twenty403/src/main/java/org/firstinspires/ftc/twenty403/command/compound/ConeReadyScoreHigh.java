package org.firstinspires.ftc.twenty403.command.compound;

import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.ParallelCommandGroup;

public class ConeReadyScoreHigh extends ParallelCommandGroup {
    public ConeReadyScoreHigh(LiftSubsystem liftSubsystem, ClawSubsystem clawSubsystem) {
        super(
                new ClawOpenCommand(clawSubsystem),
                new LiftHighJunctionCommand(liftSubsystem)
        );
    }
}