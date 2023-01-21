package org.firstinspires.ftc.swerveteen750.command.liftWithTurret;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.swerveteen750.command.lift.LiftHighPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.lift.LiftMidPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.TurretRearCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.TurretSideCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class SideScoreMidCommand extends SequentialCommandGroup {
    public SideScoreMidCommand (LiftSubsystem s) {
        super(
                new LiftMidPoleCommand(s),
                new TurretSideCommand(s)
        );
    }
}
