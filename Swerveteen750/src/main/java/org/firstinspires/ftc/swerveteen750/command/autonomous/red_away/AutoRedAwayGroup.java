package org.firstinspires.ftc.swerveteen750.command.autonomous.red_away;

import org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoRedAwayGroup extends SequentialCommandGroup {
    public AutoRedAwayGroup(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ArmSubsystem arm, ClawSubsystem claw) {
        super();
    }
}
