package org.firstinspires.ftc.swerveteen750.command.drive;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;

public class RRDriveSetDistanceCommand implements Command {
    private ConfigurableSwerveDriveSubsystem subsystem;
    private double ticksPerdistance;
    private double startingEncoderValue;

    public RRDriveSetDistanceCommand(ConfigurableSwerveDriveSubsystem s, double d) {
        subsystem = s;
        ticksPerdistance = ConfigurableSwerveDriveSubsystem.getTicksFromInches(d);
        startingEncoderValue = s.rightRearModule.getWheelPosition();
    }

    @Override
    public void execute() {
        while (subsystem.rightRearModule.getWheelPosition() - startingEncoderValue < ticksPerdistance) {
            subsystem.rightRearModule.setMotorPower(1);
        }
        subsystem.rightRearModule.setMotorPower(0);
    }

}
