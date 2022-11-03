package org.firstinspires.ftc.sixteen750.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.sixteen750.swerve_util.SwerveModule;

@TeleOp(group = "drive")
public class ServoTesting extends CommandOpMode {
    ElapsedTime t;

    SwerveModule leftFrontModule, leftRearModule, rightRearModule, rightFrontModule;
    boolean isLeftFrontPressed, isLeftRearPressed, isRightRearPressed, isRightFrontPressed = false;

    public static double servoPower = 0.1;
    public static double servoStopPower = 0.0;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        leftFrontModule = new SwerveModule(hardwareMap, "leftFrontMotor", "leftFrontServo", "leftFrontEncoder");
        leftRearModule = new SwerveModule(hardwareMap, "leftRearMotor", "leftRearServo", "leftRearEncoder");
        rightRearModule = new SwerveModule(hardwareMap, "rightRearMotor", "rightRearServo", "rightRearEncoder");
        rightFrontModule = new SwerveModule(hardwareMap, "rightFrontMotor", "rightFrontServo", "rightFrontEncoder");
    }

    @Override
    public void uponStart() {
        t = new ElapsedTime();
    }

    @Override
    public void runLoop(){
        double loopSeconds = t.seconds();

        if (this.gamepad1.dpad_left){
            leftFrontModule.setServoPower(servoPower);
            isLeftFrontPressed = true;
        }
        else {
            leftFrontModule.setServoPower(servoStopPower);
            isLeftFrontPressed = false;
        }
        if (this.gamepad1.dpad_down){
            leftRearModule.setServoPower(servoPower);
            isLeftRearPressed = true;
        }
        else {
            leftRearModule.setServoPower(servoStopPower);
            isLeftRearPressed = false;
        }
        if (this.gamepad1.dpad_up){
            rightRearModule.setServoPower(servoPower);
            isRightRearPressed = true;
        }
        else {
            rightRearModule.setServoPower( servoStopPower);
            isRightRearPressed = false;
        }
        if (this.gamepad1.dpad_right){
            rightFrontModule.setServoPower(servoPower);
            isRightFrontPressed = true;
        }
        else {
            rightFrontModule.setServoPower(servoStopPower);
            isRightFrontPressed = false;
        }

        t.reset();
        telemetry.addData("looptime",1/loopSeconds);
        telemetry.addData("LeftFront - Servo", isLeftFrontPressed);
        telemetry.addData("LeftRear - Servo", isLeftRearPressed);
        telemetry.addData("RightRear - Servo", isRightRearPressed);
        telemetry.addData("RightFront - Servo", isRightFrontPressed);
        telemetry.update();
    }
}
