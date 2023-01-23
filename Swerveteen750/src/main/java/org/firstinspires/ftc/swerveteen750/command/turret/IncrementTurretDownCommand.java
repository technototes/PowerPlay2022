package org.firstinspires.ftc.swerveteen750.command.turret;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class IncrementTurretDownCommand implements Command {
    LiftSubsystem liftSubsystem;
    public IncrementTurretDownCommand(LiftSubsystem liftSubsystem){
        this.liftSubsystem = liftSubsystem;
        addRequirements(liftSubsystem);
    }
    @Override
    public void execute() {
        liftSubsystem.turretIncrementDown();
    }
}
