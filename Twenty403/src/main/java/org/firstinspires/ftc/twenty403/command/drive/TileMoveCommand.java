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
        // addRequirements(r);
    }

    @Override
    public void execute() {
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstants.Right.TELESTART.toPose());
        switch (tile) {
            case Up:
                // It's not clear if this will allow other subsystems to run.
                // If it doesn't, we'll need to expose capabilities in the drivebase subsystem
                robot.drivebaseSubsystem.followTrajectory(
                    robot.drivebaseSubsystem
                        .trajectoryBuilder(AutoConstants.Right.TELESTART.toPose())
                        .lineTo(AutoConstants.Right.FORWARD_MOVE.toVec())
                        .build()
                );
                break;
            case Down:
                break;
            case Left:
                break;
            case Right:
                break;
        }
    }
}
