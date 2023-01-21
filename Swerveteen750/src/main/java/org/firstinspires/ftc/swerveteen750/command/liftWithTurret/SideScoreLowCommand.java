package org.firstinspires.ftc.swerveteen750.command.liftWithTurret;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.swerveteen750.command.lift.LiftLowPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.TurretLeftCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class SideScoreLowCommand extends SequentialCommandGroup {
    public SideScoreLowCommand (LiftSubsystem s) {
        super(
                new LiftLowPoleCommand(s),
                new TurretLeftCommand(s)
        );
    }
}
