package org.firstinspires.ftc.swerveteen750.command.liftWithTurret;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.swerveteen750.command.lift.LiftFloorIntakeCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.TurretFrontCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.TurretRearCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class RearIntake extends SequentialCommandGroup {
    public RearIntake(LiftSubsystem s) {
        super(
                new TurretRearCommand(s),
                new LiftFloorIntakeCommand(s)
        );
    }
}
