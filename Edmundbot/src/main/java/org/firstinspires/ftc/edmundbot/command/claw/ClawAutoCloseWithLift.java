package org.firstinspires.ftc.edmundbot.command.claw;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.edmundbot.command.lift.LiftUpCommand;
import org.firstinspires.ftc.edmundbot.subsystem.ClawSubsystem;
import org.firstinspires.ftc.edmundbot.subsystem.LiftSubsystem;

public class ClawAutoCloseWithLift extends SequentialCommandGroup {

    public ClawAutoCloseWithLift(ClawSubsystem c, LiftSubsystem l) {
        super(new ClawCloseCommand(c), new WaitCommand(.2), new LiftUpCommand(l));
    }
}
