package org.firstinspires.ftc.twenty403.command.autonomous.blue_home;

import android.util.Pair;

import com.technototes.library.command.ChoiceCommand;

import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

public class AutoBlueHomeParkingSelectionPreLoadCommand extends ChoiceCommand {
    public AutoBlueHomeParkingSelectionPreLoadCommand(VisionSubsystem visionSubsystem, DrivebaseSubsystem drivebaseSubsystem, ClawSubsystem clawSubsystem, LiftSubsystem liftSubsystem) {
        super(
                new Pair<>(visionSubsystem.visionPipeline::left, new AutoBlueHomePreLoadLeft(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::middle, new AutoBlueHomePreLoadMiddle(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(visionSubsystem.visionPipeline::right, new AutoBlueHomePreLoadRight(drivebaseSubsystem, clawSubsystem, liftSubsystem)));
    }

}
