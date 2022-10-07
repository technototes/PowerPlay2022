package org.firstinspires.ftc.sixteen750.command.autonomous;

import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.cone.ConeReadyToScoreCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class BlueHomeConeStackCommand extends SequentialCommandGroup {
    public BlueHomeConeStackCommand(MecanumDrivebaseSubsystem drive, ClawSubsystem claw, LiftSubsystem lift, ConeSubsystem cone){
        super(new TrajectorySequenceCommand(drive, RobotConstant.HIGH_JUNCTION_HOME).alongWith(new ConeReadyToScoreCommand(cone)),
                new ClawOpenCommand(claw), new BlueHomeAutoConeStack(drive, claw, lift, cone), new BlueHomeAutoConeStack(drive, claw, lift, cone),
                new BlueHomeAutoConeStack(drive, claw, lift, cone),new BlueHomeAutoConeStack(drive, claw, lift, cone),
                new TrajectorySequenceCommand(drive.RobotConstant.PARK_LOCATION)/*Placeholder for what we're doing for parking*/);

    }
}
