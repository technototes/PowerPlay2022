package org.firstinspires.ftc.sixteen750.command.autonomous.red_away;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.PathingMecanumDrivebaseSubsystem;

public class AutoRedAwayConeStackCommand extends SequentialCommandGroup {
    public AutoRedAwayConeStackCommand(PathingMecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super();
    }
}
