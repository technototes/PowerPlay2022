package org.firstinspires.ftc.sixteen750.command.compound;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.sixteen750.command.arm.ElbowUpwardCommand;
import org.firstinspires.ftc.sixteen750.command.arm.FlipperUpwardCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;

public class ArmUpwardCommand extends SequentialCommandGroup {
    public ArmUpwardCommand(ArmSubsystem armSubsystem) {
        super(new ElbowUpwardCommand(armSubsystem), new FlipperUpwardCommand(armSubsystem));
    }
}
