package org.firstinspires.ftc.swerveteen750.command.turret;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class TurretLeftCommand implements Command {
    private LiftSubsystem subsystem;

    public TurretLeftCommand(LiftSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.turretServoPositionSide();
    }
}
