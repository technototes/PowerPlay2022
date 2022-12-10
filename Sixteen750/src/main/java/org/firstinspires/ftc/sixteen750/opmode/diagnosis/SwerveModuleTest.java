package org.firstinspires.ftc.sixteen750.opmode.diagnosis;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.swerve_util.LeftFrontSwerveModule;
import org.firstinspires.ftc.sixteen750.swerve_util.LeftRearSwerveModule;
import org.firstinspires.ftc.sixteen750.swerve_util.RightFrontSwerveModule;
import org.firstinspires.ftc.sixteen750.swerve_util.RightRearSwerveModule;
import org.firstinspires.ftc.sixteen750.swerve_util.SwerveModule;

@TeleOp(group = "Test-Hardware")
public class SwerveModuleTest extends CommandOpMode {
    Hardware hardware;
    LeftFrontSwerveModule leftFrontModule;
    LeftRearSwerveModule leftRearModule;
    RightFrontSwerveModule rightFrontModule;
    RightRearSwerveModule rightRearModule;


    public double leftFrontModuleTargetOrientationRadians = 0;
    public double leftRearTargetModuleOrientationRadians = 0;
    public double rightRearModuleTargetOrientationRadians = 0;
    public double rightFrontModuleTargetOrientationRadians = 0;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.S_DRIVE_ONLY);
        leftFrontModule = new LeftFrontSwerveModule(hardware.leftFrontMotorQ, hardware.leftFrontServo, hardware.leftFrontEncoder);
        leftRearModule = new LeftRearSwerveModule(hardware.leftRearMotorQ, hardware.leftRearServo, hardware.leftRearEncoder);
        rightFrontModule = new RightFrontSwerveModule(hardware.rightFrontMotorQ, hardware.rightFrontServo, hardware.rightFrontEncoder);
        rightRearModule = new RightRearSwerveModule(hardware.rightRearMotorQ, hardware.rightRearServo, hardware.rightRearEncoder);
    }

    @Override
    public void uponStart() {

    }

    @Override
    public void runLoop() {
        if (gamepad1.dpad_up) {
//            leftFrontModuleTargetOrientationRadians = incrementOrientationRadians(leftFrontModuleTargetOrientationRadians);
            leftFrontModuleTargetOrientationRadians += 0.001;
//            gamepad1.rumble(100);
        }
        else if (gamepad1.dpad_right) {
            leftRearTargetModuleOrientationRadians = incrementOrientationRadians(leftRearTargetModuleOrientationRadians);
//            gamepad1.rumble(100);
        }
        else if (gamepad1.dpad_down) {
            rightRearModuleTargetOrientationRadians = incrementOrientationRadians(rightRearModuleTargetOrientationRadians);
//            gamepad1.rumble(100);
        }
        else if (gamepad1.dpad_left) {
            rightFrontModuleTargetOrientationRadians = incrementOrientationRadians(rightFrontModuleTargetOrientationRadians);
//            gamepad1.rumble(100);
        }

        leftFrontModule.setTargetRotation(leftFrontModuleTargetOrientationRadians);
        leftRearModule.setTargetRotation(leftRearTargetModuleOrientationRadians);
        rightRearModule.setTargetRotation(rightRearModuleTargetOrientationRadians);
        rightFrontModule.setTargetRotation(rightFrontModuleTargetOrientationRadians);

        leftFrontModule.update();
        leftRearModule.update();
        rightRearModule.update();
        rightFrontModule.update();

        telemetry.addData("LF - Target", leftFrontModuleTargetOrientationRadians);
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
            System.out.println("Resetting orientation to 0");
        } else {
            o += 0.001;
            System.out.println("Incrementing orientation by 0.001");
        }
        return o;
    }
}
