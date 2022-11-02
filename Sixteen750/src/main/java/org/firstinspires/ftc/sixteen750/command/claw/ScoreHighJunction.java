package org.firstinspires.ftc.sixteen750.command.claw;

import org.firstinspires.ftc.sixteen750.command.claw.Servos.ElbowHighJunction;
import org.firstinspires.ftc.sixteen750.command.claw.Servos.FlipperHighJunction;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.SequentialCommandGroup;

public class ScoreHighJunction extends SequentialCommandGroup {
    public ScoreHighJunction(ClawSubsystem s) {
        super(new FlipperHighJunction(s).alongWith(new ElbowHighJunction(s), new ClawOpenCommand(s)));
    }
}
