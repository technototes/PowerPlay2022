package org.firstinspires.ftc.swerveteen750.command.drive;

import com.qualcomm.robotcore.util.Range;
import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;

public class LFDriveSetRotation implements Command {
    private ConfigurableSwerveDriveSubsystem subsystem;

    public LFDriveSetRotation(ConfigurableSwerveDriveSubsystem s){
        subsystem = s;


    }
    @Override
    public void execute() {
        double target = subsystem.leftFrontModule.rotationController
                .update(subsystem.getLFModuleRotationOrientation());
        double lclippedTarget= Range.clip(target, -ConfigurableSwerveDriveSubsystem.MAX_SPEED,
                ConfigurableSwerveDriveSubsystem.MAX_SPEED);
        subsystem.setLFModuleOrientations(lclippedTarget);
    }
}
