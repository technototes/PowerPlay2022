package org.firstinspires.ftc.sixteen750.command.drive;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.MecanumDriveSubsystem;

public class ApplySnailModeCommand implements Command {
    MecanumDriveSubsystem driveSubsystem;

    public ApplySnailModeCommand(MecanumDriveSubsystem driveSubsystem){
        this.driveSubsystem = driveSubsystem;
    }
    @Override
    public void execute() {
        driveSubsystem.applySnailMode();
    }
}
