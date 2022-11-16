package org.firstinspires.ftc.sixteen750;

import static org.firstinspires.ftc.sixteen750.Robot.RobotConstant;

import org.firstinspires.ftc.sixteen750.swerve_util.AbsoluteAnalogEncoder;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.servo.Servo;

import java.util.ArrayList;

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
        public static String LIFT_LEFT_MOTOR = "leftLiftMotor";
        public static String LIFT_RIGHT_MOTOR = "rightLiftMotor";
    }

    public EncodedMotor<DcMotorEx> leftFrontMotor;
    public EncodedMotor<DcMotorEx> leftRearMotor;
    public EncodedMotor<DcMotorEx> rightFrontMotor;
    public EncodedMotor<DcMotorEx> rightRearMotor;
    public CRServo leftFrontServo;
    public CRServo leftRearServo;
    public CRServo rightFrontServo;
    public CRServo rightRearServo;
    public AbsoluteAnalogEncoder leftFrontEncoder;
    public AbsoluteAnalogEncoder leftRearEncoder;
    public AbsoluteAnalogEncoder rightFrontEncoder;
    public AbsoluteAnalogEncoder rightRearEncoder;
    public IMU imu;

    public EncodedMotor<DcMotorEx> liftLeftMotor;
    public EncodedMotor<DcMotorEx> liftRightMotor;

    public Servo clawServo;
    public Servo flipperServo;
    public Servo elbowServo;
    public DistanceSensor clawDistance;

    public ArrayList<String> hardwareWarnings = new ArrayList<>();

    public Hardware(HardwareMap hwMap) {
        if (RobotConstant.SWERVE_DRIVE_ENABLED) {
            leftFrontMotor = new EncodedMotor<>(HardwareConstant.LF_MOTOR);
            leftRearMotor = new EncodedMotor<>(HardwareConstant.LR_MOTOR);
            rightFrontMotor = new EncodedMotor<>(HardwareConstant.RF_MOTOR);
            rightRearMotor = new EncodedMotor<>(HardwareConstant.RR_MOTOR);

            leftFrontServo = hwMap.crservo.get(HardwareConstant.LF_SERVO);
            leftRearServo = hwMap.crservo.get(HardwareConstant.LR_SERVO);
            rightFrontServo = hwMap.crservo.get(HardwareConstant.RF_SERVO);
            rightRearServo = hwMap.crservo.get(HardwareConstant.RR_SERVO);

            leftFrontEncoder = new AbsoluteAnalogEncoder(hwMap.get(AnalogInput.class, HardwareConstant.LF_ENCODER));
            leftRearEncoder = new AbsoluteAnalogEncoder(hwMap.get(AnalogInput.class, HardwareConstant.LR_ENCODER));
            rightFrontEncoder = new AbsoluteAnalogEncoder(hwMap.get(AnalogInput.class, HardwareConstant.RF_ENCODER));
            rightRearEncoder = new AbsoluteAnalogEncoder(hwMap.get(AnalogInput.class, HardwareConstant.RR_ENCODER));

            //          imu = new IMU(HardwareConstant.IMU).remapAxes(AxesOrder.YXZ, IMU.AxesSigns.NNN); // TODO: figure
            // the  axes order
            imu = new IMU(HardwareConstant.IMU);
        }
        if (RobotConstant.CLAW_ENABLED) {
            clawServo = new Servo(HardwareConstant.CLAW_SERVO).invert();
            flipperServo = new Servo(HardwareConstant.FLIPPER_SERVO);
            elbowServo = new Servo(HardwareConstant.ELBOW_SERVO).invert();
            //          clawDistance = hwMap.get(DistanceSensor.class, HardwareConstant.CLAW_SENSOR); // not installed
        }
        if (RobotConstant.LIFT_ENABLED) {
            try {
                liftLeftMotor = new EncodedMotor<>(HardwareConstant.LIFT_LEFT_MOTOR);
                liftRightMotor = new EncodedMotor<>(HardwareConstant.LIFT_RIGHT_MOTOR);
            } catch (Exception e) {
                hardwareWarnings.add("At lease one lift motors not found");
            }
        }
    }
}
