package org.firstinspires.ftc.swerveteen750.swerve_util.swerve_module;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.swerveteen750.swerve_util.AbsoluteAnalogEncoder;


@Config
public class RightFrontSwerveModule extends AnotherSwerveModule {
    public static PIDCoefficients RF_ROTATION_PID = new PIDCoefficients(0.6, 0, 0);

    public RightFrontSwerveModule(DcMotorEx m, CRServo s, AbsoluteAnalogEncoder e) {
        super(m, s, e, RF_ROTATION_PID);
    }

    public RightFrontSwerveModule(HardwareMap hardwareMap, String motorName, String servoName, String encoderName){
        this(
                hardwareMap.get(DcMotorEx.class, motorName),
                hardwareMap.get(CRServo.class, servoName),
                new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, encoderName))
        );
    }

    public RightFrontSwerveModule(HardwareMap hardwareMap, String motorName, String servoName, String encoderName, PIDCoefficients rotationPID){
        super(
                hardwareMap.get(DcMotorEx.class, motorName),
                hardwareMap.get(CRServo.class, servoName),
                new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, encoderName)),
                rotationPID
        );
    }
}
