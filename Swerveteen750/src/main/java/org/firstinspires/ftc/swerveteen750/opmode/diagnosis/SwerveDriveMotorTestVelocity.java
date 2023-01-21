package org.firstinspires.ftc.swerveteen750.opmode.diagnosis;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.swerveteen750.Hardware;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;

@Config
@TeleOp(group = "Test-Hardware")
public class SwerveDriveMotorTestVelocity extends CommandOpMode {
    ElapsedTime t;

    EncodedMotor<DcMotorEx> leftFrontMotor;
    EncodedMotor<DcMotorEx> leftRearMotor;
    EncodedMotor<DcMotorEx> rightFrontMotor;
    EncodedMotor<DcMotorEx> rightRearMotor;

    boolean isLeftFrontPressed, isLeftRearPressed, isRightRearPressed, isRightFrontPressed = false;

    // 0.1 is too little, the motor trying to move but not enough to move the robot; 0.2 is slightly better
    public static double motorVelocity = 0.3;
    public static double motorStopVelocity = 0.0;

    boolean isLeftSideConnected = true;
    boolean isRightSideConnected = true;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        /// Note: here is using the hardware from TechnoLib
        try {
            leftFrontMotor = new EncodedMotor<>(Hardware.HardwareConstant.LF_MOTOR);
            leftFrontMotor.setPIDFCoeffecients(ConfigurableSwerveDriveSubsystem.LF_MOTOR_VELO_PIDF_COEF);
            leftRearMotor = new EncodedMotor<>(Hardware.HardwareConstant.LR_MOTOR);
            leftRearMotor.setPIDFCoeffecients(ConfigurableSwerveDriveSubsystem.LR_MOTOR_VELO_PIDF_COEF);
        } catch (Exception e) {
            isLeftSideConnected = false;
        }

        try {
            rightFrontMotor = new EncodedMotor<>(Hardware.HardwareConstant.RF_MOTOR);
            rightFrontMotor.setPIDFCoeffecients(ConfigurableSwerveDriveSubsystem.RF_MOTOR_VELO_PIDF_COEF);
            rightRearMotor = new EncodedMotor<>(Hardware.HardwareConstant.RR_MOTOR);
            rightRearMotor.setPIDFCoeffecients(ConfigurableSwerveDriveSubsystem.RR_MOTOR_VELO_PIDF_COEF);
        } catch (Exception e) {
            isRightSideConnected = false;
        }
    }

    @Override
    public void uponStart() {
        t = new ElapsedTime();
    }

    @Override
    public void runLoop() {
        double loopSeconds = t.seconds();
        if (isLeftSideConnected) {
            if (this.gamepad1.dpad_left) {
                leftFrontMotor.setVelocity(motorVelocity);
                isLeftFrontPressed = true;

                leftRearMotor.setVelocity(motorVelocity);
                isLeftRearPressed = true;
            } else {
                leftFrontMotor.setVelocity(motorStopVelocity);
                isLeftFrontPressed = false;

                leftRearMotor.setVelocity(motorStopVelocity);
                isLeftRearPressed = false;
            }
        }
        if (isRightSideConnected) {
            if (this.gamepad1.dpad_right) {
                rightRearMotor.setVelocity(motorVelocity);
                isRightRearPressed = true;

                rightFrontMotor.setVelocity(motorVelocity);
                isRightFrontPressed = true;
            } else {
                rightRearMotor.setVelocity(motorStopVelocity);
                isRightRearPressed = false;

                rightFrontMotor.setVelocity(motorStopVelocity);
                isRightFrontPressed = false;
            }
        }

        telemetry.addLine("Visit 192.168.43.1:8080/dash to see the FTC-Dashboard");
        telemetry.addData("Loop Seconds", loopSeconds);

        if (isLeftSideConnected) {
            telemetry.addData(
                    "LeftFront - Motor - Velocity", leftFrontMotor.getDevice().getVelocity());
            telemetry.addData(
                    "LeftRear - Motor - Velocity", leftRearMotor.getDevice().getVelocity());
        } else {
            telemetry.addLine("WARNING: Left Disconnected");
        }
        if (isRightSideConnected) {
            telemetry.addData(
                    "RightRear - Motor - Velocity", rightRearMotor.getDevice().getVelocity());
            telemetry.addData(
                    "RightFront - Motor - Velocity++" +
                            "]", rightFrontMotor.getDevice().getVelocity());
        } else {
            telemetry.addLine("WARNING: Right Disconnected");
        }

        telemetry.addData("LeftFront - Motor - Pressed", isLeftFrontPressed);
        telemetry.addData("LeftRear - Motor - Pressed", isLeftRearPressed);
        telemetry.addData("RightRear - Motor - Pressed", isRightRearPressed);
        telemetry.addData("RightFront - Motor - Pressed", isRightFrontPressed);

        telemetry.update();
    }
}
