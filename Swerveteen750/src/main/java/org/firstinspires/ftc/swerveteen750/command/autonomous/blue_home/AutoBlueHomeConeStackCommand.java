package org.firstinspires.ftc.swerveteen750.command.autonomous.blue_home;

import org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoBlueHomeConeStackCommand extends SequentialCommandGroup {
    public AutoBlueHomeConeStackCommand(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ArmSubsystem arm, ClawSubsystem claw) {
        super();
    }
}
