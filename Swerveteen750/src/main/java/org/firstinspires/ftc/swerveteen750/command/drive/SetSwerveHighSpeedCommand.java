package org.firstinspires.ftc.swerveteen750.command.drive;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;

public class SetSwerveHighSpeedCommand implements Command {
    public SetSwerveHighSpeedCommand(){

    }
    @Override
    public void execute() {
        ConfigurableSwerveDriveSubsystem.STICK_X_SCALAR = ConfigurableSwerveDriveSubsystem.STICK_SCALAR_HIGH;
        ConfigurableSwerveDriveSubsystem.STICK_Y_SCALAR = ConfigurableSwerveDriveSubsystem.STICK_SCALAR_HIGH;
    }
}
