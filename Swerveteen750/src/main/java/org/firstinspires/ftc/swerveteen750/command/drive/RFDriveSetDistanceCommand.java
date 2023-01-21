package org.firstinspires.ftc.swerveteen750.command.drive;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.swerve_util.swerve_module.AnotherSwerveModule;

public class RFDriveSetDistanceCommand implements Command {

    private double ticksPerdistance;
    private double startingEncoderValue;
    private AnotherSwerveModule module;

    public RFDriveSetDistanceCommand(AnotherSwerveModule m, double d) {
        module = m;
        ticksPerdistance = ConfigurableSwerveDriveSubsystem.getTicksFromInches(d);
        startingEncoderValue = module.getWheelPosition();
    }

    @Override
    public void execute() {
        module.setMotorVelocity(1);
    }

    public boolean isFinished() {
        if (module.getWheelPosition() - startingEncoderValue < ticksPerdistance) {
          return false;
        }
        else {
            module.setMotorVelocity(0);
            return true;
        }
    }
}