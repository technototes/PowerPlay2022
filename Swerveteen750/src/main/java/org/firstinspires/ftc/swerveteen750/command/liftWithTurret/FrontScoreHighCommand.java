package org.firstinspires.ftc.swerveteen750.command.liftWithTurret;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.swerveteen750.command.lift.LiftHighPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftLowPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.TurretFrontCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class FrontScoreHighCommand extends SequentialCommandGroup {
    public FrontScoreHighCommand(LiftSubsystem s) {
        super(
                new LiftHighPoleCommand(s),
                new TurretFrontCommand(s)
        );
    }
}
