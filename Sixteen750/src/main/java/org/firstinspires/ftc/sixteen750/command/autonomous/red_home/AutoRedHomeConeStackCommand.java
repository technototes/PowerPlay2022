package org.firstinspires.ftc.sixteen750.command.autonomous.red_home;

import org.firstinspires.ftc.sixteen750.subsystem.ArmSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.PathingMecanumDrivebaseSubsystem;

public class AutoRedHomeConeStackCommand extends SequentialCommandGroup {
    public AutoRedHomeConeStackCommand(PathingMecanumDrivebaseSubsystem driveSubsystem, LiftSubsystem liftSubsystem, ArmSubsystem armSubsystem, ClawSubsystem clawSubsystem) {
        super();
    }
}
