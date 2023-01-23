package org.firstinspires.ftc.swerveteen750.command.liftWithTurret;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.swerveteen750.command.lift.LiftFloorIntakeCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftHighPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftMidPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.TurretRearCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class RearScoreMidCommand extends SequentialCommandGroup {
    public RearScoreMidCommand(LiftSubsystem s) {
        super(
                new LiftMidPoleCommand(s),
                new TurretRearCommand(s)
        );
    }
}
