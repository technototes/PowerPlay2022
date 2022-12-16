package org.firstinspires.ftc.swerveteen750.command.compound;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.swerveteen750.command.arm.ElbowIntakeCommand;
import org.firstinspires.ftc.swerveteen750.command.arm.FlipperIntakeCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem;

public class ArmIntakeCommand extends SequentialCommandGroup {
    public ArmIntakeCommand(ArmSubsystem armSubsystem) {
        super(new ElbowIntakeCommand(armSubsystem), new FlipperIntakeCommand(armSubsystem));
    }
}
