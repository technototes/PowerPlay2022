package org.firstinspires.ftc.swerveteen750.subsystem.drive;

import static org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem.LF_MOTOR_VELO_PIDF_COEF;
import static org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem.LR_MOTOR_VELO_PIDF_COEF;
import static org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem.RF_MOTOR_VELO_PIDF_COEF;
import static org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem.RR_MOTOR_VELO_PIDF_COEF;
import static org.firstinspires.ftc.swerveteen750.swerve_util.swerve_module.AnotherSwerveModule.MAX_MOTOR_SPEED;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public class AnotherTankDriveSubsystem {
    public DcMotorEx leftFrontMotor;
    public DcMotorEx leftRearMotor;
    public DcMotorEx rightFrontMotor;
    public DcMotorEx rightRearMotor;

    public static double TICKS_POWER_CONVERSION = 2300;

    public AnotherTankDriveSubsystem(HardwareMap hardwareMap){
        leftFrontMotor = hardwareMap.get(DcMotorEx.class, "leftFrontMotor");
        leftRearMotor = hardwareMap.get(DcMotorEx.class, "leftRearMotor");
        rightFrontMotor = hardwareMap.get(DcMotorEx.class, "rightFrontMotor");
        rightRearMotor = hardwareMap.get(DcMotorEx.class, "rightRearMotor");

        MotorConfigurationType motorConfigurationType = leftFrontMotor.getMotorType().clone();
        motorConfigurationType.setAchieveableMaxRPMFraction(MAX_MOTOR_SPEED);
        leftFrontMotor.setMotorType(motorConfigurationType);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, LF_MOTOR_VELO_PIDF_COEF);

        leftRearMotor.setMotorType(motorConfigurationType);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRearMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, LR_MOTOR_VELO_PIDF_COEF);

        rightFrontMotor.setMotorType(motorConfigurationType);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, RF_MOTOR_VELO_PIDF_COEF);

        rightRearMotor.setMotorType(motorConfigurationType);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRearMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, RR_MOTOR_VELO_PIDF_COEF);
    }

    public void setMotorVelocity(double leftVelocity, double rightVelocity){
        leftFrontMotor.setVelocity(leftVelocity);
        leftRearMotor.setVelocity(leftVelocity);
        rightFrontMotor.setVelocity(rightVelocity);
        rightRearMotor.setVelocity(rightVelocity);
    }

    public void drive(double stickValue){
        setMotorVelocity(stickValue * TICKS_POWER_CONVERSION, -stickValue * TICKS_POWER_CONVERSION);
    }
}
