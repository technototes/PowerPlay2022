package org.firstinspires.ftc.sixteen750.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import org.firstinspires.ftc.sixteen750.Subsystems.LiftSubsystem;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.cone.ConeReadyToIntakeCommand;
import org.firstinspires.ftc.sixteen750.command.cone.ConeReadyToScoreCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;

public class BlueHomeAutoConeStack extends SequentialCommandGroup {
    public BlueHomeAutoConeStack(MecanumDrivebaseSubsystem drive, ClawSubsystem claw, LiftSubsystem lift, ConeSubsystem cone) {
        super(new TrajectorySequenceCommand(drive, RobotConstant.BLUE_HOME_STACK).alongWith(new ConeReadyToIntakeCommand(cone)),
                new TrajectorySequenceCommand(drive, RobotConstant.HIGH_JUNCTION_HOME).alongWith(new ConeReadyToScoreCommand(cone)),
                new ClawOpenCommand(claw) );

    }
}
