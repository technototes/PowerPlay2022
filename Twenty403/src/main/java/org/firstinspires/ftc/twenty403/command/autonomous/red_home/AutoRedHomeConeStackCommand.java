package org.firstinspires.ftc.twenty403.command.autonomous.red_home;

import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoRedHomeConeStackCommand extends SequentialCommandGroup {
    public AutoRedHomeConeStackCommand(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                // new TrajectorySequenceCommand(drive, AutoConstantsRed.Home.S_JUNCTION_TO_STACK)
                /*.alongWith(new ConeReadyToIntakeCommand(lift, claw))*/
                new ClawCloseCommand(claw),
                // new TrajectorySequenceCommand(drive, AutoConstantsRed.Home.STACK_TO_S_JUNCTION)
                /*.alongWith(new ConeReadyScoreHigh(lift, claw))*/
                new ClawOpenCommand(claw));
    }
}
