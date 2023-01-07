package org.firstinspires.ftc.swerveteen750.opmode.diagnosis;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.swerveteen750.Hardware;
import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;
import org.firstinspires.ftc.swerveteen750.swerve_util.swerve_module.AnotherSwerveModule;

@TeleOp(group = "Test-Hardware")
public class SwerveModuleTest extends CommandOpMode {
    /* DOES NOT WORK CURRENTLY! */
    Hardware hardware;
    AnotherSwerveModule leftFrontModule;
    AnotherSwerveModule leftRearModule;
    AnotherSwerveModule rightFrontModule;
    AnotherSwerveModule rightRearModule;


    public double leftFrontModuleTargetOrientationRadians = 0;
    public double leftRearTargetModuleOrientationRadians = 0;
    public double rightRearModuleTargetOrientationRadians = 0;
    public double rightFrontModuleTargetOrientationRadians = 0;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.S_DRIVE_ONLY);
        leftFrontModule = new AnotherSwerveModule(hardware.leftFrontMotorQ, hardware.leftFrontServo, hardware.leftFrontEncoder, ConfigurableSwerveDriveSubsystem.LF_SERVO_ROTATION_PID_COEF, null);
        leftRearModule = new AnotherSwerveModule(hardware.leftRearMotorQ, hardware.leftRearServo, hardware.leftRearEncoder, ConfigurableSwerveDriveSubsystem.LR_SERVO_ROTATION_PID_COEF, null);
        rightFrontModule = new AnotherSwerveModule(hardware.rightFrontMotorQ, hardware.rightFrontServo, hardware.rightFrontEncoder, ConfigurableSwerveDriveSubsystem.RF_SERVO_ROTATION_PID_COEF, null);
        rightRearModule = new AnotherSwerveModule(hardware.rightRearMotorQ, hardware.rightRearServo, hardware.rightRearEncoder, ConfigurableSwerveDriveSubsystem.RR_SERVO_ROTATION_PID_COEF, null);
    }

    @Override
    public void uponStart() {

    }

    @Override
    public void runLoop() {
        if (gamepad1.dpad_up || gamepad1.triangle) {
//            leftFrontModuleTargetOrientationRadians = incrementOrientationRadians(leftFrontModuleTargetOrientationRadians);
            leftFrontModuleTargetOrientationRadians += 0.001;
            gamepad1.rumble(100);
        }
        else if (gamepad1.dpad_right || gamepad1.circle) {
            leftRearTargetModuleOrientationRadians = incrementOrientationRadians(leftRearTargetModuleOrientationRadians);
            gamepad1.rumble(100);
        }
        else if (gamepad1.dpad_down || gamepad1.cross) {
            rightRearModuleTargetOrientationRadians = incrementOrientationRadians(rightRearModuleTargetOrientationRadians);
            gamepad1.rumble(100);
        }
        else if (gamepad1.dpad_left || gamepad1.square) {
            rightFrontModuleTargetOrientationRadians = incrementOrientationRadians(rightFrontModuleTargetOrientationRadians);
            gamepad1.rumble(100);
        }

        leftFrontModule.setTargetRotation(leftFrontModuleTargetOrientationRadians);
        leftRearModule.setTargetRotation(leftRearTargetModuleOrientationRadians);
        rightRearModule.setTargetRotation(rightRearModuleTargetOrientationRadians);
        rightFrontModule.setTargetRotation(rightFrontModuleTargetOrientationRadians);

        leftFrontModule.update();
        leftRearModule.update();
        rightRearModule.update();
        rightFrontModule.update();

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
            System.out.println("Resetting orientation to 0");
        } else {
            o += 0.001;
            System.out.println("Incrementing orientation by 0.001");
        }
        return o;
    }
}
