package org.firstinspires.ftc.swerveteen750.command.drive;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.MecanumDriveSubsystem;

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
