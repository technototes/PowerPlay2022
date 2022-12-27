package org.firstinspires.ftc.swerveteen750.command.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;

public class LiftFloorIntakeCommand implements Command {
    public LiftSubsystem liftSubsystem;

    public LiftFloorIntakeCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
    }

    @Override
    public void execute() {
        liftSubsystem.gotoFloorIntake();
        ConfigurableSwerveDriveSubsystem.STICK_X_SCALAR = ConfigurableSwerveDriveSubsystem.STICK_SCALAR_HIGH;
        ConfigurableSwerveDriveSubsystem.STICK_Y_SCALAR = ConfigurableSwerveDriveSubsystem.STICK_SCALAR_HIGH;
    }
}
