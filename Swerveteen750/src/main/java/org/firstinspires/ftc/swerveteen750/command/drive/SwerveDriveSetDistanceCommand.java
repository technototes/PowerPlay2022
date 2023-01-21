package org.firstinspires.ftc.swerveteen750.command.drive;

import com.qualcomm.robotcore.util.Range;
import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.swerve_util.swerve_module.AnotherSwerveModule;

public class SwerveDriveSetDistanceCommand implements Command {

    private double ticksPerdistance;
    private double startingEncoderValue;
    private AnotherSwerveModule module;



    public SwerveDriveSetDistanceCommand(AnotherSwerveModule m, double d) {
        module = m;
        ticksPerdistance = ConfigurableSwerveDriveSubsystem.getTicksFromInches(d);
        startingEncoderValue = module.getWheelPosition();
    }


    @Override
    public void execute() {
        module.setMotorVelocity(.5);
    }

    @Override
    public boolean isFinished() {

        if (Math.abs(module.getWheelPosition() - startingEncoderValue)  < ticksPerdistance) {
            return false;
        } else {
            module.setMotorVelocity(0);
            return true;
        }
    }
}
