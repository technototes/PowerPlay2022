package org.firstinspires.ftc.swerveteen750.command.drive;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;

public class LRDriveSetDistanceCommand implements Command {
    private ConfigurableSwerveDriveSubsystem subsystem;
    private double ticksPerdistance;
    private double startingEncoderValue;

    public LRDriveSetDistanceCommand(ConfigurableSwerveDriveSubsystem s, double d) {
        subsystem = s;
        ticksPerdistance = ConfigurableSwerveDriveSubsystem.getTicksFromInches(d);
        startingEncoderValue = s.leftFrontModule.getWheelPosition();
    }

    @Override
    public void execute() {
        while (subsystem.leftRearModule.getWheelPosition() - startingEncoderValue < ticksPerdistance) {
            subsystem.leftRearModule.setMotorPower(1);
        }
        subsystem.leftRearModule.setMotorPower(0);
    }
}
