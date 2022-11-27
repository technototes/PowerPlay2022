package org.firstinspires.ftc.sixteen750.command.arm;

import org.firstinspires.ftc.sixteen750.command.arm.ElbowGroundIntakePositionCommand;
import org.firstinspires.ftc.sixteen750.command.arm.FlipperPreIntakeCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.SequentialCommandGroup;

public class ArmRetractCommand extends SequentialCommandGroup {
    public ArmRetractCommand(ArmSubsystem s) {
        super(new ElbowGroundIntakePositionCommand(s), new FlipperPreIntakeCommand(s));
    }
}
