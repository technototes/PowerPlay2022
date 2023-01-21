package org.firstinspires.ftc.swerveteen750.command.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.swerveteen750.command.drive.SwerveDriveSetDistanceCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.SwerveDriveSubsystem;

public class DriveStraight extends ParallelCommandGroup {
    public DriveStraight(ConfigurableSwerveDriveSubsystem s) {
       super(
     //          new SwerveDriveSetDistanceCommand(ConfigurableSwerveDriveSubsystem.left)
       );
    }
}
