package org.firstinspires.ftc.swerveteen750.command.liftWithTurret;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.swerveteen750.command.lift.LiftFloorIntakeCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftLowPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.TurretFrontCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class FrontScoreLowCommand extends SequentialCommandGroup {
    public FrontScoreLowCommand(LiftSubsystem s) {
        super(
                new TurretFrontCommand(s),
                new LiftLowPoleCommand(s)
        );
    }
}
