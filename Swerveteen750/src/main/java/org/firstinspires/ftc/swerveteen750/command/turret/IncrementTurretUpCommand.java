package org.firstinspires.ftc.swerveteen750.command.turret;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class IncrementTurretUpCommand implements Command {
    private LiftSubsystem subsystem;

    public IncrementTurretUpCommand(LiftSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
          subsystem.turretIncrementUp();
    }
}
