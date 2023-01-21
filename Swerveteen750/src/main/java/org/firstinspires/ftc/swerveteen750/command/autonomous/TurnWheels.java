package org.firstinspires.ftc.swerveteen750.command.autonomous;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.command.drive.SwerveDriveSetRotationCommand;

public class TurnWheels extends ParallelCommandGroup {
    public TurnWheels(Robot r, double a) {
        super(
                new SwerveDriveSetRotationCommand(r.swerveDriveSubsystem.rightFrontModule, a),
                new SwerveDriveSetRotationCommand(r.swerveDriveSubsystem.rightRearModule, a),
                new SwerveDriveSetRotationCommand(r.swerveDriveSubsystem.leftFrontModule, a),
                new SwerveDriveSetRotationCommand(r.swerveDriveSubsystem.leftRearModule, a),
                new WaitCommand(1)
        );
    }
}
