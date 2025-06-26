package org.firstinspires.ftc.sixteen750.command.autonomous.blue_away;

import org.firstinspires.ftc.sixteen750.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.PathingMecanumDrivebaseSubsystem;

public class AutoBlueAwayConeStackCommand extends SequentialCommandGroup {
    public AutoBlueAwayConeStackCommand(PathingMecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super();
    }
}
