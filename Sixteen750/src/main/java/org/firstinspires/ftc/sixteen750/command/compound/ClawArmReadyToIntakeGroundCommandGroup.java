package org.firstinspires.ftc.sixteen750.command.compound;

import org.firstinspires.ftc.sixteen750.command.arm.ElbowGroundIntakePositionCommand;
import org.firstinspires.ftc.sixteen750.command.arm.FlipperPreIntakeCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.SequentialCommandGroup;

public class ClawArmReadyToIntakeGroundCommandGroup extends SequentialCommandGroup {
    public ClawArmReadyToIntakeGroundCommandGroup(ArmSubsystem armSubsystem, ClawSubsystem clawSubsystem) {
        super(new ElbowGroundIntakePositionCommand(armSubsystem), new FlipperPreIntakeCommand(armSubsystem), new ClawOpenCommand(clawSubsystem));
    }
}
