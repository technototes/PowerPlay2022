package org.firstinspires.ftc.sixteen750.swerve_util;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@Config
public class RightRearSwerveModule extends AnotherSwerveModule {
    public static PIDCoefficients RR_ROTATION_PID = new PIDCoefficients(0.6, 0, 0.03);
    public static CRServoProfiler.Constraints RR_SERVO_CONSTRAINTS = new CRServoProfiler.Constraints(1, 1000, 2);

    public RightRearSwerveModule(DcMotorEx m, CRServo s, AbsoluteAnalogEncoder e) {
        super(m, s, e, RR_ROTATION_PID, RR_SERVO_CONSTRAINTS);
    }
}