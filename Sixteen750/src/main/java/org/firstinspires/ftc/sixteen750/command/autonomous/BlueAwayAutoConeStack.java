package org.firstinspires.ftc.sixteen750.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

public class BlueAwayAutoConeStack extends SequentialCommandGroup {
    public BlueAwayAutoConeStack(MecanumDrivebaseSubsystem drive, ClawSubsystem claw, LiftSubsystem lift, ConeSubsystem cone) {
        super (new TrajectorySequenceCommand(drive,RobotConstant.BLUE_AWAY_STACK))
    }

}
