package org.firstinspires.ftc.swerveteen750.command.compound;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.swerveteen750.command.arm.ElbowUpwardCommand;
import org.firstinspires.ftc.swerveteen750.command.arm.FlipperUpwardCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem;

public class ArmUpwardCommand extends SequentialCommandGroup {
    public ArmUpwardCommand(ArmSubsystem armSubsystem) {
        super(new ElbowUpwardCommand(armSubsystem), new FlipperUpwardCommand(armSubsystem));
    }
}
