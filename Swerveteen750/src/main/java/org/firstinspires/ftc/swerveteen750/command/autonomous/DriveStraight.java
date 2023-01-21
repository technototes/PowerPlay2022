package org.firstinspires.ftc.swerveteen750.command.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.command.drive.SwerveDriveSetDistanceCommand;
import org.firstinspires.ftc.swerveteen750.command.drive.SwerveDriveSetRotationCommand;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.SwerveDriveSubsystem;

public class DriveStraight extends ParallelCommandGroup {
    public DriveStraight(Robot r, double d) {
       super(

               new SwerveDriveSetDistanceCommand(r.swerveDriveSubsystem.leftFrontModule, d),
                       new SwerveDriveSetDistanceCommand(r.swerveDriveSubsystem.leftRearModule, d),
                       new SwerveDriveSetDistanceCommand(r.swerveDriveSubsystem.rightFrontModule, d),
                       new SwerveDriveSetDistanceCommand(r.swerveDriveSubsystem.rightRearModule, d)

       );
    }
}
