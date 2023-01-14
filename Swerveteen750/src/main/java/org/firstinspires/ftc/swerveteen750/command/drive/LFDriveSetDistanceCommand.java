package org.firstinspires.ftc.swerveteen750.command.drive;

import com.qualcomm.robotcore.util.Range;
import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;

public class LFDriveSetDistanceCommand implements Command {
    private ConfigurableSwerveDriveSubsystem subsystem;
    private double ticksPerdistance;
    private double startingEncoderValue;

    public LFDriveSetDistanceCommand(ConfigurableSwerveDriveSubsystem s, double d) {
        subsystem = s;
        ticksPerdistance = ConfigurableSwerveDriveSubsystem.getTicksFromInches(d);
        startingEncoderValue = s.leftFrontModule.getWheelPosition();
    }

    @Override
    public void execute() {
        while (subsystem.leftFrontModule.getWheelPosition() - startingEncoderValue < ticksPerdistance) {
            subsystem.leftFrontModule.setMotorPower(1);
        }
        subsystem.leftFrontModule.setMotorPower(0);
    }

}
