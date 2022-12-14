package org.firstinspires.ftc.swerveteen750;

import java.util.ArrayList;
import java.util.List;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;
import org.firstinspires.ftc.swerveteen750.swerve_util.AbsoluteAnalogEncoder;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
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

    // T stands for TechnoLib's wrapped EncodedMotor<>
    public EncodedMotor<DcMotorEx> leftFrontMotorT;
    public EncodedMotor<DcMotorEx> leftRearMotorT;
    public EncodedMotor<DcMotorEx> rightFrontMotorT;
    public EncodedMotor<DcMotorEx> rightRearMotorT;
    // Q stands for Qualcomm's original DcMotorEx
    public DcMotorEx leftFrontMotorQ;
    public DcMotorEx leftRearMotorQ;
    public DcMotorEx rightFrontMotorQ;
    public DcMotorEx rightRearMotorQ;
    public CRServo leftFrontServo;
    public CRServo leftRearServo;
    public CRServo rightFrontServo;
    public CRServo rightRearServo;
    public AbsoluteAnalogEncoder leftFrontEncoder;
    public AbsoluteAnalogEncoder leftRearEncoder;
    public AbsoluteAnalogEncoder rightFrontEncoder;
    public AbsoluteAnalogEncoder rightRearEncoder;
    public IMU imu;

    public EncodedMotor<DcMotorEx> leftLiftMotor;
    public EncodedMotor<DcMotorEx> rightLiftMotor;

    public Servo clawServo;
    public Servo flipperServo;
    public Servo elbowServo;
    public DistanceSensor clawDistance;

    public Webcam camera;


    public ArrayList<String> hardwareWarnings = new ArrayList<>();
    public List<LynxModule> hubs;

    public Hardware(HardwareMap hwMap,
                    boolean enableSwerve,
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

            imu = new IMU(HardwareConstant.IMU).remapAxes(AxesOrder.YXZ, IMU.AxesSigns.NNN); // TODO: figure the axes order
        }
        else if (enableSwerve) {
            leftFrontMotorQ = hwMap.get(DcMotorEx.class, HardwareConstant.LF_MOTOR);
            leftRearMotorQ = hwMap.get(DcMotorEx.class, HardwareConstant.LR_MOTOR);
            rightFrontMotorQ = hwMap.get(DcMotorEx.class, HardwareConstant.RF_MOTOR);
            rightRearMotorQ = hwMap.get(DcMotorEx.class, HardwareConstant.RR_MOTOR);

            leftFrontServo = hwMap.crservo.get(HardwareConstant.LF_SERVO);
            leftRearServo = hwMap.crservo.get(HardwareConstant.LR_SERVO);
            rightFrontServo = hwMap.crservo.get(HardwareConstant.RF_SERVO);
            rightRearServo = hwMap.crservo.get(HardwareConstant.RR_SERVO);

            leftFrontEncoder = new AbsoluteAnalogEncoder(hwMap.get(AnalogInput.class, HardwareConstant.LF_ENCODER));
            leftRearEncoder = new AbsoluteAnalogEncoder(hwMap.get(AnalogInput.class, HardwareConstant.LR_ENCODER));
            rightFrontEncoder = new AbsoluteAnalogEncoder(hwMap.get(AnalogInput.class, HardwareConstant.RF_ENCODER));
            rightRearEncoder = new AbsoluteAnalogEncoder(hwMap.get(AnalogInput.class, HardwareConstant.RR_ENCODER));

            // Problem with TechNoLib
            // imu = new IMU(HardwareConstant.IMU).remapAxes(AxesOrder.YXZ, IMU.AxesSigns.NNN); // TODO: figure the axes order, its not the same as Mechanum
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
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.SWERVE_DRIVE_ENABLED : combo == Robot.SubsystemCombo.S_DRIVE_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.MECANUM_DRIVE_ENABLED : combo == Robot.SubsystemCombo.M_DRIVE_ONLY || combo == Robot.SubsystemCombo.VISION_M_DRIVE,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.LIFT_ENABLED : combo == Robot.SubsystemCombo.LIFT_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.ARM_ENABLED : combo == Robot.SubsystemCombo.ARM_CLAW_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.CLAW_ENABLED : combo == Robot.SubsystemCombo.ARM_CLAW_ONLY,
                combo == Robot.SubsystemCombo.DEFAULT ? Robot.RobotConstant.CAMERA_ENABLED : combo == Robot.SubsystemCombo.VISION_ONLY || combo == Robot.SubsystemCombo.VISION_M_DRIVE
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
