package org.firstinspires.ftc.sixteen750;

import static org.firstinspires.ftc.sixteen750.Robot.RobotConstant;

import java.util.ArrayList;
import java.util.List;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.vision.hardware.Webcam;

public class Hardware {
    @Config
    public static class HardwareConstant {
        public static String LF_MOTOR = "leftFrontMotor";
        public static String LR_MOTOR = "leftRearMotor";
        public static String RF_MOTOR = "rightFrontMotor";
        public static String RR_MOTOR = "rightRearMotor";
        public static String IMU = "imu";

        public static String CLAW_SERVO = "clawServo";
        public static String FLIPPER_SERVO = "flipperServo";
        public static String ELBOW_SERVO = "elbowServo";
        public static String LEFT_LIFT_MOTOR = "leftLiftMotor";

        public static String CAMERA = "Webcam";

    }

    // T stands for TechnoLib's wrapped EncodedMotor<>
    public EncodedMotor<DcMotorEx> leftFrontMotorT;
    public EncodedMotor<DcMotorEx> leftRearMotorT;
    public EncodedMotor<DcMotorEx> rightFrontMotorT;
    public EncodedMotor<DcMotorEx> rightRearMotorT;
    public IMU imu;

    public EncodedMotor<DcMotorEx> leftLiftMotor;

    public Servo clawServo;
    public Servo flipperServo;
    public Servo elbowServo;

    public Webcam camera;

    public ArrayList<String> hardwareWarnings = new ArrayList<>();
    public List<LynxModule> hubs;

    public Hardware(HardwareMap hwMap,
                    boolean enableMecanum,
                    boolean enableLift,
                    boolean enableArm,
                    boolean enableClaw,
                    boolean enableCamera
    ) {
        this.hubs = hwMap.getAll(LynxModule.class);

        if (enableMecanum) {
            leftFrontMotorT = new EncodedMotor<>(HardwareConstant.LF_MOTOR);
            leftRearMotorT = new EncodedMotor<>(HardwareConstant.LR_MOTOR);
            rightFrontMotorT = new EncodedMotor<>(HardwareConstant.RF_MOTOR);
            rightRearMotorT = new EncodedMotor<>(HardwareConstant.RR_MOTOR);

            imu = new IMU(HardwareConstant.IMU, RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP);
        }

        if (enableLift) {
            try {
                leftLiftMotor = new EncodedMotor<>(HardwareConstant.LEFT_LIFT_MOTOR);
            } catch (Exception e) {
                leftLiftMotor = null;
                hardwareWarnings.add("Left Lift Motor not found");
            }
        }

        if (enableArm) {
            flipperServo = new Servo(HardwareConstant.FLIPPER_SERVO);
            elbowServo = new Servo(HardwareConstant.ELBOW_SERVO);
        }

        if (enableClaw) {
            clawServo = new Servo(HardwareConstant.CLAW_SERVO).invert();
            // clawDistance = hwMap.get(DistanceSensor.class, HardwareConstant.CLAW_SENSOR); // not installed
        }
        if (enableCamera){
            camera = new Webcam(HardwareConstant.CAMERA);
        }
    }

    public Hardware(HardwareMap hwMap, Robot.SubsystemCombo combo) {
        this(
                hwMap,
                combo == Robot.SubsystemCombo.DEFAULT ? RobotConstant.MECANUM_DRIVE_ENABLED : combo == Robot.SubsystemCombo.M_DRIVE_ONLY || combo == Robot.SubsystemCombo.VISION_M_DRIVE,
                combo == Robot.SubsystemCombo.DEFAULT ? RobotConstant.LIFT_ENABLED : combo == Robot.SubsystemCombo.LIFT_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? RobotConstant.ARM_ENABLED : combo == Robot.SubsystemCombo.ARM_CLAW_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? RobotConstant.CLAW_ENABLED : combo == Robot.SubsystemCombo.ARM_CLAW_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? RobotConstant.CAMERA_ENABLED : combo == Robot.SubsystemCombo.VISION_ONLY || combo == Robot.SubsystemCombo.VISION_M_DRIVE
        );
    }

    public double getVoltage() {
        double totalVoltage = 0;
        double lynxModuleCount = 0;
        for (LynxModule lynxModule : hubs) {
            lynxModuleCount += 1;
            totalVoltage += lynxModule.getInputVoltage(VoltageUnit.VOLTS);
        }
        return totalVoltage / lynxModuleCount;
    }
}
