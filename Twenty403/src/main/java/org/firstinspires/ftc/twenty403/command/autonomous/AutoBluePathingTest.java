package org.firstinspires.ftc.twenty403.command.autonomous;

import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoBluePathingTest extends SequentialCommandGroup {
    public AutoBluePathingTest(DrivebaseSubsystem drive) {
        super(
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.START_TO_E_JUNCTION),
                // second cone
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.E_JUNCTION_TO_BETWEEN),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.BETWEEN_TO_STACK),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.STACK_TO_BETWEEN),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.BETWEEN_TO_E_JUNCTION),
                // third cone
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.START_TO_E_JUNCTION),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.E_JUNCTION_TO_BETWEEN),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.BETWEEN_TO_STACK),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.STACK_TO_BETWEEN),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.BETWEEN_TO_E_JUNCTION),
                // fourth cone
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.START_TO_E_JUNCTION),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.E_JUNCTION_TO_BETWEEN),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.BETWEEN_TO_STACK),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.STACK_TO_BETWEEN),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.BETWEEN_TO_E_JUNCTION),
                // fifth cone
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.START_TO_E_JUNCTION),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.E_JUNCTION_TO_BETWEEN),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.BETWEEN_TO_STACK),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.STACK_TO_BETWEEN),
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.BETWEEN_TO_E_JUNCTION),
                // park
                new TrajectorySequenceCommand(drive, AutoConstantsBlue.Home.E_JUNCTION_TO_RIGHT));
    }
}
