package org.firstinspires.ftc.forteaching.TechnoBot;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;
import com.technototes.library.logger.Log;
import com.technototes.library.util.Color;

import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.TankDriveSubsystem;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.VisionSubsystem;

// This is the "robot" class, which is for containing all the subsystems of the bot
public class TheBot implements Loggable {

    // This can be convenient when working with partially completed, or mid-reconfigured robots:
    @Config
    public static class Connected {
        public static boolean DriveTrain = true;
        public static boolean Sensors = false;
        public static boolean Camera = true;
    }

    // Add all our subsystems in here:
    public TankDriveSubsystem tankDriveBase;
    // public SensingSubsystem sensing;
    @Log(name = "Vision", entryColor = Color.PINK)
    public VisionSubsystem visionSystem;

    public TheBot(Hardware hw) {
        if (Connected.DriveTrain) {
            tankDriveBase = new TankDriveSubsystem(hw.leftDriveMotor, hw.rightDriveMotor, hw.inertialMovementUnit);
        }
        if (Connected.Sensors) {
            // sensing = new SensingSubsystem(hw.colorSensor, hw.bumpSensor, hw.distanceSensor);
        }
        if (Connected.Camera) {
            visionSystem = new VisionSubsystem(hw.camera);
        }
    }
}
