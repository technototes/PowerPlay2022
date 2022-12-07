package org.firstinspires.ftc.twenty403.subsystem;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class OdoSubsystem {

    DistanceSensor dLeft;
    DistanceSensor dRight;

    RevColorSensorV3 cLeft;
    ColorRangeSensor cMiddle;
    RevColorSensorV3 cRight;

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

    double CheckDistance() {
        // if left and right distance sensors measure the same distance
        if (dLeft.getDistance(DistanceUnit.CM) == dRight.getDistance(DistanceUnit.CM)) {
            //
        } else if (dLeft.getDistance(DistanceUnit.CM) < dRight.getDistance(DistanceUnit.CM)) {
            //
        } else if (dLeft.getDistance(DistanceUnit.CM) > dRight.getDistance(DistanceUnit.CM)) {
            //
        }
    }
}
