package org.firstinspires.ftc.twenty403.command.autonomous.Left;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.technototes.library.command.ChoiceCommand;

public class AutoBlueAwayParkingSelectionPreLoadCommand extends ChoiceCommand {
    public AutoBlueAwayParkingSelectionPreLoadCommand(
            DrivebaseSubsystem drivebaseSubsystem,
            VisionSubsystem visionSubsystem,
            LiftSubsystem liftSubsystem,
            ClawSubsystem clawSubsystem) {
        super(
                new Pair<>(
                        visionSubsystem.visionPipeline::left,
                        new AutoBlueAwayPreLoadLeft(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(
                        visionSubsystem.visionPipeline::middle,
                        new AutoBlueAwayPreLoadMiddle(drivebaseSubsystem, clawSubsystem, liftSubsystem)),
                new Pair<>(
                        visionSubsystem.visionPipeline::right,
                        new AutoBlueAwayPreLoadRight(drivebaseSubsystem, clawSubsystem, liftSubsystem)));
    }
}
