package org.firstinspires.ftc.sixteen750.command.autonomous.blue_home;

import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoBlueHomeGroup extends SequentialCommandGroup {
    public AutoBlueHomeGroup(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ArmSubsystem arm, ClawSubsystem claw) {
        super();
    }
}
