package org.firstinspires.ftc.sixteen750;

import static org.firstinspires.ftc.sixteen750.Robot.RobotConstant;

import java.util.ArrayList;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
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
        public static String LF_SERVO = "leftFrontServo";
        public static String LR_SERVO = "leftRearServo";
        public static String RF_SERVO = "rightFrontServo";
        public static String RR_SERVO = "rightRearServo";
        public static String LF_ENCODER = "leftFrontEncoder";
        public static String LR_ENCODER = "leftRearEncoder";
        public static String RF_ENCODER = "rightFrontEncoder";
        public static String RR_ENCODER = "rightRearEncoder";
        public static String IMU = "imu";

        public static String CLAW_SERVO = "clawServo";
        public static String FLIPPER_SERVO = "flipperServo";
        public static String ELBOW_SERVO = "elbowServo";
        public static String CLAW_SENSOR = "clawSensor";
        public static String LEFT_LIFT_MOTOR = "leftLiftMotor";
        public static String RIGHT_LIFT_MOTOR = "rightLiftMotor";

        public static String CAMERA = "Webcam";

    }

    public EncodedMotor<DcMotorEx> leftFrontMotor;
    public EncodedMotor<DcMotorEx> leftRearMotor;
    public EncodedMotor<DcMotorEx> rightFrontMotor;
    public EncodedMotor<DcMotorEx> rightRearMotor;
    public IMU imu;

    public EncodedMotor<DcMotorEx> leftLiftMotor;
    public EncodedMotor<DcMotorEx> rightLiftMotor;

    public Servo clawServo;
    public Servo flipperServo;
    public Servo elbowServo;
    public DistanceSensor clawDistance;

    public Webcam camera;


    public ArrayList<String> hardwareWarnings = new ArrayList<>();

    public Hardware(HardwareMap hwMap, boolean enableMecanum, boolean enableLift, boolean enableArm, boolean enableClaw, boolean enableCamera) {
        if (enableMecanum) {
            leftFrontMotor = new EncodedMotor<>(HardwareConstant.LF_MOTOR);
            leftRearMotor = new EncodedMotor<>(HardwareConstant.LR_MOTOR);
            rightFrontMotor = new EncodedMotor<>(HardwareConstant.RF_MOTOR);
            rightRearMotor = new EncodedMotor<>(HardwareConstant.RR_MOTOR);

            imu = new IMU(HardwareConstant.IMU).remapAxes(AxesOrder.YXZ, IMU.AxesSigns.NNN); // TODO: figure the axes order
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
            elbowServo = new Servo(HardwareConstant.ELBOW_SERVO).invert();
        }

        if (enableClaw) {
            clawServo = new Servo(HardwareConstant.CLAW_SERVO).invert();
            // clawDistance = hwMap.get(DistanceSensor.class, HardwareConstant.CLAW_SENSOR); // not installed
        }
        if (enableCamera){
            camera = new Webcam(HardwareConstant.CAMERA);
        }
    }

    public Hardware(HardwareMap hwMap, Robot.SubsystemCombo type) {
        this(hwMap,
                type == Robot.SubsystemCombo.DEFAULT ? RobotConstant.MECANUM_DRIVE_ENABLED : type == Robot.SubsystemCombo.DRIVE_ONLY || type == Robot.SubsystemCombo.VISION_DRIVE,
                type == Robot.SubsystemCombo.DEFAULT ? RobotConstant.LIFT_ENABLED : type == Robot.SubsystemCombo.LIFT_ONLY,
                type == Robot.SubsystemCombo.DEFAULT ? RobotConstant.ARM_ENABLED : type == Robot.SubsystemCombo.ARM_CLAW_ONLY,
                type == Robot.SubsystemCombo.DEFAULT ? RobotConstant.CLAW_ENABLED : type == Robot.SubsystemCombo.ARM_CLAW_ONLY,
                type == Robot.SubsystemCombo.DEFAULT ? RobotConstant.CAMERA_ENABLED : type == Robot.SubsystemCombo.VISION_ONLY || type == Robot.SubsystemCombo.VISION_DRIVE
        );
    }
}
