package org.firstinspires.ftc.forteaching.TechnoBot;

// This is a sample hardware class that keeps a reference to all the
// pieces of hardware on the robot

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.vision.hardware.Webcam;

public class Hardware {
    // Put all the names of the devices in here:
    // @Config Disable for the dashboard
    public static class DeviceNames {
        public static String FLEFT_MOTOR = "flMotor";
        public static String FRIGHT_MOTOR = "frMotor";
        public static String RLEFT_MOTOR = "rlMotor";
        public static String RRIGHT_MOTOR = "rrMotor";
        public static String IMU = "imu";

        public static String REV2M_DIST = "distance";
        public static String SERVO = "servo";
        public static String BUMP = "bump";
        public static String COLOR = "color";

        public static String CLAW = "claw";
        public static String SLIDER = "slider_motor";
        public static String CAMERA = "Webcam";

        public static String TEST_SERVO = "claw";
        public static String TEST_ENCODED_MOTOR = "slider_motor";
        public static String TEST_MOTOR = "motorL";
    }

    // We make this public so subsystems & whatnot can get them
    public EncodedMotor<DcMotorEx> frontLeftDriveMotor;
    public EncodedMotor<DcMotorEx> frontRightDriveMotor;
    public EncodedMotor<DcMotorEx> rearLeftDriveMotor;
    public EncodedMotor<DcMotorEx> rearRightDriveMotor;

    public IMU inertialMovementUnit;

    public Rev2MDistanceSensor distanceSensor;
    public Servo spinnerServo;
    public RevTouchSensor bumpSensor;
    public RevColorSensorV3 colorSensor;

    public Servo clawServo;
    public DcMotorEx sliderMotor;

    public Webcam camera;

    public Servo servoForTesting;
    public EncodedMotor<DcMotorEx> encodedMotorForTesting;
    public Motor<DcMotorEx> normalMotorForTesting;

    public Hardware(HardwareMap hwmap) {
        if (TheBot.Connected.DriveTrain) {
            frontLeftDriveMotor = new EncodedMotor<DcMotorEx>(DeviceNames.FLEFT_MOTOR);
            frontRightDriveMotor = new EncodedMotor<DcMotorEx>(DeviceNames.FRIGHT_MOTOR);
            rearLeftDriveMotor = new EncodedMotor<DcMotorEx>(DeviceNames.RLEFT_MOTOR);
            rearRightDriveMotor = new EncodedMotor<DcMotorEx>(DeviceNames.RRIGHT_MOTOR);
            inertialMovementUnit = new IMU(DeviceNames.IMU);
        }
        if (TheBot.Connected.Sensors) {
            distanceSensor = new Rev2MDistanceSensor(DeviceNames.REV2M_DIST);
            spinnerServo = new Servo(DeviceNames.SERVO);
            bumpSensor = hwmap.get(RevTouchSensor.class, DeviceNames.BUMP);
            colorSensor = hwmap.get(RevColorSensorV3.class, DeviceNames.COLOR);
        }
        if (TheBot.Connected.Claw) {
            clawServo = new Servo(DeviceNames.CLAW);
        }
        if (TheBot.Connected.Slider) {
            sliderMotor = hwmap.get(DcMotorEx.class, DeviceNames.SLIDER);
        }
        if (TheBot.Connected.Camera) {
            camera = new Webcam(DeviceNames.CAMERA);
        }
        if (TheBot.Connected.MovementTesters) {
            servoForTesting = new Servo(DeviceNames.TEST_SERVO);
            encodedMotorForTesting = new EncodedMotor<DcMotorEx>(DeviceNames.TEST_ENCODED_MOTOR);
            normalMotorForTesting = new Motor<DcMotorEx>(DeviceNames.TEST_MOTOR);
        }
    }
}
