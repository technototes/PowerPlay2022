package org.firstinspires.ftc.swerveteen750.command.compound;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.swerveteen750.command.arm.ElbowScoreCommand;
import org.firstinspires.ftc.swerveteen750.command.arm.FlipperScoreCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem;

public class ArmScoreCommand extends SequentialCommandGroup {
    public ArmScoreCommand(ArmSubsystem armSubsystem) {
        super(new ElbowScoreCommand(armSubsystem), new FlipperScoreCommand(armSubsystem));
    }
}
