package org.firstinspires.ftc.sixteen750.command.compound;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;

public class ArmIntakeToScoreCommand extends SequentialCommandGroup {
    // TODO: might to a good idea to detect whether the arm is at the intake position first
    public ArmIntakeToScoreCommand(ArmSubsystem armSubsystem) {
        // The Arm cannot move from intake to score all the way through, since the servos are not powerful enough?
        // Since these Arm commands don't have isFinished() methods, which means they will end immediately
        super(
                new ArmUpwardCommand(armSubsystem),
                new WaitCommand(0.25),
                new ArmScoreCommand(armSubsystem)
        );
    }
}
