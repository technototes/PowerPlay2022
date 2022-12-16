package org.firstinspires.ftc.swerveteen750.swerve_util;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.util.Angle;
import com.qualcomm.robotcore.hardware.AnalogInput;

@Config
public class AbsoluteAnalogEncoder {
    public static double DEFAULT_RANGE = 3.3;
    public static boolean VALUE_REJECTION = false;
    private final AnalogInput encoder;
    private double offset, analogRange;
    private Encoder.Direction inverted;

    public AbsoluteAnalogEncoder(AnalogInput enc){
        this(enc, DEFAULT_RANGE);
    }
    public AbsoluteAnalogEncoder(AnalogInput enc, double analogRange){
        this.encoder = enc;
        this.analogRange = analogRange;
        this.offset = 0;
        this.inverted = Encoder.Direction.FORWARD;
    }
    public AbsoluteAnalogEncoder zero(double off){
        this.offset = off;
        return this;
    }
    public AbsoluteAnalogEncoder setInverted(Encoder.Direction invert){
        this.inverted = invert;
        return this;
    }
    public Encoder.Direction getDirection() {
        return inverted;
    }

    private double pastPosition = 1;

    public double getVoltage() {
        return this.encoder.getVoltage();
    }

    public double getCurrentPosition() {
        double pos = Angle.norm(this.getVoltage()*this.getDirection().getMultiplier()*Math.PI*2/2.3);
        //checks for crazy values when the encoder is close to zero
        if (!VALUE_REJECTION || Math.abs(Angle.normDelta(this.pastPosition)) > 0.1 || Math.abs(Angle.normDelta(pos)) < 1) {
            this.pastPosition = pos;
        }
        return this.pastPosition;
    }
}