package org.firstinspires.ftc.swerveteen750.command.liftWithTurret;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.swerveteen750.command.lift.LiftFloorIntakeCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftHighPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftLowPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.TurretRearCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class RearScoreLowCommand extends SequentialCommandGroup {
    public RearScoreLowCommand(LiftSubsystem s) {
        super(
                new LiftLowPoleCommand(s),
                new TurretRearCommand(s)
        );
    }
}
