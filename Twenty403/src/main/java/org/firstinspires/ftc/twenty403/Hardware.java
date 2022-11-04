package org.firstinspires.ftc.twenty403;

import static org.firstinspires.ftc.twenty403.Robot.RobotConstant;

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
        public static String FL_MOTOR = "FLMOTOR";
        public static String FR_MOTOR = "FRMOTOR";
        public static String RL_MOTOR = "RLMOTOR"; // bad
        public static String RR_MOTOR = "RRMOTOR";
        public static String IMU = "imu";

        public static String CAMERA = "Webcam";

        public static String CLAW_SERVO = "claw";
        //public static String CLAW_SENSOR = "claw_sensor";
        public static String LIFT_LEFT_MOTOR = "LLIFT";
        public static String LIFT_RIGHT_MOTOR = "RLIFT";
    }

    public EncodedMotor<DcMotorEx> flDriveMotor;
    public EncodedMotor<DcMotorEx> frDriveMotor;
    public EncodedMotor<DcMotorEx> rlDriveMotor;
    public EncodedMotor<DcMotorEx> rrDriveMotor;
    public IMU imu;

    public EncodedMotor<DcMotorEx> LiftLeftMotor;
    public EncodedMotor<DcMotorEx> LiftRightMotor;
    public Servo claw;
    public Servo flipper;
    public DistanceSensor clawDistance;

    public Webcam camera;

    public Hardware(HardwareMap hwmap) {
        if (RobotConstant.DRIVE_CONNECTED) {
            flDriveMotor = new EncodedMotor<>(HardwareConstant.FL_MOTOR);
            frDriveMotor = new EncodedMotor<>(HardwareConstant.FR_MOTOR);
            rlDriveMotor = new EncodedMotor<>(HardwareConstant.RL_MOTOR);
            rrDriveMotor = new EncodedMotor<>(HardwareConstant.RR_MOTOR);
            imu = new IMU(HardwareConstant.IMU).remapAxes(AxesOrder.YXZ, IMU.AxesSigns.NNN);
        }
        if (RobotConstant.CLAW_CONNECTED) {
            claw = new Servo(HardwareConstant.CLAW_SERVO);
            //clawDistance = hwmap.get(DistanceSensor.class, HardwareConstant.CLAW_SENSOR);
        }
        if (RobotConstant.LIFT_CONNECTED) {
            LiftLeftMotor = new EncodedMotor<>(HardwareConstant.LIFT_LEFT_MOTOR);
            LiftRightMotor = new EncodedMotor<>(HardwareConstant.LIFT_RIGHT_MOTOR);
        }
        if (RobotConstant.CAMERA_CONNECTED) {
            camera = new Webcam(HardwareConstant.CAMERA);
        }
    }
}
