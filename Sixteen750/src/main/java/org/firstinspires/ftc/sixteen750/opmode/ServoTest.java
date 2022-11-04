package org.firstinspires.ftc.sixteen750.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.sixteen750.swerve_util.AbsoluteAnalogEncoder;

@Config
@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class ServoTest extends CommandOpMode {
    ElapsedTime t;

    public static boolean alsoEncoder = true;

    CRServo leftFrontServo, leftRearServo, rightRearServo, rightFrontServo;
    AbsoluteAnalogEncoder leftFrontEncoder, leftRearEncoder, rightRearEncoder, rightFrontEncoder;
    boolean isLeftFrontPressed, isLeftRearPressed, isRightRearPressed, isRightFrontPressed = false;

    public static double servoPower = 0.3; // 0.1 is too little
    public static double servoStopPower = 0.0;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        leftFrontServo = hardwareMap.get(CRServo.class, "leftFrontServo");
        leftRearServo = hardwareMap.get(CRServo.class, "leftRearServo");
        rightRearServo = hardwareMap.get(CRServo.class, "rightRearServo");
        rightFrontServo = hardwareMap.get(CRServo.class, "rightFrontServo");

        if (alsoEncoder){
            leftFrontEncoder = new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, "leftFrontEncoder"));
            leftRearEncoder = new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, "leftRearEncoder"));
            rightRearEncoder = new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, "rightRearEncoder"));
            rightFrontEncoder = new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, "rightFrontEncoder"));
        }
    }

    @Override
    public void uponStart() {
        t = new ElapsedTime();
    }

    @Override
    public void runLoop(){
        double loopSeconds = t.seconds();

        if (this.gamepad1.dpad_left){
            leftFrontServo.setPower(servoPower);
            isLeftFrontPressed = true;
        }
        else {
            leftFrontServo.setPower(servoStopPower);
            isLeftFrontPressed = false;
        }
        if (this.gamepad1.dpad_down){
            leftRearServo.setPower(servoPower);
            isLeftRearPressed = true;
        }
        else {
            leftRearServo.setPower(servoStopPower);
            isLeftRearPressed = false;
        }
        if (this.gamepad1.dpad_up){
            rightRearServo.setPower(servoPower);
            isRightRearPressed = true;
        }
        else {
            rightRearServo.setPower(servoStopPower);
            isRightRearPressed = false;
        }
        if (this.gamepad1.dpad_right){
            rightFrontServo.setPower(servoPower);
            isRightFrontPressed = true;
        }
        else {
            rightFrontServo.setPower(servoStopPower);
            isRightFrontPressed = false;
        }

        t.reset();
        telemetry.addData("looptime",1/loopSeconds);
        telemetry.addData("LeftFront - Servo - Pressed", isLeftFrontPressed);
        telemetry.addData("LeftRear - Servo - Pressed", isLeftRearPressed);
        telemetry.addData("RightRear - Servo - Pressed", isRightRearPressed);
        telemetry.addData("RightFront - Servo - Pressed", isRightFrontPressed);

        if (alsoEncoder) {
            telemetry.addData("LeftFront - Position", leftFrontEncoder.getCurrentPosition());
            telemetry.addData("LeftRear - Position", leftRearEncoder.getCurrentPosition());
            telemetry.addData("RightRear - Position", rightRearEncoder.getCurrentPosition());
            telemetry.addData("RightFront - Position", rightFrontEncoder.getCurrentPosition());
        }

        telemetry.update();
    }
}
