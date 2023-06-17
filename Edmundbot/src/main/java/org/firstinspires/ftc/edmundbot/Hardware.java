package org.firstinspires.ftc.edmundbot;

import static org.firstinspires.ftc.edmundbot.Robot.RobotConstant;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.ColorDistanceSensor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.vision.hardware.Webcam;
import java.util.List;

import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;

public class Hardware {

    @Config
    public static class HardwareConstant {

        public static String FL_MOTOR = "FLMOTOR";
        public static String FR_MOTOR = "FRMOTOR";
        public static String RL_MOTOR = "RLMOTOR"; // bad
        public static String RR_MOTOR = "RRMOTOR";
        public static String IMU = "imu";

        public static String CAMERA = "Webcam";

        public static String LEFT_SHOOTER_MOTOR = "leftShooterMotor";
        public static String RIGHT_SHOOTER_MOTOR = "rightShooterMotor";

        public static String DISTANCE_SENSOR_LEFT = "LeftDis";
        public static String DISTANCE_SENSOR_RIGHT = "RightDis";
        public static String COLOR_SENSOR_LEFT = "ColorLeft";
        public static String COLOR_SENSOR_CENTER = "ColorCenter";
        public static String COLOR_SENSOR_RIGHT = "ColorRight";
    }

    public List<LynxModule> hubs;

    public EncodedMotor<DcMotorEx> flDriveMotor;
    public EncodedMotor<DcMotorEx> frDriveMotor;
    public EncodedMotor<DcMotorEx> rlDriveMotor;
    public EncodedMotor<DcMotorEx> rrDriveMotor;
    public IMU imu;

    public EncodedMotor<DcMotorEx> leftShooterMotor;
    public EncodedMotor<DcMotorEx> rightShooterMotor;

    public Rev2MDistanceSensor leftdis;
    public Rev2MDistanceSensor rightdis;
    public ColorDistanceSensor colorleft;
    public ColorDistanceSensor colorright;
    public ColorDistanceSensor colorcenter;

    public Webcam camera;

    public Hardware(HardwareMap hwmap) {
        hubs = hwmap.getAll(LynxModule.class);
        if (RobotConstant.DRIVE_CONNECTED) {
            flDriveMotor = new EncodedMotor<>(HardwareConstant.FL_MOTOR);
            frDriveMotor = new EncodedMotor<>(HardwareConstant.FR_MOTOR);
            rlDriveMotor = new EncodedMotor<>(HardwareConstant.RL_MOTOR);
            rrDriveMotor = new EncodedMotor<>(HardwareConstant.RR_MOTOR);
            imu = new IMU(HardwareConstant.IMU, LogoFacingDirection.LEFT, UsbFacingDirection.UP);
        }

        if (RobotConstant.CAMERA_CONNECTED) {
            camera = new Webcam(HardwareConstant.CAMERA);
        }
        if (RobotConstant.ODO_SENSORS_CONNECTED) {
            leftdis = new Rev2MDistanceSensor(HardwareConstant.DISTANCE_SENSOR_LEFT);
            rightdis = new Rev2MDistanceSensor(HardwareConstant.DISTANCE_SENSOR_RIGHT);
            colorleft = new ColorDistanceSensor(HardwareConstant.COLOR_SENSOR_LEFT);
            colorright = new ColorDistanceSensor(HardwareConstant.COLOR_SENSOR_RIGHT);
            colorcenter = new ColorDistanceSensor(HardwareConstant.COLOR_SENSOR_CENTER);
        }

        if (RobotConstant.SHOOTER_CONNECTED) {
            leftShooterMotor = new EncodedMotor<>(HardwareConstant.LEFT_SHOOTER_MOTOR);
            rightShooterMotor = new EncodedMotor<>(HardwareConstant.RIGHT_SHOOTER_MOTOR);
        }
    }

    public double voltage() {
        double volt = 0;
        double count = 0;
        for (LynxModule lm : hubs) {
            count += 1;
            volt += lm.getInputVoltage(VoltageUnit.VOLTS);
        }
        return volt / count;
    }
}
