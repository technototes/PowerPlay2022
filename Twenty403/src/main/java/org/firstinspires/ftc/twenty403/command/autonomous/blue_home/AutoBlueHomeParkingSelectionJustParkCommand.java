package org.firstinspires.ftc.twenty403.command.autonomous.blue_home;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.technototes.library.command.ChoiceCommand;

public class AutoBlueHomeParkingSelectionJustParkCommand extends ChoiceCommand {
    public AutoBlueHomeParkingSelectionJustParkCommand(
            VisionSubsystem visionSubsystem, DrivebaseSubsystem drivebaseSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new AutoBlueHomeParkingLeft(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new AutoBlueHomeParkingRight(drivebaseSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new AutoBlueHomeParkingRight(drivebaseSubsystem)));
    }
}
