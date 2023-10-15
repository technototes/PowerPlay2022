package org.firstinspires.ftc.twenty403.command.drive;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.twenty403.Robot;

public class TileMoveCommand implements Command {

    Robot robot;
    TileMoving tile;

    public TileMoveCommand(Robot r, TileMoving tm) {
        robot = r;
        tile = tm;
    }

    @Override
    public void execute() {
        switch (tile) {
            case Up:
                // It's not clear if this will allow other subsystems to run.
                // If it doesn't, we'll need to expose capabilities in the drivebase subsystem

                robot.drivebaseSubsystem.requestTrajectoryMove(0, 24, 0);
                break;
            case Down:
                robot.drivebaseSubsystem.requestTrajectoryMove(0, -24, 0);
                break;
            case Left:
                robot.drivebaseSubsystem.requestTrajectoryMove(-24, 0, 0);
                break;
            case Right:
                robot.drivebaseSubsystem.requestTrajectoryMove(24, 0, 0);
                break;
        }
    }

    public enum TileMoving {
        Up,
        Down,
        Left,
        Right,
    }
}
