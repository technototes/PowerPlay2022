package org.firstinspires.ftc.sixteen750.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.Subsystems.LiftSubsystem;
import org.firstinspires.ftc.sixteen750.command.Claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.cone.ConeReadyToIntakeCommand;
import org.firstinspires.ftc.sixteen750.command.cone.ConeReadyToScoreCommand;
import org.firstinspires.ftc.sixteen750.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;

public class BlueHomeAutoGroup extends SequentialCommandGroup {
    public BlueHomeAutoGroup (MechanumDrive drive, ClawSubsystem claw, LiftSubsystem lift, ConeSubsystem cone) {
        super(new TrajectorySequenceCommand(drive, RobotConstant.HIGH_JUNCTION_HOME).alongWith(new ConeReadyToScoreCommand(cone)),
                new ClawOpenCommand(claw), new TrajectorySequenceCommand(drive, RobotConstant.BLUE_HOME_STACK).alongWith(new ConeReadyToIntakeCommand(cone)), new ConeReadyToScoreCommand()

    }
}
