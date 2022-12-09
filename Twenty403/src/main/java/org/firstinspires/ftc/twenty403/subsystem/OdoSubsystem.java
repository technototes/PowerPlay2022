package org.firstinspires.ftc.twenty403.subsystem;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.technototes.library.subsystem.Subsystem;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class OdoSubsystem implements Subsystem {

    DistanceSensor dLeft;
    DistanceSensor dRight;

    RevColorSensorV3 cLeft;
    ColorRangeSensor cMiddle;
    RevColorSensorV3 cRight;

    double leftDistance;
    double rightDistance;
    int leftColor;
    int middleColor;
    int rightColor;

    public OdoSubsystem(
        DistanceSensor dl,
        DistanceSensor dr,
        RevColorSensorV3 cl,
        ColorRangeSensor cm,
        RevColorSensorV3 cr
    ) {
        dLeft = dl;
        dRight = dr;
        cLeft = cl;
        cMiddle = cm;
        cRight = cr;
    }

    public double WallDistance() {
        // if left and right distance sensors measure the same distance
        if (leftDistance == rightDistance) {
            //
        } else if (dLeft.getDistance(DistanceUnit.CM) < dRight.getDistance(DistanceUnit.CM)) {
            //
        } else if (dLeft.getDistance(DistanceUnit.CM) > dRight.getDistance(DistanceUnit.CM)) {
            //
        }
        return 1234.5;
    }

    @Override public void periodic() {
        // Read the sensors and squirrel away the value
        leftDistance = dLeft.getDistance(DistanceUnit.CM);
        rightDistance = dRight.getDistance(DistanceUnit.CM);
        leftColor = cLeft.argb();
        middleColor = cMiddle.argb();
        rightColor = cRight.argb();
    }
}
