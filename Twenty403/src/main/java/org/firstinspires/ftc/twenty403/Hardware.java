package org.firstinspires.ftc.twenty403;

import static org.firstinspires.ftc.twenty403.Robot.RobotConstant;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;

public class Hardware {
    @Config
    public static class HardwareConstant {
        public static String FL_MOTOR = "motorFL";
        public static String FR_MOTOR = "motorFR";
        public static String RL_MOTOR = "motorRL"; // bad
        public static String RR_MOTOR = "motorRR";
        public static String IMU = "imu";

        public static String CLAW_SERVO = "claw";
        public static String FLIPPER_SERVO = "flipper";
        public static String CLAW_SENSOR = "claw_sensor";
        public static String LIFT_LEFT_MOTOR = "LLIFT";
        public static String LIFT_RIGHT_MOTOR = "RLIFT";
    }

    public EncodedMotor<DcMotorEx> flDriveMotor;
    public EncodedMotor<DcMotorEx> frDriveMotor;
    public EncodedMotor<DcMotorEx> rlDriveMotor;
    public EncodedMotor<DcMotorEx> rrDriveMotor;
    public IMU imu;

    public Hardware() {
        if (RobotConstant.DRIVE_CONNECTED) {
            flDriveMotor = new EncodedMotor<>(HardwareConstant.FL_MOTOR);
            frDriveMotor = new EncodedMotor<>(HardwareConstant.FR_MOTOR);
            rlDriveMotor = new EncodedMotor<>(HardwareConstant.RL_MOTOR);
            rrDriveMotor = new EncodedMotor<>(HardwareConstant.RR_MOTOR);
            imu = new IMU(HardwareConstant.IMU).remapAxes(AxesOrder.YXZ, IMU.AxesSigns.NNN);
        }
    }
}
