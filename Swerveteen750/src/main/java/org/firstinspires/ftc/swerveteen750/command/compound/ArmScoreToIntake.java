package org.firstinspires.ftc.swerveteen750.command.compound;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem;

public class ArmScoreToIntake extends SequentialCommandGroup {
    // TODO: might to a good idea to detect whether the arm is at the score position first
    public ArmScoreToIntake(ArmSubsystem armSubsystem) {
        super(
                new ArmUpwardCommand(armSubsystem),
                new WaitCommand(0.25),
                new ArmIntakeCommand(armSubsystem)
        );
    }
}
