package org.firstinspires.ftc.twenty403.command.claw;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.lift.LiftUpCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class ClawAutoCloseWithLift extends SequentialCommandGroup {
    public ClawAutoCloseWithLift (ClawSubsystem c, LiftSubsystem l){
        super(
            new ClawCloseCommand(c), new WaitCommand(.2), new LiftUpCommand(l)
                );
    }
}
