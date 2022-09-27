package org.firstinspires.ftc.forteaching.TechnoBot;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Loggable;
import com.technototes.library.logger.Log;
import com.technototes.library.util.Color;

import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.MotorAsServoSubsystem;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.MovementTestingSubsystem;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.ClawSubsystem;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.LiftSubsystem;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.TankDriveSubsystem;
import org.firstinspires.ftc.forteaching.TechnoBot.Subsystems.VisionSubsystem;

// This is the "robot" class, which is for containing all the subsystems of the bot
public class TheBot implements Loggable {

    // This can be convenient when working with partially completed, or mid-reconfigured robots:
    @Config
    public static class Connected {
        public static boolean DriveTrain = false;
        public static boolean Sensors = false;
        public static boolean Camera = false;
        public static boolean MovementTesters = true;
        public static boolean Claw = false;
        public static boolean Slider = false;
    }

    // Add all our subsystems in here:
    public TankDriveSubsystem tankDriveBase;
    // public SensingSubsystem sensing;
    public ClawSubsystem clawSubsystem;
    public LiftSubsystem liftSubsystem;
    @Log(name = "Vision", entryColor = Color.PINK)
    public VisionSubsystem visionSystem;

    public MovementTestingSubsystem movementTestingSubsystem;
    public MotorAsServoSubsystem encodedMotorSubsystem;

    public TheBot(Hardware hw) {
        if (Connected.DriveTrain) {
            tankDriveBase = new TankDriveSubsystem(hw.leftDriveMotor, hw.rightDriveMotor, hw.inertialMovementUnit);
        }
        if (Connected.Sensors) {
            // sensing = new SensingSubsystem(hw.colorSensor, hw.bumpSensor, hw.distanceSensor);
        }
        if (Connected.Claw) {
            clawSubsystem = new ClawSubsystem(hw.clawServo, null, null);
        }
        if (Connected.Slider) {
            liftSubsystem = new LiftSubsystem(hw.sliderMotor);
        }
        if (Connected.Camera) {
            visionSystem = new VisionSubsystem(hw.camera);
        }
        if (Connected.MovementTesters) {
            movementTestingSubsystem = new MovementTestingSubsystem(hw.normalMotorForTesting, hw.servoForTesting);
            encodedMotorSubsystem = new MotorAsServoSubsystem(hw.encodedMotorForTesting);
        }
    }
}
