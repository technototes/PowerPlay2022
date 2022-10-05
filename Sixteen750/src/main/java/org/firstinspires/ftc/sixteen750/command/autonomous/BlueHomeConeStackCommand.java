package org.firstinspires.ftc.sixteen750.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.cone.ConeReadyToScoreCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;


public class BlueHomeConeStackCommand extends SequentialCommandGroup {
    public BlueHomeConeStackCommand(MecanumDrivebaseSubsystem drive, ClawSubsystem claw, LiftSubsystem lift, ConeSubsystem cone){
        super(new TrajectorySequenceCommand(drive, RobotConstant.HIGH_JUNCTION_HOME).alongWith(new ConeReadyToScoreCommand(cone),
                new ClawOpenCommand(claw), new BlueHomeAutoConeStack(drive, claw, lift, cone), new BlueHomeAutoConeStack(drive, claw, lift, cone), new BlueHomeAutoConeStack(drive, claw, lift, cone),new BlueHomeAutoConeStack(drive, claw, lift, cone)));

    }
}