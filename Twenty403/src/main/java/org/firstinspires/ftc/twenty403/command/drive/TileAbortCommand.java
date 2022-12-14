package org.firstinspires.ftc.twenty403.command.drive;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.twenty403.Robot;

public class TileAbortCommand implements Command {
    Robot robot;
    public TileAbortCommand (Robot r){
        robot = r;
    }
    @Override
    public void execute() {
        robot.drivebaseSubsystem.requestCancelled();
    }
}
