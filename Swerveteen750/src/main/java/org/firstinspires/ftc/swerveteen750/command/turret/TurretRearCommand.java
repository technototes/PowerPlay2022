package org.firstinspires.ftc.swerveteen750.command.turret;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class TurretRearCommand implements Command {
    private LiftSubsystem subsystem;

    public TurretRearCommand(LiftSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.turretServoPositionRear();
    }
}
