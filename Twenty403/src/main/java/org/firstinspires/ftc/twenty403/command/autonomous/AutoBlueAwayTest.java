package org.firstinspires.ftc.twenty403.command.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;
import com.technototes.path.trajectorysequence.TrajectorySequence;

import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

public class AutoBlueAwayTest extends SequentialCommandGroup {
    public AutoBlueAwayTest(MecanumDrivebaseSubsystem drivebaseSubsystem, ClawSubsystem clawSubsystem, LiftSubsystem liftSubsystem){
        super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.START_TO_W_JUNCTION).alongWith(new LiftHighJunctionCommand(liftSubsystem)),
                new ClawOpenCommand(clawSubsystem),
                        CommandScheduler.getInstance()::terminateOpMode);

    }
}
