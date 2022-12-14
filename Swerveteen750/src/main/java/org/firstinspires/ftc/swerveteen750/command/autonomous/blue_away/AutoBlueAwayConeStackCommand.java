package org.firstinspires.ftc.swerveteen750.command.autonomous.blue_away;

import org.firstinspires.ftc.swerveteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoBlueAwayConeStackCommand extends SequentialCommandGroup {
    public AutoBlueAwayConeStackCommand(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super();
    }
}
