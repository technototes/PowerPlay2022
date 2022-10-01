package org.firstinspires.ftc.sixteen750.command.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

public class BlueAwayAutoGroup extends SequentialCommandGroup {
    public BlueAwayAutoGroup (MechanumDrive drive, ClawSubsystem claw, LiftSubsystem lift, ConeSubsystem cone) {
        super (new TrajectorySequenceCommand(drive,Robot))
    }

}
