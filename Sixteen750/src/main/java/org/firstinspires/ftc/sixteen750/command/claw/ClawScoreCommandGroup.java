package org.firstinspires.ftc.sixteen750.command.claw;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.SequentialCommandGroup;

public class ClawScoreCommandGroup extends SequentialCommandGroup {
    public ClawScoreCommandGroup(ClawSubsystem s) {
        super(new ElbowScoreMidJunctionCommand(s), new FlipperScoreMidJunctionCommand(s));
    }
}
