package org.firstinspires.ftc.sixteen750.command.claw;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

public class ClawScoreCommandGroup extends SequentialCommandGroup {
    public ClawScoreCommandGroup(ClawSubsystem s) {
        super(
                new ClawCloseCommand(s),
                new ElbowScoreMidJunctionCommand(s),
                new FlipperScoreMidJunctionCommand(s)
        );
    }
}
