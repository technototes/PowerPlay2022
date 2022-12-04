package org.firstinspires.ftc.twenty403.helpers;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ElapsedTimeHelper implements BooleanSupplier {

    DoubleSupplier timer;
    double timeout;

    public ElapsedTimeHelper(DoubleSupplier curTime, double seconds) {
        timer = curTime;
        timeout = seconds;
    }

    @Override
    public boolean getAsBoolean() {
        return timer.getAsDouble() < timeout;
    }
}
