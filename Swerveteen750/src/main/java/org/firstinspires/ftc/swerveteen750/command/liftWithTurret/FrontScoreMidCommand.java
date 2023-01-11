package org.firstinspires.ftc.swerveteen750.command.liftWithTurret;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.swerveteen750.command.lift.LiftLowPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftMidPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.TurretFrontCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class FrontScoreMidCommand extends SequentialCommandGroup {
    public FrontScoreMidCommand(LiftSubsystem s) {
        super(
                new LiftMidPoleCommand(s),
                new TurretFrontCommand(s)
        );
    }
}
