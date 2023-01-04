package org.firstinspires.ftc.swerveteen750.opmode.diagnosis;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.technototes.library.structure.CommandOpMode;

@Config
@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class HandServoTest extends CommandOpMode {
    Servo handServo;

    public static double HAND_FORWARD = 0.0;
    public static double HAND_RIGHTWARD = 0.5;
    public static double HAND_BACKWARD = 1.0;

    @Override
    public void uponInit() {
        handServo = hardwareMap.get(Servo.class, "handServo");
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void runLoop() {
        if (gamepad1.dpad_up){
            handServo.setPosition(HAND_FORWARD);
        } else if (gamepad1.dpad_right){
            handServo.setPosition(HAND_RIGHTWARD);
        } else if (gamepad1.dpad_down){
            handServo.setPosition(HAND_BACKWARD);
        }

        telemetry.addData("Hand Servo Position", handServo.getPosition());
        telemetry.update();
    }
}
