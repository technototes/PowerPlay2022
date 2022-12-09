package org.firstinspires.ftc.forteaching.TechnoBot.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

public class MovementTestingSubsystem implements Subsystem, Loggable {

    // @Config - Don't want this on the dashboard
    public static class TestingValues {

        public static double SERVO_DELTA = 0.09;
        public static double SERVO_HIGH = 0.72;
        public static double SERVO_LOW = 0.0;
        public static double SERVO_MID = (SERVO_HIGH + SERVO_LOW) / 2.0;
        public static double ENCODED_MOTOR_DELTA = 125; // 125 ticks per inch, actually :)
        public static double MOTOR_DELTA = 0.01;
        public static double SERVO_PWM_LOW = 100;
        public static double SERVO_PWM_HIGH = 3500;
    }

    public Motor<DcMotorEx> normalMotor;
    public Servo servo;

    public MovementTestingSubsystem(Motor<DcMotorEx> normalM, Servo srvo) {
        servo = srvo;
        servo.scalePWM(TestingValues.SERVO_PWM_LOW, TestingValues.SERVO_PWM_HIGH);
        normalMotor = normalM;
    }

    private void setServo(double val) {
        servo.setPosition(val);
        curServo = val;
    }

    public void increaseServoMotor() {
        setServo(
            Range.clip(
                servo.getPosition() + TestingValues.SERVO_DELTA,
                TestingValues.SERVO_LOW,
                TestingValues.SERVO_HIGH
            )
        );
    }

    public void decreaseServoMotor() {
        setServo(
            Range.clip(
                servo.getPosition() - TestingValues.SERVO_DELTA,
                TestingValues.SERVO_LOW,
                TestingValues.SERVO_HIGH
            )
        );
    }

    public void neutralServoMotor() {
        setServo(TestingValues.SERVO_MID);
    }

    private void setMotor(double val) {
        normalMotor.coast();
        normalMotor.setSpeed(val);
        curMotor = val;
    }

    public void increaseMotorSpeed() {
        setMotor(Range.clip(normalMotor.getSpeed() + TestingValues.MOTOR_DELTA, -1, 1));
    }

    public void decreaseMotorSpeed() {
        setMotor(Range.clip(normalMotor.getSpeed() - TestingValues.MOTOR_DELTA, -1, 1));
    }

    public void neutralMotorSpeed() {
        setMotor(0);
    }

    public void brakeMotor() {
        normalMotor.brake();
        normalMotor.setSpeed(0);
        curMotor = 0;
    }

    @Log.Number(name = "Servo")
    public volatile double curServo = 0.0;

    @Log.Number(name = "Motor")
    public volatile double curMotor = 0.0;
}
