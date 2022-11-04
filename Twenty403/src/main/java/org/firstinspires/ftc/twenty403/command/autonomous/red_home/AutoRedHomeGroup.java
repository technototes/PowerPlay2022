package org.firstinspires.ftc.twenty403.command.autonomous.red_home;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsRed;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoRedHomeGroup extends SequentialCommandGroup {
    public AutoRedHomeGroup(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        //super(
                //new TrajectorySequenceCommand(drive, AutoConstantsRed.Home.START_TO_S_JUNCTION)
                /*.alongWith(new ConeReadyScoreHigh(lift, claw))
                new ClawOpenCommand(claw),
                new AutoRedHomeConeStackCommand(drive, lift, claw),
                new AutoRedHomeConeStackCommand(drive, lift, claw),
                new AutoRedHomeConeStackCommand(drive, lift, claw),
                new AutoRedHomeConeStackCommand(drive, lift, claw),*/
                // TODO: This should be our "use vision to decide where to park" command
                //new TrajectorySequenceCommand(drive, AutoConstantsRed.Home.S_JUNCTION_TO_RIGHT));
    }
}
