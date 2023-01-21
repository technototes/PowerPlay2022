package org.firstinspires.ftc.swerveteen750.command.autonomous.right;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.swerveteen750.command.autonomous.DriveStraight;
import org.firstinspires.ftc.swerveteen750.command.autonomous.TurnWheels;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.MecanumDriveSubsystem;

public class RightJustParkMiddle extends SequentialCommandGroup {
    public RightJustParkMiddle(Robot r){
        super(
                new TurnWheels(r, 0),
                new DriveStraight(r,40)
        );
    }
}
