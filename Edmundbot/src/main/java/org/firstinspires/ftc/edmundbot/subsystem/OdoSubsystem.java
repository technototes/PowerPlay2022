package org.firstinspires.ftc.edmundbot.subsystem;

import com.technototes.library.hardware.sensor.ColorDistanceSensor;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.edmundbot.helpers.ColorHelper;

public class OdoSubsystem implements Subsystem, Loggable {

    public enum GraySensorCombo {
        All,
        JustLeft,
        JustRight,
        LeftCenter,
        RightCenter,
        LeftRight,
        Weird,
    }

    Rev2MDistanceSensor dLeft;
    Rev2MDistanceSensor dRight;

    ColorDistanceSensor cLeft;
    ColorDistanceSensor cMiddle;
    ColorDistanceSensor cRight;

    double leftDistance;
    double rightDistance;
    int leftColor;
    int centerColor;
    int rightColor;

    public OdoSubsystem(
        Rev2MDistanceSensor dl,
        Rev2MDistanceSensor dr,
        ColorDistanceSensor cl,
        ColorDistanceSensor cm,
        ColorDistanceSensor cr
    ) {
        dLeft = dl;
        dRight = dr;
        cLeft = cl;
        cMiddle = cm;
        cRight = cr;
    }

    public OdoSubsystem() {
        this(null, null, null, null, null);
    }

    public double WallDistance(double angle) {
        // does not need to constantly check distance if stored in variable
        // cone stack is on the left
        if (leftDistance > 36 || rightDistance > 36) {
            return -123.4;
        } else return (Math.cos(angle) * (leftDistance + rightDistance) / 2);
    }

    //Gray = Math.abs(Red - Blue) < 50;

    public GraySensorCombo ReadSensors() {
        int leftRed = ColorHelper.red(leftColor);
        int rightRed = ColorHelper.red(rightColor);
        int centerRed = ColorHelper.red(centerColor);
        int leftBlue = ColorHelper.blue(leftColor);
        int rightBlue = ColorHelper.blue(rightColor);
        int centerBlue = ColorHelper.blue(centerColor);
        boolean leftGray = leftRed == leftBlue;
        boolean rightGray = rightRed == rightBlue;
        boolean centerGray = centerRed == centerBlue;

        if (leftGray == true && rightGray == true && centerGray == true) {
            return GraySensorCombo.All;
        }
        if (leftGray == true && rightGray == false && centerGray == false) {
            return GraySensorCombo.JustLeft;
        }
        if (leftGray == false && rightGray == true && centerGray == false) {
            return GraySensorCombo.JustRight;
        }
        if (leftGray == true && rightGray == false && centerGray == true) {
            return GraySensorCombo.LeftCenter;
        }
        if (leftGray == false && rightGray == true && centerGray == true) {
            return GraySensorCombo.RightCenter;
        }
        if (leftGray == true && rightGray == true && centerGray == false) {
            return GraySensorCombo.LeftRight;
        }
        return GraySensorCombo.Weird;
    }

    @Log
    public String odoData = "";

    @Override
    public void periodic() {
        if (dLeft == null) {
            leftDistance = 1000.0;
            rightDistance = 1000.0;
            leftColor = 0;
            rightColor = 0;
            centerColor = 0;
        } else {
            // Read the sensors and squirrel away the value
            leftDistance = dLeft.getDistance(DistanceUnit.INCH);
            rightDistance = dRight.getDistance(DistanceUnit.INCH);
            leftColor = cLeft.argb();
            centerColor = cMiddle.argb();
            rightColor = cRight.argb();
        }
        odoData =
            String.format(
                "O%s-%d,%d:%x|%x|%x",
                ReadSensors().toString(),
                (int) leftDistance,
                (int) rightDistance,
                leftColor,
                centerColor,
                rightColor
            );
    }
}
