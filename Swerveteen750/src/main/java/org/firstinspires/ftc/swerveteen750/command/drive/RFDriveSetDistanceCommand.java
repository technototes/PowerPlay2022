package org.firstinspires.ftc.swerveteen750.command.drive;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;

public class RFDriveSetDistanceCommand implements Command {
    private ConfigurableSwerveDriveSubsystem subsystem;
    private double ticksPerdistance;
    private double startingEncoderValue;

    public RFDriveSetDistanceCommand(ConfigurableSwerveDriveSubsystem s, double d) {
        subsystem = s;
        ticksPerdistance = ConfigurableSwerveDriveSubsystem.getTicksFromInches(d);
        startingEncoderValue = s.leftFrontModule.getWheelPosition();
    }

    @Override
    public void execute() {
        while (subsystem.rightFrontModule.getWheelPosition() - startingEncoderValue < ticksPerdistance) {
            subsystem.rightFrontModule.setMotorPower(1);
        }
        subsystem.rightFrontModule.setMotorPower(0);
    }
}
