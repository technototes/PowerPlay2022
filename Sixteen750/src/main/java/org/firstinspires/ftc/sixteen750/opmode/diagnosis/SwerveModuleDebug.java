package org.firstinspires.ftc.sixteen750.opmode.diagnosis;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.swerve_util.SwerveModule;

@TeleOp(group = "Test-Hardware")
public class SwerveModuleDebug extends CommandOpMode {
    Hardware hardware;
    SwerveModule leftFrontModule;
    SwerveModule leftRearModule;
    SwerveModule rightRearModule;
    SwerveModule rightFrontModule;

    public double leftFrontModuleOrientationRadians = 0;
    public double leftRearModuleOrientationRadians = 0;
    public double rightRearModuleOrientationRadians = 0;
    public double rightFrontModuleOrientationRadians = 0;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.S_DRIVE_ONLY);
        leftFrontModule = new SwerveModule(hardware.leftFrontMotorQ, hardware.leftFrontServo, hardware.leftFrontEncoder);
        leftRearModule = new SwerveModule(hardware.leftRearMotorQ, hardware.leftRearServo, hardware.leftRearEncoder);
        rightRearModule = new SwerveModule(hardware.rightRearMotorQ, hardware.rightRearServo, hardware.rightRearEncoder);
        rightFrontModule = new SwerveModule(hardware.rightFrontMotorQ, hardware.rightFrontServo, hardware.rightFrontEncoder);
    }

    @Override
    public void uponStart() {

    }

    @Override
    public void runLoop() {
        if (gamepad1.dpad_up) {
            leftFrontModuleOrientationRadians = incrementOrientationRadians(leftFrontModuleOrientationRadians);
        }
        else if (gamepad1.dpad_right) {
            leftRearModuleOrientationRadians = incrementOrientationRadians(leftRearModuleOrientationRadians);
        }
        else if (gamepad1.dpad_down) {
            rightRearModuleOrientationRadians = incrementOrientationRadians(rightRearModuleOrientationRadians);
        }
        else if (gamepad1.dpad_left) {
            rightFrontModuleOrientationRadians = incrementOrientationRadians(rightFrontModuleOrientationRadians);
        }

        leftFrontModule.setTargetRotation(leftFrontModuleOrientationRadians);
        leftRearModule.setTargetRotation(leftRearModuleOrientationRadians);
        rightRearModule.setTargetRotation(rightRearModuleOrientationRadians);
        rightFrontModule.setTargetRotation(rightFrontModuleOrientationRadians);

        telemetry.addData("LF - Target", leftFrontModule.getTargetRotation());
        telemetry.addData("LF - Current", leftFrontModule.getModuleRotation());

        telemetry.addData("LR - Target", leftRearModule.getTargetRotation());
        telemetry.addData("LR - Current", leftRearModule.getModuleRotation());

        telemetry.addData("RF - Target", rightFrontModule.getTargetRotation());
        telemetry.addData("RF - Current", rightFrontModule.getModuleRotation());

        telemetry.addData("RR - Target", rightRearModule.getTargetRotation());
        telemetry.addData("RR - Current", rightRearModule.getModuleRotation());
    }

    public double incrementOrientationRadians(double o){
        if (o > 2 * Math.PI){
            o = 0;
        } else {
            o += 0.001;
        }
        return o;
    }
}
