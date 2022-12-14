package org.firstinspires.ftc.swerveteen750.command.autonomous.red_away;

import org.firstinspires.ftc.swerveteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoRedAwayConeStackCommand extends SequentialCommandGroup {
    public AutoRedAwayConeStackCommand(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super();
    }
}
