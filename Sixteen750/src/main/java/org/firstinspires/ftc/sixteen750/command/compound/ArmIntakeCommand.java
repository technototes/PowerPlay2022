package org.firstinspires.ftc.sixteen750.command.compound;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.sixteen750.command.arm.ElbowIntakeCommand;
import org.firstinspires.ftc.sixteen750.command.arm.FlipperIntakeCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;

public class ArmIntakeCommand extends SequentialCommandGroup {
    public ArmIntakeCommand(ArmSubsystem armSubsystem) {
        super(new ElbowIntakeCommand(armSubsystem), new FlipperIntakeCommand(armSubsystem));
    }
}
