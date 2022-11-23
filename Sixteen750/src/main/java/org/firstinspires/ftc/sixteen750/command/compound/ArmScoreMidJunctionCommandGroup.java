package org.firstinspires.ftc.sixteen750.command.compound;

import org.firstinspires.ftc.sixteen750.command.arm.ElbowScoreMidJunctionCommand;
import org.firstinspires.ftc.sixteen750.command.arm.FlipperScoreMidJunctionCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.SequentialCommandGroup;

public class ArmScoreMidJunctionCommandGroup extends SequentialCommandGroup {
    public ArmScoreMidJunctionCommandGroup(ArmSubsystem armSubsystem) {
        super(new ElbowScoreMidJunctionCommand(armSubsystem), new FlipperScoreMidJunctionCommand(armSubsystem));
    }
}
