package org.firstinspires.ftc.twenty403.command.autonomous.red_away;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsRed;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class AutoRedAwayConeStackCommand extends SequentialCommandGroup {
    public AutoRedAwayConeStackCommand(MecanumDrivebaseSubsystem drive, LiftSubsystem lift, ClawSubsystem claw) {
        super(
                //new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.E_JUNCTION_TO_STACK)
                /*.alongWith(new ConeReadyToIntakeCommand(lift, claw))*/
                //new ClawCloseCommand(claw),
                //new TrajectorySequenceCommand(drive, AutoConstantsRed.Away.STACK_TO_E_JUNCTION)
                /*.alongWith(new ConeReadyToScoreCommand(lift, claw))*/
                new ClawOpenCommand(claw));
    }
}
