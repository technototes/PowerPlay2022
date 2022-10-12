package org.firstinspires.ftc.twenty403.command.autonomous;

import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.compound.ConeReadyScoreHigh;
import org.firstinspires.ftc.twenty403.command.compound.ConeReadyToIntakeCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.ConeSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoRedHomeConeStackCommand extends SequentialCommandGroup {
    public AutoRedHomeConeStackCommand(
            MecanumDrivebaseSubsystem drive, ConeSubsystem cone, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Home.S_JUNCTION_TO_STACK)
                        .alongWith(new ConeReadyToIntakeCommand(cone)),
                new ClawCloseCommand(claw),
                new TrajectorySequenceCommand(drive, AutoConstantsRed.Home.STACK_TO_S_JUNCTION)
                        .alongWith(new ConeReadyScoreHigh(cone)),
                new ClawOpenCommand(claw));
    }
}
