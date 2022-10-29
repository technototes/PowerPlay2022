package org.firstinspires.ftc.twenty403.command.autonomous.blue_home;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

public class AutoBlueHomeParkingSelectCommand extends ChoiceCommand {
    public AutoBlueHomeParkingSelectCommand(DrivebaseSubsystem drivebaseSubsystem, VisionSubsystem visionSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new AutoBlueHomeLeft(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new AutoBlueHomeRight(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new AutoBlueHomeRight(drivebaseSubsystem))
        );
    }
}
