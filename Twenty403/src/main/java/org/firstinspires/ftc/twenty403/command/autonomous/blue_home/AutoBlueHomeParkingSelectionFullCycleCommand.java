package org.firstinspires.ftc.twenty403.command.autonomous.blue_home;

import android.util.Pair;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.helpers.ElapsedTimeHelper;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.VisionSubsystem;

import com.technototes.library.command.ChoiceCommand;

import java.util.function.DoubleSupplier;

public class AutoBlueHomeParkingSelectionFullCycleCommand extends ChoiceCommand {
    public AutoBlueHomeParkingSelectionFullCycleCommand(
            Robot robot, DoubleSupplier currOpModeRunTime
    ) {
        super(
                new Pair<>(
                        robot.visionSystem.visionPipeline::left,
                        new AutoBlueHomeFullCycle(robot, AutoConstantsBlue.Home.W_JUNCTION_TO_LEFT_PARK, currOpModeRunTime)),
                new Pair<>(
                        robot.visionSystem.visionPipeline::middle,
                        new AutoBlueHomeFullCycle(robot, AutoConstantsBlue.Home.W_JUNCTION_TO_MIDDLE_PARK, currOpModeRunTime)),
                new Pair<>(
                        robot.visionSystem.visionPipeline::right,
                        new AutoBlueHomeFullCycle(robot, AutoConstantsBlue.Home.W_JUNCTION_TO_RIGHT_PARK, currOpModeRunTime)));
    }
}
