package org.firstinspires.ftc.twenty403.command.compound;

import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawReleaseCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;

public class ScoreHighJunctionCommandGroup extends SequentialCommandGroup {
    public ScoreHighJunctionCommandGroup(LiftSubsystem lift, ClawSubsystem claw) {
        super(
                new LiftHighJunctionCommand(lift),
                new ClawReleaseCommand(claw),
                new ClawOpenCommand(claw),
                new ConeReadyToIntakeCommand(claw, lift));
    }
}
