package org.firstinspires.ftc.swerveteen750.command.autonomous.red_home;

import org.firstinspires.ftc.swerveteen750.subsystem.ArmSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoRedHomeConeStackCommand extends SequentialCommandGroup {
    public AutoRedHomeConeStackCommand(MecanumDrivebaseSubsystem driveSubsystem, LiftSubsystem liftSubsystem, ArmSubsystem armSubsystem, ClawSubsystem clawSubsystem) {
        super();
    }
}
