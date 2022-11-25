package org.firstinspires.ftc.sixteen750.command.drive;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.MecanumDriveSubsystem;

public class ResetGyroCommand implements Command {
    public MecanumDriveSubsystem mecanumDriveSubsystem;

    public ResetGyroCommand(MecanumDriveSubsystem s) {
        mecanumDriveSubsystem = s;
    }

    @Override
    public void execute() {
        mecanumDriveSubsystem.setExternalHeading(0);
    }
}
