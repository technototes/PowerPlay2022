package org.firstinspires.ftc.sixteen750.command.autonomous;

import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class BlueAwayAutoConeStack extends SequentialCommandGroup {
    public BlueAwayAutoConeStack(MecanumDrivebaseSubsystem drive, ClawSubsystem claw, LiftSubsystem lift, ConeSubsystem cone) {
        super (new TrajectorySequenceCommand(drive,RobotConstant.BLUE_AWAY_STACK).alongWith(new ConeReadyToIntakeCommand(cone)),
                TrajectorySequenceCommand(drive, RobotConstant.HIGH_JUNCTION_AWAY).alongWith(new ConeReadyToScoreCommand(cone)),
                new ClawOpenCommand(claw) );
    }

}
