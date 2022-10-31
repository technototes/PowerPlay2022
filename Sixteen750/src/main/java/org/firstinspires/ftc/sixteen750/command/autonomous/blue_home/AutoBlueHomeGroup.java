package org.firstinspires.ftc.sixteen750.command.autonomous.blue_home;

import org.firstinspires.ftc.sixteen750.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.compound.ConeReadyToScoreCommand;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoBlueHomeGroup extends SequentialCommandGroup {
    public AutoBlueHomeGroup(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.START_TO_S_JUNCTION)
                        .alongWith(new ConeReadyToScoreCommand(lift, claw)),
                new ClawOpenCommand(claw),
                new AutoBlueHomeConeStackCommand(drive, lift, claw),
                new AutoBlueHomeConeStackCommand(drive, lift, claw),
                new AutoBlueHomeConeStackCommand(drive, lift, claw),
                new AutoBlueHomeConeStackCommand(drive, lift, claw),
                new TrajectorySequenceCommand(
                        drive,
                        AutoConstantsBlue.Home.E_JUNCTION_TO_MIDDLE) /*Placeholder for what we're doing for parking*/);
    }
}
