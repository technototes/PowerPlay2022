package org.firstinspires.ftc.twenty403.command.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.Command;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;

public class TileMoveCommand implements Command {

    Robot robot;
    TileMoving tile;

    public TileMoveCommand(Robot r, TileMoving tm) {
        robot = r;
        tile = tm;
    }

    @Override
    public void execute() {
        // robot.drivebaseSubsystem.setPoseEstimate(AutoConstants.Right.TELESTART.toPose());
        switch (tile) {
            case Up:
                // It's not clear if this will allow other subsystems to run.
                // If it doesn't, we'll need to expose capabilities in the drivebase subsystem

                robot.drivebaseSubsystem.requestTrajectoryMove(0, 24);
                break;
            case Down:
                robot.drivebaseSubsystem.requestTrajectoryMove(0, -24);
                break;
            case Left:
                robot.drivebaseSubsystem.requestTrajectoryMove(-24, 0);
                break;
            case Right:
                robot.drivebaseSubsystem.requestTrajectoryMove(24, 0);
                break;
        }
    }
}
