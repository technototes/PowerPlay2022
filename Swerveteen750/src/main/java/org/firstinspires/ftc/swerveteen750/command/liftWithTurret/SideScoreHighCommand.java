package org.firstinspires.ftc.swerveteen750.command.liftWithTurret;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.swerveteen750.command.lift.LiftHighPoleCommand;
import org.firstinspires.ftc.swerveteen750.command.turret.TurretLeftCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class SideScoreHighCommand extends SequentialCommandGroup {
    public SideScoreHighCommand (LiftSubsystem s) {
        super(
                new LiftHighPoleCommand(s),
                new TurretLeftCommand(s)
        );
    }
}
