package org.firstinspires.ftc.swerveteen750.swerve_util.swerve_module;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.swerveteen750.swerve_util.AbsoluteAnalogEncoder;

@Config
public class LeftRearSwerveModule extends AnotherSwerveModule {
    public static PIDCoefficients LR_ROTATION_PID = new PIDCoefficients(0.6, 0, 0);
    public LeftRearSwerveModule(DcMotorEx m, CRServo s, AbsoluteAnalogEncoder e) {
        super(m, s, e, LR_ROTATION_PID, null);
    }

    public LeftRearSwerveModule(HardwareMap hardwareMap, String motorName, String servoName, String encoderName){
        this(
                hardwareMap.get(DcMotorEx.class, motorName),
                hardwareMap.get(CRServo.class, servoName),
                new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, encoderName))
        );
    }

    public LeftRearSwerveModule(HardwareMap hardwareMap, String motorName, String servoName, String encoderName, PIDCoefficients rotationPID){
        super(
                hardwareMap.get(DcMotorEx.class, motorName),
                hardwareMap.get(CRServo.class, servoName),
                new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, encoderName)),
                rotationPID,
                null // TODO: add motor PID
        );
    }
}
