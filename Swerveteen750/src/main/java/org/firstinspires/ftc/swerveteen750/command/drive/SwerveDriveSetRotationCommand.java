package org.firstinspires.ftc.swerveteen750.command.drive;

import com.qualcomm.robotcore.util.Range;
import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.swerve_util.swerve_module.AnotherSwerveModule;

public class SwerveDriveSetRotationCommand implements Command {
    private AnotherSwerveModule module;
    private double target;

    public SwerveDriveSetRotationCommand(AnotherSwerveModule m, double t){
        module = m;
        target = t;

    }
    @Override
    public void execute() {
       module.setTargetRotation(target);
    }
}
